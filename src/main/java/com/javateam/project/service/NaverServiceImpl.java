package com.javateam.project.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class NaverServiceImpl implements ApiService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String clientId = "oFAZF8Ag1uPdozPQf6rO";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "gGoYpHqWGX";//애플리케이션 클라이언트 시크릿값";
        
        try {
        	PrintWriter out = response.getWriter();
            String text = URLEncoder.encode(request.getParameter("search"), "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/news.json?query="+text+"&display=5&start=1&sort=sim"; // json 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            System.out.println("Response code: " +con.getResponseCode());
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response1 = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response1.append(inputLine+"\n");
            }
            br.close();
            out.println(response1.toString());
            System.out.println(response1.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
	}
}
