package com.javateam.project.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javateam.project.domain.LikeDTO;
import com.javateam.project.domain.LikeVO;
import com.javateam.project.service.DAOService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ArticleRestController {
	
	@Autowired
	private DAOService daoSvc;
	
	@RequestMapping(value = "likeInsert.do", 
			produces = "text/plain; charset=UTF-8")
	public String likeInsert(@RequestParam("userId")String userId,
							 @RequestParam("articleNum")String StrarticleNum){
		log.info("======좋아요 구현해보기=======");
		int articleNum = Integer.parseInt(StrarticleNum);
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null)
			ip = req.getRemoteAddr();
		if (ip == null || ip.length() == 0) {
	        ip = req.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0) {
	        ip = req.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0) {
	        ip = req.getRemoteAddr() ;
	    }
		LikeDTO likeDTO = new LikeDTO();
		likeDTO.setArticleNum(articleNum);
		likeDTO.setUserId(userId);
		likeDTO.setUserIp(ip);
		LikeVO likeVO = new LikeVO(likeDTO);
		boolean flag = daoSvc.likeInsert(likeVO);
		
		return flag ? daoSvc.likeCount(articleNum)+"": "실패";
	}
	
	@RequestMapping(value = "likeCount.do",produces="text/plain; charset=UTF-8")
	public String likeCount(@RequestParam("articleNum")String StrArticleNum) {
		int articleNum = Integer.parseInt(StrArticleNum);
		log.info("=======================넘어온 인자 : "+StrArticleNum);
		log.info("=========좋아요 count=========== : "+daoSvc.likeCount(articleNum));
		return daoSvc.likeCount(articleNum)+"";
	}
	
	@RequestMapping(value = "myArticle.do", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> myArticle(@RequestParam("userId")String userId){
		
		log.info("===============myArticle==============");
		log.info("=======================넘어온 인자 : "+userId);
		
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
		
		String json="";
		// mapper 객체 생성
	    ObjectMapper mapper = new ObjectMapper();
	    
	    try {
	    	// List로 반환된 값을 mapper로 writeValueAsString로 변환을 해줘서 String값을 json에 넣어줌
           json = mapper.writeValueAsString(daoSvc.getMyArticle(userId));
           
	    } catch (JsonProcessingException e) {
	        log.error("json exception !");
	        e.printStackTrace();
	    }
	   
	    return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/ReadArticle.do",produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> readArticle(@RequestParam("userId") String userId,
							  @RequestParam("articleNum") int articleNum) {
		
		log.info("===============articleRestController readArticle===========");
		log.info("==========userId : "+userId);
		log.info("==========articleNum : "+articleNum);
		
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/String; charset=UTF-8");
		
		return daoSvc.readInsert(userId, articleNum) ? 
				new ResponseEntity<String>("새로운 기사", responseHeaders, HttpStatus.OK) : 
				new ResponseEntity<String>("읽은 기사", responseHeaders, HttpStatus.OK);
	}
	
	//내가 본 기사 파싱
	@RequestMapping(value = "readArticle.do", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> readArticle(@RequestParam("userId")String userId){
		
		log.info("===============readArticle==============");
		log.info("=======================넘어온 인자 : "+userId);
		
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
		
		String json="";
		// mapper 객체 생성
	    ObjectMapper mapper = new ObjectMapper();
	    
	    try {
	    	// List로 반환된 값을 mapper로 writeValueAsString로 변환을 해줘서 String값을 json에 넣어줌
           json = mapper.writeValueAsString(daoSvc.getReadArticle(userId));
           
	    } catch (JsonProcessingException e) {
	        log.error("json exception !");
	        e.printStackTrace();
	    }
	   
	    return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
	}
	
}