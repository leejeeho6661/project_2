package com.javateam.project.dao;

import java.util.List;

import com.javateam.project.domain.BoardVO;

public interface BoardDAO {

	/**
	 * 게시글 쓰기(작성)
	 * 
	 * @param boardVO 게시글 객체
	 * @return 게시글 작성 성공 여부
	 */
	boolean writeBoard(BoardVO boardVO);
	
	/**
	 * 최근 시퀀스로 게시글번호 가져오기
	 *  
	 * @return 게시글 번호
	 */
	int getBoardNumByLastSeq();
	
	/**
	 * 개별 게시글 조회
	 * 
	 * @param boardNum 게시글 번호
	 * @return 게시글 객체
	 */
	BoardVO getBoard(int board_num);

	/**
	 * 개별 게시글 조회수 수정(업데이트)
	 * 
	 * @param boardNum 게시글
	 * @return 수정 성공 여부
	 */
	boolean updateReadCount(int board_num);
	
	/**
	 * 전체 게시글 목록 조회 (페이징 적용)
	 * 
	 * @param page 현재 페이지
	 * @param rowsPerPage 페이지당 게시글 수 
	 * @return 게시글 목록
	 */
	List<BoardVO> getArticleList(int page, int rowsPerPage);
	
	/**
	 * 총 게시글 수 조회
	 * 
	 * @return 총 게시글 수
	 */
	int getListCount();

	/**
	 * 개별 게시글 수정
	 * 
	 * @param boardVO 게시글 객체
	 * @return 게시글 수정 성공 여부
	 */
	boolean updateBoard(BoardVO boardVO);
	
	/**
	 * 게시글 검색
	 * 
	 * @param searchKind 검색어 종류
	 * @param searchWord 검색어
	 * @param rowsPerPage 페이지당 게시글 수
	 * @param page 현재 페이지
	 * @return 조회 게시글 목록
	 */
	List<BoardVO> getBoardBySearch(String searchKind, 
								   String searchWord, 
								   int rowsPerPage,
								   int page);
	
	/**
	 * 게시글 삭제
	 * 
	 * @param boardNum 삭제할 게시글 아이디
	 * @return 게사글 삭제 성공 여부
	 */
	boolean deleteBoard(int board_num);

	List<BoardVO> getNotice();
}
