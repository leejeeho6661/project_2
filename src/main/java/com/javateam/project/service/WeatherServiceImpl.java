package com.javateam.project.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements ApiService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	    StringBuilder sb = new StringBuilder();
	    Calendar c1 = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        
        int[][] region = {{60,127},{61,126},{98,76},{99,75},{89,90},
        				  {54,125},{60,74},{67,101},{102,84},{66,103},
        				  {60,120},{58,123},{62,121},{73,134},{69,107},
        				  {68,100},{63,89},{51,67},{89,91},{91,77},
        				  {52,38},{144,123}};
        /*String[] location = {"서울","강남구","부산","해운대","대구",
        				   "인천","광주","대전","울산","세종특별시",
        				   "경기도","목감","풍덕천","강원도","충청북도",
        				   "충청남도","전라북도","전라남도","경상북도","경상남도",
        				   "제주도","독도"};*/
        HttpURLConnection conn = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
        for(int i=0; i<=21; i++) {
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=fb7cE1Ocdv9bUYUT1xxTs%2BMDbiLILOOH4PQZ%2FCIT58dRzrF%2FMaxBTcYAmEVcxBB4nLKRQ6whtOr%2FjXsolrFuZQ%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("60", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
	        
	        
	        
		    urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(dateFormat.format(c1.getTime()), "UTF-8")); /* 오늘 날짜*/
	        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /*08시 발표(정시단위)*/
	      
	        
	        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(region[i][0]+"", "UTF-8")); /*예보지점의 X 좌표값*/
	        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(region[i][1]+"", "UTF-8")); /*예보지점 Y 좌표*/
	        URL url = new URL(urlBuilder.toString());
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        //System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {  // success
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {  // error
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	    
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        
	        rd.close();
        }
        conn.disconnect();
        out.println(new ResponseEntity<String>(sb.toString(), responseHeaders, HttpStatus.OK));
        
      /*  System.out.println(sb.toString());*/
        //sb.append(location);
        
	}

	public String getWeatherJson(HttpServletRequest request, HttpServletResponse response, int x, int y)throws ServletException, IOException {
		//PrintWriter out = response.getWriter();
	    StringBuilder sb = new StringBuilder();
	    Calendar c1 = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        
        /*int[][] region = {{60,127},{61,126},{98,76},{99,75},{89,90},
        				  {54,125},{60,74},{67,101},{102,84},{66,103},
        				  {60,120},{58,123},{62,121},{73,134},{69,107},
        				  {68,100},{63,89},{51,67},{89,91},{91,77},
        				  {52,38},{144,123}};*/
        /*String[] location = {"서울","강남구","부산","해운대","대구",
        				   "인천","광주","대전","울산","세종특별시",
        				   "경기도","목감","풍덕천","강원도","충청북도",
        				   "충청남도","전라북도","전라남도","경상북도","경상남도",
        				   "제주도","독도"};*/
        HttpURLConnection conn = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=fb7cE1Ocdv9bUYUT1xxTs%2BMDbiLILOOH4PQZ%2FCIT58dRzrF%2FMaxBTcYAmEVcxBB4nLKRQ6whtOr%2FjXsolrFuZQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("60", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
        
        
        
	    urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(dateFormat.format(c1.getTime()), "UTF-8")); /* 오늘 날짜*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /*08시 발표(정시단위)*/
      
        
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(x+"", "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(y+"", "UTF-8")); /*예보지점 Y 좌표*/
        URL url = new URL(urlBuilder.toString());
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        //System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {  // success
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {  // error
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
    
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        
        rd.close();
        conn.disconnect();
        
      /*  System.out.println(sb.toString());*/
        //sb.append(location);
		
		
		return sb.toString();
	}

}
