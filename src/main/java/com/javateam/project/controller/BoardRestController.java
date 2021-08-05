package com.javateam.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javateam.project.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BoardRestController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/SelectNotice.do")
	public ResponseEntity<String> selectNotice() {
		log.info("BoardRestController getNotice");
		
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
       
	    String json="";
	    ObjectMapper mapper = new ObjectMapper();
	    
	    try {
           json = mapper.writeValueAsString(boardService.getNotice());
           
	    } catch (JsonProcessingException e) {
	        log.error("json exception !");
	        e.printStackTrace();
	    }
	   
	    return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
		//return commentService.commentList(ano);
	}
	
	

}