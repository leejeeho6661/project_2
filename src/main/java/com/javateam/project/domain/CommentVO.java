package com.javateam.project.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class CommentVO {

	private int cno;
	private int ano;
	private String ccontent;
	private String writer;
	private Date cdate;

	public CommentVO() {
		
	}
	
	//CommentDTO -> CommentVO
	public CommentVO(CommentDTO comment) {
		this.cno = comment.getCno();
		this.ano = comment.getAno();
		this.ccontent = comment.getCcontent();
		this.writer = comment.getWriter();
		this.cdate = comment.getCdate();
		
	}
}
