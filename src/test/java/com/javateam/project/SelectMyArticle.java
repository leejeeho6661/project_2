package com.javateam.project;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javateam.project.dao.BoardDAO;
import com.javateam.project.dao.DAO;
import com.javateam.project.domain.ArticleVO;
import com.javateam.project.domain.BoardVO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration // Spring 4.3.x 이후로는 필히 첨부 !
@Slf4j
public class SelectMyArticle {
	
	ArticleVO article;

	@Autowired
	private DAO dao;
	
	
	//@Transactional(propagation=Propagation.REQUIRES_NEW)
	//@Rollback(true)
	@Test
	public void test() {
		log.debug("############## select myarticle ###################");
		//List<ArticleVO> list = dao.getMyArticle("leejeeho6661");
		//assertEquals(2, list.size());
		//assertEquals("안녕하세요", list.get(0).getBoard_title().trim());
	}
}
