package com.javateam.project.service;

import java.util.List;

import com.javateam.project.domain.CommentVO;


public interface CommentService {
	
	//댓글 개수
	public int commentCount(int ano);
	
	// 댓글 목록
	public List<CommentVO> commentList(int ano);
	
	// 댓글 작성
	public boolean commentInsert(CommentVO comment);
	
	// 댓글 수정
	public boolean commentUpdate(CommentVO comment);
	
	// 댓글 삭제
	public boolean commentDelete(int cno);

	int commentSqLast();
}
