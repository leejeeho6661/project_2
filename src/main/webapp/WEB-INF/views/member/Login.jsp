<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/login.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<jsp:include page="../Header.jsp"></jsp:include>
<style>
div#naver_id_login{
height: 48px;
}
</style>
<div class="text-center">
	<form 	id="loginForm" name="loginForm"	class="form-signin"	method="POST" action="<c:url value='/login?${_csrf.parameterName}=${_csrf.token}' />">
		<img class="mb-4" src="img/news_3.png" alt="나만 몰랐던 NEWS" id="news_3">
		<div id="col-log">
			<div class="SimpleLogin">
				<img src="img/login.JPG" id="login_img" alt="로그인">
				<label for="username" class="sr-only">Id</label> 
				<input type="text" id="username" name="username" class="form-control" placeholder="아이디" required autofocus> 
				<label for="password" class="sr-only">Password</label> 
				<input type="password" id="password" name="password" class="form-control" placeholder="비밀번호" required>
				<div class="checkbox mb-3 float-right" id="id_save">
					<label> <input type="checkbox" value="remember-me">
						아이디 저장
					</label>
				</div>
				<button class="btn btn-secondary btn-block" type="submit" id="login_button">로그인</button>
			</div>
			
			<div class="SimpleLogin">
				<img src="img/easy_login.JPG" id="login_img_2" alt="간편 로그인">
				<div id="naver_id_login">
					<button class="btn btn-lg btn-success btn-block" type="button" id="button_naver" onclick="location.href='${url}'">Naver</button>
				</div>
				<button class="btn btn-lg btn-warning btn-block" type="submit" id="button_kakao">Kakao</button>
				<button class="btn btn-lg btn-light btn-block" type="submit" id="button_google">Google</button>
			</div>
		</div>
		<div class="id_found">
		<a href="#" class="mb-3 text-muted">아이디 찾기</a>
		<span>|</span>
		<a href="#" class="mb-3 text-muted">비밀번호 찾기</a>
		<span>|</span>
		<a href="Agree.do" class="mb-3 text-muted">회원가입</a>
		</div>
	</form>
</div>

<jsp:include page="../Footer.jsp"></jsp:include>