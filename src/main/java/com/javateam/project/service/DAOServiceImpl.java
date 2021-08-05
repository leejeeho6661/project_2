package com.javateam.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.javateam.project.dao.DAO;
import com.javateam.project.domain.ArticleVO;
import com.javateam.project.domain.LikeVO;
import com.javateam.project.domain.MyArticleVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DAOServiceImpl implements DAOService {

	@Autowired
	private DAO dao;
	
	@Autowired
    private DataSourceTransactionManager transactionManager;
	
	//@Autowired
	//private TransactionTemplate transactionTemplate;
	
	// 플래그 변수를 익명 클래스 메서드 안에 등록하는 에러나는 부분을 패치 -> 멤버 변수화 
	private boolean flag = false;

	@Override
	public boolean insertArticle(ArticleVO article) {

		log.debug("service insertArticle");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = transactionManager.getTransaction(def);
        
        try {
        	flag = dao.insertArticle(article);
        	
        	if(flag == true) 
        		transactionManager.commit(status);
        	else
        		throw new Exception();
        }catch (Exception e) {
        	transactionManager.rollback(status);
            flag = false;
			e.printStackTrace();	
		}
		return flag;
	
	}

	@Override
	public int getArticleNumByLastSeq() {
		log.info("Service getArticleNumByLastSeq");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		int result = 0;
		
		try {
			result = dao.getArticleNumByLastSeq();
			transactionManager.commit(status);
			
		}catch(Exception e) {
			transactionManager.rollback(status);
			log.error("Service getArticleNumByLastSeq Exception" + e.getMessage());
		}
		return result;
	}

	@Override
	public List<ArticleVO> getArticleList(int page, int rowsPerPage) {
		log.info("Service getArticleList");
		

		TransactionDefinition def= new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		List<ArticleVO> list = null;
		
		try {
			list = dao.getArticleList(page, rowsPerPage);
		}catch(Exception e) {
			transactionManager.rollback(status);
			log.error("Servicce getArticleList Exception :" + e.getMessage());
		}
		return list;
	}

	@Override
	public List<ArticleVO> getArticleListByCategory(int page, int rowsPerPage, String boardwritecategory) {
		log.info("Service getArticleListbycategory");
		
		TransactionDefinition def= new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		List<ArticleVO> list = null;
		
		try {
			list = dao.getArticleListByCategory(page, rowsPerPage, boardwritecategory);
		}catch(Exception e) {
			transactionManager.rollback(status);
			log.error("Servicce getArticleList Exception :" + e.getMessage());
		}
		return list;
	}
	
	@Override
	public int getListArticleCount() {
		log.info("Service getListArticleCount");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		int result = 0;
		
		try {
			result = dao.getListArticleCount();
			transactionManager.commit(status);
			
		}catch(Exception e) {
			transactionManager.rollback(status);
			log.error("Service getListArticleCount Exception :" + e.getMessage());
		}
		
		return result;
	
	}

	@Override
	public List<ArticleVO> getArticleBySearch(String searchKind, String searchWord, int rowsPerPage, int page) {
		log.info("Service getArticleBySearch");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);

		List<ArticleVO> result = null;

		try {
			result = dao.getArticleBySearch(searchKind, searchWord, rowsPerPage, page);
			transactionManager.commit(status);

		} catch (Exception e) {
			transactionManager.rollback(status);
			log.error("Service getArticleBySearch Exception : " + e.getMessage());
		}
		return result;
	}

	@Override
	public int getListArticleCountByCategory(String boardwritecategory) {
		log.info("dao getListArticleCountByCategory");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		int result = 0;
		
		try {
			result = dao.getListArticleCountByCategory(boardwritecategory);
			transactionManager.commit(status);
		}catch (Exception e) {
			transactionManager.rollback(status);
			log.error("Service getArticleBySearch Exception : " + e.getMessage());
		}
		
		return result;
	}

	@Override
	public ArticleVO getArticle(int boardWriteNum) {
		log.info("dao getArticle");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
			
		ArticleVO articleVO = new ArticleVO(); 
		try {
			articleVO = dao.getArticle(boardWriteNum);
			transactionManager.commit(status);
			
		}catch(Exception e) {
			transactionManager.rollback(status);
			log.error("Service getArticle Exception" + e.getMessage());
		}
		return articleVO;
	}

	@Override
	public boolean updateReadCountArtice(int boardWriteNum) {
		log.info("Service updateReadCountArtice");
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED); 
		TransactionStatus status =transactionManager.getTransaction(def);
		
		boolean flag = false;
		
		try {
			flag = dao.updateReadCountArtice(boardWriteNum);
			if(flag == true) {
				transactionManager.commit(status);
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			log.error("Service updateReadCountArtice Exception : " + e.getMessage());
			transactionManager.rollback(status);
			e.printStackTrace();
			}
		return flag;
	}

	@Override
	public boolean articleUpdate(ArticleVO article) {
		log.info("=== Service articleUpdate ===");
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        
        boolean flag = false;
        
        try {
        	flag = dao.articleUpdate(article);
        	if(flag == true) {
        		transactionManager.commit(status);
        	}else {
        		throw new Exception();
        	}
        	
        }catch(Exception e) {
        	log.error("==== Service articleUpdate Exception :" + e.getMessage());
        	transactionManager.rollback(status);
        	flag = false;
        	e.printStackTrace();
        }
		return flag;
	}

	@Override
	public boolean articleDelete(int boardWriteNum) {
		log.info("=== Service articleDelete ====");
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		boolean flag = false;
		
		try {
			flag = dao.articleDelete(boardWriteNum);
			if(flag == true) {
				transactionManager.commit(status);
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			log.error("=== Service articleDelete Exception :" + e.getMessage());
			transactionManager.rollback(status);
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean likeInsert(LikeVO likeVO) {
		log.debug("service insertArticle");

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
        
        boolean flag = false;
        
        try {
			flag = dao.likeInsert(likeVO);
			if(flag) 
				transactionManager.commit(status);
			else
				throw new Exception();
		} catch (Exception e) {
			log.error("=== Service likeInsert Exception :" + e.getMessage());
			transactionManager.rollback(status);
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int likeCount(int articleNum) {
		log.info("dao likeCount");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		int result = 0;
		
		try {
			result = dao.likeCount(articleNum);
			transactionManager.commit(status);
		} catch (Exception e) {
			log.error("=== Service likeCount Exception :" + e.getMessage());
			transactionManager.rollback(status);
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<MyArticleVO> getMyArticle(String userId) {
		log.info("Service getMyArticle");
		
		TransactionDefinition def= new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		List<MyArticleVO> list = null;
		
		try {
			list = dao.getMyArticle(userId);
		}catch(Exception e) {
			transactionManager.rollback(status);
			log.error("Servicce getMyArticle Exception :" + e.getMessage());
		}
		return list;

	}

	@Override
	public boolean readInsert(String userId, int articleNum) {
		log.debug("service insertArticle");

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
        log.info("=======아이디 : "+userId);
        log.info("========번호 : "+articleNum);
        boolean flag = false;
        
        try {
			flag = dao.readInsert(userId, articleNum);
			if(flag) 
				transactionManager.commit(status);
			else
				throw new Exception();
		} catch (Exception e) {
			log.error("=== Service readInsert Exception :" + e.getMessage());
			transactionManager.rollback(status);
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<MyArticleVO> getReadArticle(String userId) {
		log.info("Service getMyArticle");
		
		TransactionDefinition def= new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		List<MyArticleVO> list = null;
		
		try {
			list = dao.getReadArticle(userId);
		}catch(Exception e) {
			transactionManager.rollback(status);
			log.error("Servicce getReadArticle Exception :" + e.getMessage());
		}
		return list;
	}
}
