package com.javateam.project.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.ServletContextResource;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JythonService {
	
	@Autowired
	private ServletContext servletContext;
	
	 //Resource resource = new ServletContextResource(servletContext, "/resources/python/");
	 
	public String resolvePythonScriptPath(HttpServletRequest request, 
			String path, String pythonFile) throws IOException {

			String contextPath = request.getContextPath().replace("/", "");
			ServletContextResource resource 
			= new ServletContextResource(servletContext, pythonFile);
			
			log.info("contextPath workPath : "+ resource.getFile().getPath());
			String workPath = resource.getFile().getPath();
			
			// "."이 포함된 경로를 jython이 실행할 때 에러를 유발하므로 ServletContext를 이용한 경로에서 
			// 순수 work 경로(".metadata" 이후 경로를 뺀 앞경로)만 추출하여 python 파일의 실제 경로를 추출
			workPath = workPath.substring(0, workPath.indexOf(".metadata")) + contextPath;
			log.info("실제 경로 : "+ workPath); // ex) D:\student\lsh\works\egov4\jythonTest
			
			return workPath + "\\src\\main\\java\\" + path + "\\" + pythonFile;
			}

	public void getInfoFromJython(HttpServletRequest request) throws IOException {
		
		 String line = resolvePythonScriptPath(request, "python", "younhab.py");
		
		log.info("--- real line : "+resolvePythonScriptPath(request, "python", "younhab.py"));
		
		line = "python " + line; 
		CommandLine cmdLine = null;
		
	    try {
	    	cmdLine = CommandLine.parse(line);
	        
		    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		    PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
		        
		    DefaultExecutor executor = new DefaultExecutor();
		    executor.setStreamHandler(streamHandler);

	    	executor.execute(cmdLine);
			
	    } catch (ExecuteException e) {
	    	log.error("실행 오류 : "+e);
		} catch (IOException e) {
			log.error("IO 오류 : "+e);
		} catch (Exception e) {
			log.error("다른 오류 : "+e);
		} //
		
	} //}
}
