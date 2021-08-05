package com.javateam.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javateam.project.service.NaverServiceImpl;

@Controller
public class NaverController {
 
	@RequestMapping("/naver.do")
	public void naver(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		NaverServiceImpl naversrv = new NaverServiceImpl();
		naversrv.execute(request, response);
		
	}
}
