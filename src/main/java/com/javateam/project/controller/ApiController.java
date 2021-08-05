package com.javateam.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javateam.project.service.ApiServiceImpl;
import com.javateam.project.service.WeatherServiceImpl;

@Controller
public class ApiController {
 
	
	@RequestMapping("/corona.do")
	public void corona(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		ApiServiceImpl apisrv = new ApiServiceImpl();
		apisrv.execute(request, response);
	}
	
	@RequestMapping("/weather.do")
	public void weather(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		WeatherServiceImpl wsImpl = new WeatherServiceImpl();
		wsImpl.execute(request, response);
	}
	
	@RequestMapping(value="/weather2.do", produces="application/json; charset=UTF-8")
	public ResponseEntity<String> boardDetailFeed(HttpServletRequest request, HttpServletResponse response,
												  @RequestParam("x") int x,
												  @RequestParam("y") int y)throws ServletException, IOException
	{
		WeatherServiceImpl wsImpl = new WeatherServiceImpl();
		
		// HTTP Header 정보 setting
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
		
		String json="";
	    ObjectMapper mapper = new ObjectMapper();
	    
        try {
            json = mapper.writeValueAsString(wsImpl.getWeatherJson(request, response,x,y));
	            
	     } catch (JsonProcessingException e) {
	         //log.error("json exception !");
	         e.printStackTrace();
	     }
	    
	     return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
	}
}
