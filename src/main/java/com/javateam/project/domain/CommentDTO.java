package com.javateam.project.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class CommentDTO {
		private int cno;
		private int ano;
		private String ccontent;
		private String writer;
		private Date cdate;

}
