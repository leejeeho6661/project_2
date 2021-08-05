package com.javateam.project.domain;

import lombok.Data;

@Data
public class MyArticleVO extends ArticleVO {

	private String userId;
	private int articleNum;
	private String userIp;
	

	
	
}
