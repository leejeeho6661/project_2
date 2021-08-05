package com.javateam.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
 
 
@Controller
@Slf4j
public class AuthController {
	
    @RequestMapping("/login")
    public String login(ModelMap model) {
    	
    	log.info("login");
    	
    	return "member/Login";
    }
    
    @RequestMapping(value="/logoutProc", method = RequestMethod.GET)
    public String logout(ModelMap model,
    					 HttpServletRequest request,
    					 HttpServletResponse response,
    					 HttpSession session) {
    	
    	log.info("logout");
    	
	    Authentication auth 
		    	= SecurityContextHolder.getContext()
		    						   .getAuthentication();
	    session.invalidate();
	    log.info("auth : "+auth);
	    
	    // logout !
	    if (auth != null) {    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }

	    return "Index_total";
    } //
    
    @RequestMapping(value="/loginError", method = RequestMethod.GET)
    public String loginError(ModelMap model) {
    	
	    model.addAttribute("error", "true");
	    model.addAttribute("msg", "로그인 정보가 잘못되었습니다.");
	    
	    return "member/Login";
    } //
    
/*   
    @RequestMapping("/access-denied")
    public String accessDenied() {
    	
    	log.info("403");
    	
    	return "redirect:/login";
    }
*/
    
    
 
} //