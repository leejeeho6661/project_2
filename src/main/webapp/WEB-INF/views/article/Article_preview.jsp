<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
img{
max-width: 800px;
max-height: 450px;
}
#article_detail_header{
margin-top:20px;
border-bottom:1.5px solid gray;
}
#article_detail_header_title{
font-family: Malgun Gothic;
font-size: 29px;
margin-top: 73px;
font-weight: bold;
}
#article_detail_header_time{
font-family: Malgun Gothic;
font-size: 14px;
color: gray;
}
#article_img{
margin-top: 34px;
width: 70%;

}
#atricle_detail_article{
display: block;
text-align: center;
}
#atricle_detail_article_writer{
font-size : 14px;
font-family: Malgun Gothic;
text-align : right;
width: 949px;
margin-top: 10px;
color: darkgray;
font-weight: bolder;
}
#atricle_detail_article_detail{
font-family: Malgun Gothic;
width: 831px;
text-align: left;
margin:46px auto 125px auto;
}

#article_detail_buttons{
	min-height: 50px;
	overflow: hidden;
}
#article_detail_buttons .article_detail_button{
display: inline-block;
float: right;
margin:auto 15px auto 0;

}
#article_detail_header #article_detail_hit {
    font-family: Malgun Gothic;
    display: inline-block;
    float: right;
    text-align: right;
    font-size : 14px;
}
</style>
<div class="container">
	<div id="article_detail_header">
		<h1 id="article_detail_header_title"></h1>
		<span id="article_detail_header_time">기사입력 : ${preToday}</span>
		<span id="article_detail_hit">조회수:0</span>
</div>
	
	<div id="atricle_detail_article">
		<img alt="기사 사진" src="${preVO.prePicture}" id="article_img"> 
		<p id="atricle_detail_article_writer"></p>
		<div id="atricle_detail_article_detail">
				
		</div>
	</div>
	<div id="article_detail_like">
		<button>♡</button>
		<span id="article_detail_likeCount"></span>
	</div>
	
	<div id="article_detail_buttons">
		<button id="article_detail_modify" class="article_detail_button btn btn-outline-secondary" >수정</button>
		<button id="article_detail_delete" class="article_detail_button btn btn-outline-secondary">삭제</button>
		<button id="article_detail_list" class="article_detail_button btn btn-outline-secondary">목록</button>
	</div>
</div>

