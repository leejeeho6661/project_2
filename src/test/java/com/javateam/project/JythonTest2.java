package com.javateam.project;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.python.jline.internal.Log;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration
public class JythonTest2 {
	
	private String resolvePythonScriptPath(String filename) {
		File file = new File("src/main/java/python/" + filename);
		return file.getAbsolutePath();
	}

	@Test
	public void test(){
		
		Log.info("-------------- jython test ----------------");
		Log.info("---- json 정보를 얻어오는데 다소 시간이 걸립니다. ----");
		
		String line = "python " + resolvePythonScriptPath("younhab.py");
	/*	String line = "python " + resolvePythonScriptPath("demo.py");*/
	    CommandLine cmdLine = CommandLine.parse(line);
	        
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
	        
	    DefaultExecutor executor = new DefaultExecutor();
	    executor.setStreamHandler(streamHandler);
	    
	    try {

	    	int exitCode = executor.execute(cmdLine);
	    	Log.info("--- json feed(push) info : "+outputStream.toString());
	    	Log.info("==================exitcode"+exitCode);
	    } catch (ExecuteException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} //

	} //
	
}