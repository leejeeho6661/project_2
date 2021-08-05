package com.javateam.project.domain;

import lombok.Data;

@Data
public class LikeVO {

	private String userId;
	private int articleNum;
	private String userIp;
	
	public LikeVO(LikeDTO likeDTO) {
		this.userId = likeDTO.getUserId();
		this.articleNum = likeDTO.getArticleNum();
		this.userIp = likeDTO.getUserIp();
	}

	
}


