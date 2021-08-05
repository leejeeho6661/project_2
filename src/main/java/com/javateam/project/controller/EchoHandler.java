package com.javateam.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/echo")
@Slf4j
public class EchoHandler extends TextWebSocketHandler{
    //세션 리스트
    private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

    //클라이언트가 연결 되었을 때 실행
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	
    	sessionList.add(session);
    	if(session.getPrincipal()==null) {
    		log.info("{} 연결됨", session.getId());
    	}
    	else
    		log.info("{} 연결됨",session.getPrincipal().getName());
    }

    //클라이언트가 웹소켓 서버로 메시지를 전송했을 때 실행
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	if(session.getPrincipal() ==null) {
	    	log.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
	        //모든 유저에게 메세지 출력
	        for(WebSocketSession sess : sessionList){
	        		sess.sendMessage(new TextMessage(session.getId()+"|"+message.getPayload()));
	        		//sess.sendMessage(new TextMessage(session.getPrincipal().getName()+"|"+message.getPayload()));
	        }
    	}
    	else {
    		log.info("{}로 부터 {} 받음", session.getPrincipal().getName(), message.getPayload());
	        //모든 유저에게 메세지 출력
	        for(WebSocketSession sess : sessionList){
	        		sess.sendMessage(new TextMessage(session.getPrincipal().getName()+"|"+message.getPayload()));
	        		//sess.sendMessage(new TextMessage(session.getPrincipal().getName()+"|"+message.getPayload()));
	        }
    	}
    }
    //클라이언트 연결을 끊었을 때 실행
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if(session.getPrincipal() != null) 
        	log.info("{} 연결 끊김.", session.getPrincipal().getName());
        
        else
        	log.info("{} 연결 끊김.", session.getId());

        sessionList.remove(session);
        
        
    }
}
