package com.javateam.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.javateam.project.domain.CommentVO;

public interface CommentMapper {

	//댓글 개수
	public int commentCount(@Param("ano")int ano);
	
	// 댓글 목록
	public List<CommentVO> commentList(@Param("ano")int ano);
	
	// 댓글 작성
	public void commentInsert(CommentVO comment);
	
	// 댓글 수정
	public void commentUpdate(CommentVO comment);
	
	// 댓글 삭제
	public void commentDelete(int cno);
		
	public int commentSqLast();









}
