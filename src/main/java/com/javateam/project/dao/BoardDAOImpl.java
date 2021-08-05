package com.javateam.project.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.project.domain.BoardVO;
import com.javateam.project.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean writeBoard(BoardVO boardVO) {
		
		log.info("dao writeBoard");
		boolean flag = false;
		
		try {
			sqlSession.getMapper(BoardMapper.class).writeBoard(boardVO);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public int getBoardNumByLastSeq() {
		log.info("dao getBoardNumByLastSeq");
		return sqlSession.getMapper(BoardMapper.class).getBoardNumByLastSeq();
	}

	@Override
	public BoardVO getBoard(int board_num) {
		log.info("dao selectBoard");
		return sqlSession.getMapper(BoardMapper.class).getBoard(board_num);
	}

	@Override
	public boolean updateReadCount(int board_num) {
		
		log.info("dao updateReadCount");
		boolean flag = false;
		
		try {
			sqlSession.getMapper(BoardMapper.class).updateReadCount(board_num);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// 게시물 전부다 가져오기 
	@Override
	public List<BoardVO> getArticleList(int page, int rowsPerPage) {
		
		log.info("dao getArticleList");
		return sqlSession.getMapper(BoardMapper.class).getArticleList(page, rowsPerPage);
	}

	// 게시물 갯수 (페이징)
	@Override
	public int getListCount() {

		log.info("dao getListCount");
		return sqlSession.getMapper(BoardMapper.class).getListCount();
	}

	// 업데이트
	@Override
	public boolean updateBoard(BoardVO boardVO) {
		
		log.info("dao updateBoard");
		boolean flag = false;
		
		try {
			sqlSession.getMapper(BoardMapper.class).updateBoard(boardVO);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// 검색
	@Override
	public List<BoardVO> getBoardBySearch(String searchKind, String searchWord, int rowsPerPage, int page) {
		log.info("dao getBoardBySearch");
		log.info("검색 구분 : {}", searchKind);
		log.info("검색어 : {}", searchWord);
		
		return sqlSession.getMapper(BoardMapper.class).getBoardBySearch(searchKind, searchWord, rowsPerPage, page);
	}

	// 삭제
	@Override
	public boolean deleteBoard(int board_num) {
		
		log.info("dao deleteBoard");
		boolean flag = false; 
		
		try {
			sqlSession.getMapper(BoardMapper.class).deleteBoard(board_num);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public List<BoardVO> getNotice() {
		log.info("dao getNotice");
		
		return sqlSession.getMapper(BoardMapper.class).getNotice();
	}

}
