package com.javateam.project;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.project.dao.DAO;
import com.javateam.project.domain.ArticleVO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration // Spring 4.3.x 이후로는 필히 첨부 !
@Slf4j
public class InsertTest {
/*	
	ArticleVO article;

	@Autowired
	private DAO dao;
	
	@Before
	public void setup() {
		article = new ArticleVO();
		article.setTitle("i love you jihyeon");
		article.setArticle("fnsdaiflnsdalf");
		article.setInsertdate("2020-07-01");
		article.setUploaddate("2020-07-01");
		article.setLink("http://www.naver.com");
	}
	
	//@Transactional(propagation=Propagation.REQUIRES_NEW)
	//@Rollback(true)
	@Test
	public void test() {
		log.debug("############## insert article ###################");
		dao.insertArticle(article);
	}*/
}
