package com.javateam.project.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.javateam.project.domain.ArticleVO;
import com.javateam.project.domain.LikeVO;
import com.javateam.project.domain.MyArticleVO;

public interface DAOService {

	
	boolean insertArticle(ArticleVO article);
	int getArticleNumByLastSeq();
	
	List<ArticleVO> getArticleList(@Param("page") int page,
								   @Param("rowsPerPage") int rowsPerPage);
	
	int getListArticleCount();
	
	List<ArticleVO> getArticleBySearch(@Param("searchKind") String searchKind,
									   @Param("searchWord") String searchWord,
									   @Param("rowsPerPage") int rowsPerPage,
									   @Param("page") int page);
	
	List<ArticleVO> getArticleListByCategory(@Param("page") int page,
											 @Param("rowsPerPage") int rowsPerPage,
											 @Param("boardwritecategory") String boardwritecategory);
	
	int getListArticleCountByCategory(@Param("boardwritecategory") String boardwritecategory);
	
	ArticleVO getArticle(int boardWriteNum);

	boolean updateReadCountArtice(int boardWriteNum);

	boolean articleUpdate(ArticleVO article);
	
	boolean articleDelete(int boardWriteNum);
	
	boolean likeInsert(LikeVO likeVO);
	
	int likeCount(int articleNum);
	
	List<MyArticleVO> getMyArticle(@Param("userId") String userId);
	
	boolean readInsert(@Param("userId") String userId,
			   @Param("articleNum") int articleNum);
	
	List<MyArticleVO> getReadArticle(@Param("userId") String userId);
}