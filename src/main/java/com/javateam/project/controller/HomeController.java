package com.javateam.project.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/*import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.project.domain.ArticleDTO;
import com.javateam.project.domain.ArticleVO;
import com.javateam.project.service.DAOService;
import com.javateam.project.service.JythonService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	private JythonService jSvc;
	
	@Autowired
	private DAOService daoSvc;
	
	@RequestMapping("/")
	public String home(HttpServletRequest request, Model model) throws IOException {
		log.info("index");
		//jSvc.getInfoFromJython(request);
		List<ArticleVO> articles = daoSvc.getArticleListByCategory(1, 10, "mostview");
		model.addAttribute("articles",articles);
		return "Index_total";
	}
	
	@RequestMapping("/Quiz.do")
	public String quiz() {
		log.info("quiz");
		return "quiz/Quiz";
	}
	@RequestMapping("/Preview.do")
	public String preview(Model model) {
		log.info("preview");
		Calendar c1 = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("preToday",dateFormat.format(c1.getTime()));
		return "article/Article_preview";
	}
}

