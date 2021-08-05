package com.javateam.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javateam.project.domain.CommentVO;
import com.javateam.project.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CommentRestController {
	
	@Autowired
	private CommentService commentService;
	
	// 댓글 리스트
	@RequestMapping("/CommentList.do")
	//private List<CommentVO> commentList(@RequestParam("ano") int ano){
	public ResponseEntity<String> commentList(@RequestParam("ano") int ano){
		
		log.info("=== CommentRest CommentList");
		
		// HTTP Header 정보 setting
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
       
	    String json="";
	    ObjectMapper mapper = new ObjectMapper();
	    
	    try {
           json = mapper.writeValueAsString(commentService.commentList(ano));
           
	    } catch (JsonProcessingException e) {
	        log.error("json exception !");
	        e.printStackTrace();
	    }
	   
	    return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
		//return commentService.commentList(ano);
       
	}
	// insert
	@RequestMapping(value = "/CommentInsert.do", 
			produces = "text/plain; charset=UTF-8")
	public String commentInsert(@RequestParam("comment_ano") int ano,
								@RequestParam("comment_textarea") String ccontent,
								@RequestParam("comment_write") String write){
		
		log.info("=== CommentREST CommentInsert ===");
		
		CommentVO comment = new CommentVO();
        comment.setAno(ano);
        comment.setCcontent(ccontent);
        //로그인 기능을 구현했거나 따로 댓글 작성자를 입력받는 폼이 있다면 입력 받아온 값으로 사용하면 됩니다. 저는 따로 폼을 구현하지 않았기때문에 임시로 "test"라는 값을 입력해놨습니다.
        comment.setWriter(write);  
        
        comment.setCno(commentService.commentSqLast());
		
        return commentService.commentInsert(comment) ? "성공":"실패";

	
	}
	
	@RequestMapping(value = "/CommentDelete.do/{comment_ano}", 
					produces = "text/plain; charset=UTF-8")
	public String commentDelete(@PathVariable int comment_ano) {
		log.info("=== CommentRestCotroller CommentDeleted====");
		boolean flag =  commentService.commentDelete(comment_ano);
		return flag ? "성공" : "실패";
		
	}
	
	@RequestMapping(value = "/CommentCount.do/{comment_ano",
					produces = "text/plain; charset=UTF-8")
	public String commentCount(@PathVariable int comment_ano) {
		log.info("=== CommentRestController CommentCount : ");
		return commentService.commentCount(comment_ano)+"";
	}

}