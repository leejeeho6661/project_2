package com.javateam.project.domain;

import lombok.Data;

@Data
public class LikeDTO {
	private String userId;
	private int articleNum;
	private String userIp;
}
