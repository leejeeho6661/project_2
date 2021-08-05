<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>나만 몰랐었던 NEWS</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="${contextPath}/js/header.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="${contextPath}/css/bootstrap.css" rel="stylesheet">
<link href="${contextPath}/css/index.css" rel="stylesheet">
<link href="${contextPath}/css/header.css" rel="stylesheet">
<script>
function goNaver(){
	var query= $("#naverQuery").val();
	console.log(query);
	var win = window.open("https://search.naver.com/search.naver?ie=utf8&query="+query+"");
    win.focus();
}
</script>
</head>
<body>
<div class="container-fluid" id="header_line">
<div class="container">
	<header class="blog-header py-3">
		<div
			class="row flex-nowrap justify-content-between align-items-center pb-2">
			<div class="col-4 pt-3">
				<p id="weather_location"></p>
				<img id="weather_img" src="${contextPath}/img/clould_weather.JPG"><span id="weather_temp"></span>
			</div>
			<div class="col-4 text-center">
				<a class="blog-header-logo text-dark" href="${contextPath}"><strong>나만 몰랐던 NEWS</strong></a>
			</div>
			<div class="col-4 d-flex justify-content-end align-items-center pt-2">
			<c:if test="${empty pageContext.request.userPrincipal.name && sessionId == null}">
				<a class="material-icons pr-2 text-muted" href="${contextPath}/naverLogin" aria-label="Sign">person</a> 
				<a class="btn btn-sm btn-outline-secondary" href="${contextPath}/member/Agree.do">Sign up</a>
			</c:if>
			<sec:authorize access="isAuthenticated()">
           		<sec:authentication property="principal.username" var="user_id" />
           		<input type="hidden" id="login_id" value="${pageContext.request.userPrincipal.name}">
            	<span class="pr-4">${user_id}님 환영합니다.</span><br>
				<a class="material-icons pr-2 text-muted" href="${contextPath}/member/update.do?Id=${user_id}" aria-label="Modify">person</a> 
				<input 	type="button" class="btn btn-sm btn-outline-secondary"
			   			value="로그아웃"
			   			onclick="location.href='${contextPath}/logoutProc'" />
            </sec:authorize>
            <c:if test="${sessionId != null}">
            	<input type="hidden" id="login_id" value="${sessionId}">
            	<span class="pr-4">${sessionId}님 환영합니다.</span><br>
				<%-- <a class="material-icons pr-2 text-muted" href="${contextPath}/member/update.do?Id=${user_id}" aria-label="Modify">person</a> --%> 
				<input 	type="button" class="btn btn-sm btn-outline-secondary"
			   			value="로그아웃"
			   			onclick="location.href='${contextPath}/logoutProc'" />
            </c:if>
			
			</div>
		</div>

		<div id="row flex-row justify-content-between">
			<div id="time" class="col-4 align-middle p-0"></div>
			<div id="search" class="col-4 p-0 pt-3 d-inline-flex justify-content-center align-content-center">
				<input type="search" id="naverQuery" onkeydown="javascript:if(event.keyCode== 13) {goNaver(); return false;}"><span id="search-icon" onclick="goNaver();"><svg
						class="rounded" xmlns="http://www.w3.org/2000/svg" width="27"
						height="26" fill="none" stroke="currentColor"
						stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
						role="img" viewBox="0 0 24 24" focusable="false">
							<title>Search</title><circle cx="10.5" cy="10.5" r="7.5" />
							<path d="M21 21l-5.2-5.2" /></svg></span>
			</div>
			<div id="today" class="col-3 align-middle justify-content-end d-flex">
				<a href="https://news.naver.com/main/list.nhn?mode=LPOD&mid=sec&sid1=001&sid2=140&oid=001&isYeonhapFlash=Y">today's
					news</a>
			</div>
		</div>
	</header>
	<div class="nav-scroller py-1 mb-2" id="header_nav">
			<nav class="nav d-flex justify-content-between">
				<a class="p-2 text-muted" href="${contextPath}/Article/it/1.do">IT</a> 
				<a class="p-2 text-muted" href="${contextPath}/Article/economy/1.do">경제</a>
				<a class="p-2 text-muted" href="${contextPath}/Article/society/1.do">사회</a> 
				<a class="p-2 text-muted" href="${contextPath}/Article/sport/1.do">스포츠</a> 
				<a class="p-2 text-muted" href="#" id="popup_quiz">Quiz</a>
				<a class="p-2 text-muted" href="${contextPath}/boardList.do/1">자유게시판</a>
			</nav>
	</div>
	<%@ include file="sideslide.jsp" %>
</div>
</div>