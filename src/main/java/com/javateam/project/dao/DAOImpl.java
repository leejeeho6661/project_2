package com.javateam.project.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.project.domain.ArticleVO;
import com.javateam.project.domain.LikeVO;
import com.javateam.project.domain.MyArticleVO;
import com.javateam.project.mapper.ArticleMapper;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class DAOImpl implements DAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean insertArticle(ArticleVO article) {
		
		log.debug("insertArticle");
		// sqlSession.insert("com.javateam.project.mapper.ArticleMapper.insertArticle", article);
		boolean flag = false;
		
		try {
		sqlSession.getMapper(ArticleMapper.class).insertArticle(article);
		flag = true;
	} catch(Exception e) {
		e.printStackTrace();
	}
		return flag;
		
	}
	
	
	@Override
	public int getArticleNumByLastSeq() {
		log.info("dao getArticleNumByLastSeq");
		return sqlSession.getMapper(ArticleMapper.class).getArticleNumByLastSeq();
	}

	
	// 게시물 가져오기
	@Override
	public List<ArticleVO> getArticleList(int page, int rowsPerPage) {

		log.info("dao getArticleList");
		return sqlSession.getMapper(ArticleMapper.class).getArticleList(page, rowsPerPage);
	}

	// 기사 갯수(페이징)
	@Override
	public int getListArticleCount() {
		log.info("dao getListArticleCount");
		return sqlSession.getMapper(ArticleMapper.class).getListArticleCount();
	}

	// 검색
	@Override
	public List<ArticleVO> getArticleBySearch(String searchKind, String searchWord, int rowsPerPage, int page) {
		log.info("dao getArticleBySearch");
		log.info("검색 구분 : {}", searchKind);
		log.info("검색어 : {}", searchWord);
		return sqlSession.getMapper(ArticleMapper.class).getArticleBySearch(searchKind, searchWord, rowsPerPage, page);
	}


	@Override
	public List<ArticleVO> getArticleListByCategory(int page, int rowsPerPage, String boardwritecategory) {
		log.info("dao getArticleListbycategory");
		return sqlSession.getMapper(ArticleMapper.class).getArticleListByCategory(page, rowsPerPage, boardwritecategory);
	}


	@Override
	public int getListArticleCountByCategory(String boardwritecategory) {
		log.info("dao getListArticleCountByCategory");
		return sqlSession.getMapper(ArticleMapper.class).getListArticleCountByCategory(boardwritecategory);
	}


	@Override
	public ArticleVO getArticle(int boardWriteNum) {
		log.info("dao getArticle");
		return sqlSession.getMapper(ArticleMapper.class).getArticle(boardWriteNum);
	}


	@Override
	public boolean updateReadCountArtice(int boardWriteNum) {
		log.info("dao updateReadCountArtice");
		boolean flag = false;
		
		try {
			sqlSession.getMapper(ArticleMapper.class).updateReadCountArtice(boardWriteNum);
			flag = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	@Override
	public boolean articleUpdate(ArticleVO article) {
		log.info("==== dao articleUpdate =====");
		boolean flag = false;
		
		try {
			sqlSession.getMapper(ArticleMapper.class).articleUpdate(article);
			flag = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	@Override
	public boolean articleDelete(int boardWriteNum) {
		log.info("==== dao articleDelete ===");
		boolean flag = false;
		
		try {
			sqlSession.getMapper(ArticleMapper.class).articleDelete(boardWriteNum);
			flag = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	@Override
	public boolean likeInsert(LikeVO likeVO) {
		log.info("======= dao likeInsert========");
		boolean flag=false;
		try {
			sqlSession.getMapper(ArticleMapper.class).likeInsert(likeVO);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	@Override
	public int likeCount(int articleNum) {
		log.info("=======dao likeCount==========");
		return sqlSession.getMapper(ArticleMapper.class).likeCount(articleNum);
	}


	@Override
	public List<MyArticleVO> getMyArticle(String userId) {
		log.info("dao getMyArticle");
		log.info("아이디 : {}", userId);
		return sqlSession.getMapper(ArticleMapper.class).getMyArticle(userId);
	}


	@Override
	public boolean readInsert(String userId, int articleNum) {
		log.info("======= dao readInsert========");
		boolean flag=false;
		try {
			sqlSession.getMapper(ArticleMapper.class).readInsert(userId, articleNum);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	@Override
	public List<MyArticleVO> getReadArticle(String userId) {
		log.info("dao getReadArticle");
		log.info("아이디 : {}", userId);
		return sqlSession.getMapper(ArticleMapper.class).getReadArticle(userId);
	}
}
