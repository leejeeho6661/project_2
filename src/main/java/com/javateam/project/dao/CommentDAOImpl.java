package com.javateam.project.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.project.domain.CommentVO;
import com.javateam.project.mapper.CommentMapper;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CommentDAOImpl implements CommentDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int commentCount(int ano) {
	log.info("===dao CommentCount");	
		
		return sqlSession.getMapper(CommentMapper.class).commentCount(ano);
	}

	
	@Override
	public List<CommentVO> commentList(int ano) {
		log.info("===DAO CommentList");
		return sqlSession.getMapper(CommentMapper.class).commentList(ano);
	}

	@Override
	public boolean commentInsert(CommentVO comment) {
		log.info("=== DAO CommentInsert");
		boolean flag = false;
		
		try {
			sqlSession.getMapper(CommentMapper.class).commentInsert(comment);
			flag = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean commentUpdate(CommentVO comment) {
		log.info("===dao commentUpdate");
		boolean flag = false;
		
		try {
			sqlSession.getMapper(CommentMapper.class).commentUpdate(comment);
			flag = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean commentDelete(int cno) {
		log.info("===dao commentDelete===");
		boolean flag = false;
		
		try {
			sqlSession.getMapper(CommentMapper.class).commentDelete(cno);
			flag = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return  flag;
	}


	@Override
	public int commentSqLast() {
		log.info("== dao commentSqlAST ===");
		return sqlSession.getMapper(CommentMapper.class).commentSqLast();
	}

}
