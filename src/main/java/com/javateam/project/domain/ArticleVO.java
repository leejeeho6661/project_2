package com.javateam.project.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ArticleVO {

	private int boardWriteNum;
	private String boardWriteCategory;
	private String boardWriteTitle;
	private String boardWritePicture;
	private String boardWriteWriter;
	private String boardWriteArticle;
	private int boardWriteLike;
	private int boardWriteHit;
	private Date boardInsertDate;

	public ArticleVO() {
	}

	public ArticleVO(ArticleDTO articleDTO) {
		this.boardWriteNum = articleDTO.getBoardWriteNum();
		this.boardWriteCategory = articleDTO.getBoardWriteCategory();
		this.boardWriteTitle = articleDTO.getBoardWriteTitle();
		this.boardWritePicture = articleDTO.getBoardWritePicture().getOriginalFilename();
		this.boardWriteWriter = articleDTO.getBoardWriteWriter();
		this.boardWriteArticle = articleDTO.getBoardWriteArticle();
		this.boardWriteLike = articleDTO.getBoardWriteLike();
		this.boardWriteHit = articleDTO.getBoardWriteHit();
		this.boardInsertDate = articleDTO.getBoardInsertDate();
	}
	
	
	
}
