package com.javateam.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.javateam.project.dao.BoardDAO;
import com.javateam.project.domain.BoardVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	
	/*
	 * 트랜잭션 매니저(transactionManager)를 직접 와이어링 하거나  
	 * 트랜잭션 템플릿(transactionTemplate)을 와이어링 하는 방식 둘 중 하나를 사용하여 
	 * 트랜잭션 제어 프로그래밍을 할 수 있습니다. 
	 */
	
	@Autowired
    private DataSourceTransactionManager transactionManager;
	
	//@Autowired
	//private TransactionTemplate transactionTemplate;
	
	// 플래그 변수를 익명 클래스 메서드 안에 등록하는 에러나는 부분을 패치 -> 멤버 변수화 
	private boolean flag = false;
	
	@Override
	public boolean writeBoard(BoardVO boardVO) {
		
		log.info("Service writeBoard");
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        
        TransactionStatus status = transactionManager.getTransaction(def);
        
        try {
        	flag = boardDAO.writeBoard(boardVO);
        	
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
	public int getBoardNumByLastSeq() {
		log.info("Service getBoardNumByLastSeq");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		int result = 0;
		
		try {
			result = boardDAO.getBoardNumByLastSeq();
			transactionManager.commit(status);
			
		}catch(Exception e) {
			transactionManager.rollback(status);
			log.error("Service getBoardByLastSeq Exception" + e.getMessage());
		}
		return result;
	}

	@Override
	public BoardVO getBoard(int board_num) {
		log.info("Service getBoard");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		BoardVO boardVO = new BoardVO();
		
		try {
			boardVO = boardDAO.getBoard(board_num);
			transactionManager.commit(status);
			
		}catch(Exception e) {
			transactionManager.rollback(status);
			log.error("Service getBoard Exception" + e.getMessage());
		}
		return boardVO;
	}

	@Override
	public void updateReadCount(int board_num) {
		log.info("Service updateReadCount");
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED); 
		TransactionStatus status =transactionManager.getTransaction(def);
		
		boolean flag = false;
		
		try {
			flag = boardDAO.updateReadCount(board_num);
			if(flag == true) {
				transactionManager.commit(status);
			}else {
				throw new Exception();
			}
			
		}catch(Exception e) {
			log.error("Service updateReadCount Exception : " + e.getMessage());
			transactionManager.rollback(status);
			e.printStackTrace();
			
		}
		
	}

	@Override
	public List<BoardVO> getArticleList(int page, int rowsPerPage) {
		log.info("Service getArticleList");
		
		TransactionDefinition def= new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		List<BoardVO> list = null;
		
		try {
			list = boardDAO.getArticleList(page, rowsPerPage);
		}catch(Exception e) {
			transactionManager.rollback(status);
			log.error("Servicce getArticleList Exception :" + e.getMessage());
			
		}
		
		return list;
	}

	@Override
	public int getListCount() {
		
		log.info("Service getListCount");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		int result = 0;
		
		try {
			result = boardDAO.getListCount();
			transactionManager.commit(status);
			
		}catch(Exception e) {
			transactionManager.rollback(status);
			log.error("Service getListCount Exception :" + e.getMessage());
		}
		
		return result;
	}

	@Override
	public boolean updateBoard(BoardVO boardVO) {
		log.info("Service updateBoard");
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        
        boolean flag = false;
        
        try {
                flag = boardDAO.updateBoard(boardVO);
                if (flag == true) {
                	transactionManager.commit(status);
                } else {
                	throw new Exception();
                }
                
        } catch (Exception e) {
        	
        	log.error("Service updateBoard Exception : " + e.getMessage());
            transactionManager.rollback(status);
            flag = false;
            e.printStackTrace();            
        } // 
        
		return flag;
	}



	@Override
	public List<BoardVO> getBoardBySearch(String searchKind, String searchWord, int rowsPerPage, int page) {
		log.info("Service getBoardBySearch");

		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);

		List<BoardVO> result = null;

		try {
			result = boardDAO.getBoardBySearch(searchKind, searchWord, rowsPerPage, page);
			transactionManager.commit(status);

		} catch (Exception e) {
			transactionManager.rollback(status);
			log.error("Service getBoardBySearch Exception : " + e.getMessage());
		}

		return result;
	}



	@Override
	public boolean deleteBoard(int board_num) {
		log.info("Service deleteBoard");

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);

		boolean flag = false;

		try {
			flag = boardDAO.deleteBoard(board_num);
			if (flag == true) {
				transactionManager.commit(status);
			} else {
				throw new Exception();
			}

		} catch (Exception e) {

			log.error("Service deleteBoard Exception : " + e.getMessage());
			transactionManager.rollback(status);
			flag = false;
			e.printStackTrace();
		} //

		return flag;
	}



	@Override
	public List<BoardVO> getNotice() {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		List<BoardVO> result = null;
		
		try {
			result = boardDAO.getNotice();
			transactionManager.commit(status);

		} catch (Exception e) {
			transactionManager.rollback(status);
			log.error("Service getNotice Exception : " + e.getMessage());
		}

		return result;
	}

	
}
