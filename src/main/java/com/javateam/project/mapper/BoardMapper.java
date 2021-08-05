package com.javateam.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.javateam.project.domain.BoardVO;

public interface BoardMapper {

	void writeBoard(BoardVO boardVO);
	int getBoardNumByLastSeq(); // 마지막 시퀀스 리턴하는 메서드
	
	BoardVO getBoard(int board_num);
	void updateReadCount(int board_num);
	
	List<BoardVO> getArticleList(@Param("page") int page, 
								 @Param("rowsPerPage") int rowsPerPage);
	int getListCount();
	void updateBoard(BoardVO boardVO);
	List<BoardVO> getBoardBySearch(@Param("searchKind") String searchKind,
								   @Param("searchWord") String searchWord,
								   @Param("rowsPerPage") int rowsPerPage,
								   @Param("page") int page);
	void deleteBoard(int board_num);
	
	List<BoardVO> getNotice();
	
}
