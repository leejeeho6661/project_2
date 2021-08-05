package com.javateam.project.domain;

import java.sql.Date;

import com.javateam.project.domain.BoardVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardVO {
	private int board_num;
	private String board_title;
	private Date board_date;
	private String board_file;
	private String board_content;
	private String board_name;
	private int board_readcount;
	private int board_part;
	private int recnt;
	
	// BoardDTO -> BoardVO
	public BoardVO(BoardDTO board) {
		this.board_num = board.getBoard_num();
		this.board_title = board.getBoard_title();
		this.board_date = board.getBoard_date();
		this.board_file = board.getBoard_file().getOriginalFilename();
		this.board_content = board.getBoard_content();
		this.board_name = board.getBoard_name();
		this.board_readcount = board.getBoard_readcount();
		this.board_part = board.getBoard_part();
		
	}
		
	
	
}
