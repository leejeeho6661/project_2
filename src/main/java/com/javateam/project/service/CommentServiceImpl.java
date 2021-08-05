package com.javateam.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.javateam.project.dao.CommentDAO;
import com.javateam.project.domain.CommentVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
    private DataSourceTransactionManager transactionManager;
	
	// 플래그 변수를 익명 클래스 메서드 안에 등록하는 에러나는 부분을 패치 -> 멤버 변수화 
	private boolean flag = false;
	
	@Override
	public int commentCount(int ano) {
		
		log.info("== Service CommentCounnt ==");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		int result = 0;
		
		try {
			result = commentDAO.commentCount(ano);
			transactionManager.commit(status);
		}catch(Exception e) {
			transactionManager.rollback(status);
			log.error("== Service CommentCount Exception : " + e.getMessage());
		}
		return result;
	}

	
	
	@Override
	public List<CommentVO> commentList(int ano) {
		log.info("== Service CommentList ==");
		
		TransactionDefinition def= new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		List<CommentVO> list = null;
		
		try {
			list = commentDAO.commentList(ano);
			log.info("===========list.size() : "+list.size());
		}catch(Exception e) {
			transactionManager.rollback(status);
			log.error("== Service CommentList Exception : " + e.getMessage());
		}
		return list;
	}

	@Override
	public boolean commentInsert(CommentVO comment) {
		log.info("=== Service CommentInsert====");
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        
        TransactionStatus status = transactionManager.getTransaction(def);
        
        try {
        	flag = commentDAO.commentInsert(comment);
        	
        	if(flag == true)
        		transactionManager.commit(status);
        	else
        		throw new Exception();
        }catch(Exception e) {
        	transactionManager.rollback(status);
        	flag = false;
        	e.printStackTrace();
        }
		return flag;
	}

	@Override
	public boolean commentUpdate(CommentVO comment) {
		log.info("=== Service CommentUpdate");
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        
        boolean flag = false;
        
        try {
        	flag = commentDAO.commentUpdate(comment);
        	if(flag = true) {
        		transactionManager.commit(status);
        	}else {
        		throw new Exception();
        	}
        }catch(Exception e) {
        	log.error("== Service CommentUpdate Exception : " + e.getMessage());
        	transactionManager.rollback(status);
        	flag = false;
        	e.printStackTrace();
        }
		return flag;
	}

	@Override
	public boolean commentDelete(int cno) {
		log.info("== Service commentDelete ==");
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);

		boolean flag = false;
		
		try {
			flag = commentDAO.commentDelete(cno);
			if(flag == true) {
				transactionManager.commit(status);
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			log.error("== Servoce CommentDelete Exception :" + e.getMessage());
			transactionManager.rollback(status);
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}



	@Override
	public int commentSqLast() {
		log.info("=== Service commentSqLast ===");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		int result = 0;
		
		try {
			result = commentDAO.commentSqLast();
			transactionManager.commit(status);
			
		}catch(Exception e) {
			transactionManager.rollback(status);
			log.error("== Service commentSqLast Exception : " + e.getMessage());
		}
		
		return result;
	}
	
	
}
