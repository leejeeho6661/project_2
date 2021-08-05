package com.javateam.project.domain;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class ArticleDTO {
	
	private int boardWriteNum;
	private String boardWriteCategory;
	private String boardWriteTitle;
	private MultipartFile boardWritePicture;
	private String boardWriteWriter;
	private String boardWriteArticle;
	private int boardWriteLike;
	private int boardWriteHit;
	private Date boardInsertDate;
	private String prePicture;
	public String getPrePicture() {
		return prePicture;
	}
	public void setPrePicture(String prePicture) {
		this.prePicture = prePicture;
	}
	public int getBoardWriteNum() {
		return boardWriteNum;
	}
	public void setBoardWriteNum(int boardWriteNum) {
		this.boardWriteNum = boardWriteNum;
	}
	public String getBoardWriteCategory() {
		return boardWriteCategory;
	}
	public void setBoardWriteCategory(String boardWriteCategory) {
		this.boardWriteCategory = boardWriteCategory;
	}
	public String getBoardWriteTitle() {
		return boardWriteTitle;
	}
	public void setBoardWriteTitle(String boardWriteTitle) {
		this.boardWriteTitle = boardWriteTitle;
	}
	public MultipartFile getBoardWritePicture() {
		return boardWritePicture;
	}
	public void setBoardWritePicture(MultipartFile boardWritePicture) {
		this.boardWritePicture = boardWritePicture;
	}
	public String getBoardWriteWriter() {
		return boardWriteWriter;
	}
	public void setBoardWriteWriter(String boardWriteWriter) {
		this.boardWriteWriter = boardWriteWriter;
	}
	public String getBoardWriteArticle() {
		return boardWriteArticle;
	}
	public void setBoardWriteArticle(String boardWriteArticle) {
		this.boardWriteArticle = boardWriteArticle;
	}
	public int getBoardWriteLike() {
		return boardWriteLike;
	}
	public void setBoardWriteLike(int boardWriteLike) {
		this.boardWriteLike = boardWriteLike;
	}
	public int getBoardWriteHit() {
		return boardWriteHit;
	}
	public void setBoardWriteHit(int boardWriteHit) {
		this.boardWriteHit = boardWriteHit;
	}
	public Date getBoardInsertDate() {
		return boardInsertDate;
	}
	public void setBoardInsertDate(Date boardInsertDate) {
		this.boardInsertDate = boardInsertDate;
	}
	
	@Override
	public String toString() {
		return "ArticleDTO [boardWriteNum=" + boardWriteNum + ", boardWriteCategory=" + boardWriteCategory
				+ ", boardWriteTitle=" + boardWriteTitle + ", boardWritePicture=" + boardWritePicture
				+ ", boardWriteWriter=" + boardWriteWriter + ", boardWriteArticle=" + boardWriteArticle
				+ ", boardWriteLike=" + boardWriteLike + ", boardWriteHit=" + boardWriteHit + ", boardInsertDate="
				+ boardInsertDate + ", prePicture=" + prePicture + "]";
	}
	public ArticleDTO() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boardInsertDate == null) ? 0 : boardInsertDate.hashCode());
		result = prime * result + ((boardWriteArticle == null) ? 0 : boardWriteArticle.hashCode());
		result = prime * result + ((boardWriteCategory == null) ? 0 : boardWriteCategory.hashCode());
		result = prime * result + boardWriteHit;
		result = prime * result + boardWriteLike;
		result = prime * result + boardWriteNum;
		result = prime * result + ((boardWritePicture == null) ? 0 : boardWritePicture.hashCode());
		result = prime * result + ((boardWriteTitle == null) ? 0 : boardWriteTitle.hashCode());
		result = prime * result + ((boardWriteWriter == null) ? 0 : boardWriteWriter.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleDTO other = (ArticleDTO) obj;
		if (boardInsertDate == null) {
			if (other.boardInsertDate != null)
				return false;
		} else if (!boardInsertDate.equals(other.boardInsertDate))
			return false;
		if (boardWriteArticle == null) {
			if (other.boardWriteArticle != null)
				return false;
		} else if (!boardWriteArticle.equals(other.boardWriteArticle))
			return false;
		if (boardWriteCategory == null) {
			if (other.boardWriteCategory != null)
				return false;
		} else if (!boardWriteCategory.equals(other.boardWriteCategory))
			return false;
		if (boardWriteHit != other.boardWriteHit)
			return false;
		if (boardWriteLike != other.boardWriteLike)
			return false;
		if (boardWriteNum != other.boardWriteNum)
			return false;
		if (boardWritePicture == null) {
			if (other.boardWritePicture != null)
				return false;
		} else if (!boardWritePicture.equals(other.boardWritePicture))
			return false;
		if (boardWriteTitle == null) {
			if (other.boardWriteTitle != null)
				return false;
		} else if (!boardWriteTitle.equals(other.boardWriteTitle))
			return false;
		if (boardWriteWriter == null) {
			if (other.boardWriteWriter != null)
				return false;
		} else if (!boardWriteWriter.equals(other.boardWriteWriter))
			return false;
		return true;
	}
	

/*	public ArticleDTO(String boardWriteCategory, String boardWriteTitle, MultipartFile boardWritePicture,
			String boardWriteWriter, String boardWriteArticle, int boardWriteLike, int boardWriteHit, Date boardInsertDate) {
		super();
		this.boardWriteCategory = boardWriteCategory;
		this.boardWriteTitle = boardWriteTitle;
		this.boardWritePicture = boardWritePicture;
		this.boardWriteWriter = boardWriteWriter;
		this.boardWriteArticle = boardWriteArticle;
		this.boardWriteLike = boardWriteLike;
		this.boardWriteHit = boardWriteHit;
		this.boardInsertDate = boardInsertDate;
	}*/






}

