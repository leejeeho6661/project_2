package com.javateam.project.domain;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDTO {
	private int board_num;
	private String board_title;
	private Date board_date;
	private MultipartFile board_file;
	private String board_content;
	private String board_name;
	private int board_readcount =0;
	private int board_part;
	
}
