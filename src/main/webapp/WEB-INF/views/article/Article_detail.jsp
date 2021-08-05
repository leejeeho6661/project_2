<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../Header.jsp" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="${rootPath}/css/article_detail.css" rel="stylesheet">
<script type="text/javascript" src="${rootPath}/js/article_detail.js"></script>

<div class="container">
	<div id="article_detail_header">
		<h1 id="article_detail_header_title">${ArticleVO.boardWriteTitle}</h1>
		<span id="article_detail_header_time">기사입력 ${ArticleVO.boardInsertDate}</span>
		<span id="article_detail_hit">조회수: ${ArticleVO.boardWriteHit}</span>
		<input type="hidden" id="category" value="${ArticleVO.boardWriteCategory}">
		<input type="hidden" id="articleNum" value="${ArticleVO.boardWriteNum}">
		<input type="hidden" id="userId" value="${pageContext.request.userPrincipal.name}">
</div>
	
	<div id="atricle_detail_article">
		<img alt="기사 사진" src="${rootPath}/article_img/${ArticleVO.boardWritePicture}" id="article_img">
		<p id="atricle_detail_article_writer">${ArticleVO.boardWriteWriter}</p>
		<div id="atricle_detail_article_detail">
				${ArticleVO.boardWriteArticle}
		</div>
	</div>
	<div id="article_detail_like">
		<button onclick="like_article();">♡</button>
		<span id="article_detail_likeCount"></span>
	</div>
	
	<div id="article_detail_buttons">
		<c:if test="${ArticleVO.boardWriteWriter == pageContext.request.userPrincipal.name }">
		<button id="article_detail_modify" class="article_detail_button btn btn-outline-secondary" onclick="location.href='${rootPath}/article_update_view.do?boardWriteNum=${ArticleVO.boardWriteNum}'">수정</button>
		<button id="article_detail_delete" class="article_detail_button btn btn-outline-secondary" onclick="article_delete()">삭제</button>
		</c:if>
		<c:if test='${ArticleVO.boardWriteCategory !="mostview"}'>
		<button id="article_detail_list" class="article_detail_button btn btn-outline-secondary" onclick="location.href='${rootPath}/Article/${ArticleVO.boardWriteCategory}/1.do'">목록</button>
		</c:if>
		<c:if test='${ArticleVO.boardWriteCategory =="mostview"}'>
		<button id="article_detail_list" class="article_detail_button btn btn-outline-secondary" onclick="location.href='${rootPath}'">목록</button>
		</c:if>
	</div>
</div>

<%@ include file="../Footer.jsp" %>