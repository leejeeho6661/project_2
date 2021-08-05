package com.javateam.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javateam.project.service.AuthMyBatisService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthRestController {
	
	@Autowired
	private AuthMyBatisService svc;	
	
	// id 유무 점검 : spring security bug patch !
	 	@RequestMapping("/login/idCheck")
	 	public String idCheck(@RequestParam("username") String username,
	 						  Model model) {
	 		
	 		log.info("================userName : "+username);
	 		
	 		boolean flag = svc.hasUsername(username);
	 		
	 		return flag ? "true" : "false";
	 	} //
	
}