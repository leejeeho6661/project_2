package com.javateam.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javateam.project.dao.DAO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration // Spring 4.3.x 이후로는 필히 첨부 !
@Slf4j
public class InsertTest2 {
	
	String userId;
	int articleNum;
	
	@Autowired
	private DAO dao;
	
	@Before
	public void setup() {
		userId="leejeeho6661";
		articleNum = 7;
	}
	
	//@Transactional(propagation=Propagation.REQUIRES_NEW)
	//@Rollback(true)
	@Test
	public void test() {
		log.debug("############## readInsert ###################");
		assertTrue(dao.readInsert(userId, articleNum));
	}
}
