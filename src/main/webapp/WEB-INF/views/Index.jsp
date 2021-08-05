<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/index.js"></script>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<div class="container">

	<div id="mainCarousel" class="carousel slide" data-ride="carousel">

		<ol class="carousel-indicators">
			<li data-target="#mainCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#mainCarousel" data-slide-to="1"></li>
			<li data-target="#mainCarousel" data-slide-to="2"></li>
		</ol>

		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="img/main_slide1.PNG" class="d-block w-100" alt="black">

			</div>
			<div class="carousel-item">
				<img src="img/main_slide2.JPG" class="d-block w-100" alt="jimin">
			</div>
			<div class="carousel-item">
				<img src="img/main_slide3.jpg" class="d-block w-100" alt="black">
			</div>

		</div>

		<a class="carousel-control-prev" href="#mainCarousel" role="button"
			data-slide="prev"> <span class="carousel-control-prev-icon"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>

		</a> <a class="carousel-control-next" href="#mainCarousel" role="button"
			data-slide="next"> <span class="carousel-control-next-icon"
			aria-hidden="true"></span> <span class="sr-only">Next</span>
		</a>
	</div>
	<!--  코로나 현황 -->

	<!--  코로나 실시간 헤더 한번 해보기 -->
 <!--  연합뉴스 코로나 현황 -->
	<div class="content03 width1100">
		<aside class="aside-box27">
			<div class="bnr-wrap503">
				<span class="blind">배너</span>
				<div class="inner">
					<div class="con txt-con">
						<strong class="tit"><a href="//www.yna.co.kr/safe/index">코로나19
								국내 현황</a></strong> <span class="date" id="stateDate"></span>
					</div>
					<div class="con info-con">
							<div class="info-line01">
								<div class="info-item01">
									<span class="item">확진</span> <span class="count01"> <strong
										class="num" id="decideCnt"></strong><span class="fluc" >(<em class="pt" id="decideCntGap"></em>)</span>
									</span>
								</div>
								<div class="info-item02">
									<span class="item">격리해제</span> <span class="count02"> <strong
										class="num" id="clearCnt"></strong><span class="fluc">(<em class="pt" id="clearCntGap"></em>)</span>
									</span>
								</div>
								<div class="info-item03">
									<span class="item">사망</span> <span class="count03"> <strong
										class="num" id="deathCnt"></strong><span class="fluc">(<em class="pt" id="deathCntGap"></em>)</span>
									</span>
								</div>
								
								<div class= "tit-link">
									<span class="tit-link"><a href="//www.yna.co.kr/safe/index">뉴스 더보기 ></a></span>
								</div>
							</div>
							</div>
						
					</div>
				</div>
		</aside>
	</div>



	<!--  AJAX 검색창 -->
	<div class="container mt-4">
		<img class="article_search_2" src="img/article_search.JPG" alt="기사 검색">
		<nav class="navbar navbar-expand-sm">
			<form class="form-inline article-search">
				<div class="input-group">
					<input type="text" class="form-control" size="30" placeholder="기사 검색" id="need" onkeydown="javascript:if(event.keyCode== 13) {naver_search(); return false;}">
					<div class="input-group-append">
						<span class="input-group-text material-icons" onclick="naver_search();">search</span>
					</div>
				</div>
			</form>
		</nav>


	<!--  AJAX 여기까지 끝 -->
		<div class="container" id="naver_search_container">
			<div id="naver_search_result">
			</div>
		</div>

	</div>
	
	<!--  4칸 시작 -->
	<c:if test="${not empty articles}">
	<div class="container mt-4">
		<c:forEach var="articleVO" items="${articles}" varStatus="st">
			<c:choose>
			<c:when test="${st.count%2==1}">
			<div class="row mb-2">
				<div class="col-md-6" onclick="location.href='${rootPath}/Article_detail.do?boardWriteNum=${articleVO.boardWriteNum}'">
					<div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<div class="col p-4 d-flex flex-column position-static">
							<strong class="d-inline-block mb-2 text-primary">실시간 인기 뉴스</strong>
							<h5 class="mb-0" >${articleVO.boardWriteTitle}</h5>
							<div class="mb-1 text-muted">${articleVO.boardInsertDate}</div>
							<div class="card-text mb-auto">${articleVO.boardWriteArticle}</div>
							<!-- <a href="" class="stretched-link">Continue reading</a> -->
						</div>
						<div class="col-auto d-none d-lg-block">
							<img src="${rootPath}/article_img/${articleVO.boardWritePicture}" alt="main_image" class="main_img2">
						</div>
						
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="col-md-6" onclick="location.href='${rootPath}/Article_detail.do?boardWriteNum=${articleVO.boardWriteNum}'">
					<div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<div class="col p-4 d-flex flex-column position-static">
							<strong class="d-inline-block mb-2 text-success">실시간 인기 뉴스</strong>
							<h5 class="mb-0" >${articleVO.boardWriteTitle}</h5>
							<div class="mb-1 text-muted">${articleVO.boardInsertDate}</div>
							<p class="mb-auto">${articleVO.boardWriteArticle}</p>
							<!--  <a href="#" class="stretched-link">Continue reading</a>-->
						</div>
						<div class="col-auto d-none d-lg-block">
							<img src="${rootPath}/article_img/${articleVO.boardWritePicture}" alt="main_image" class="main_img2" >
						</div>
					</div>
				</div>
			</div>
			</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
	<!-- 4칸 끝  -->
	</c:if>
</div>
<!--  MAIN 컨테이너 -->
<main role="main" class="container" >
<div class="row" id="mainIndex2">
	<div class="col-md-8 blog-main">
		<img id="youtubeTitle" alt="오늘의 주요 뉴스" src="img/youtubeTitle.JPG">
		<div id="youtubeDiv" class="carousel slide" data-ride="carousel">
		  <ul class="carousel-indicators" id="youtubeUl">
		  </ul>
		
		  <!-- The slideshow -->
		  <div class="carousel-inner" id="youtubeItems">
		  </div>
		  <a class="carousel-control-prev" href="#youtubeDiv" data-slide="prev">
		    <span class="carousel-control-prev-icon"></span>
		  </a>
		  <a class="carousel-control-next" href="#youtubeDiv" data-slide="next">
		    <span class="carousel-control-next-icon"></span>
		  </a>
		</div>
	</div>
	<!-- /.blog-main -->

	<aside class="col-md-4 blog-sidebar">
	<img src="img/chatting.JPG" id="chatt_img">
		<div class="p-2 mb-3 bg-light rounded">
			<%@include file="Chatting.jsp" %>
		</div>

		<div class="p-4">
			<img src="img/realtime_search.JPG" id="realtime_search" alt="네이버 실시간 검색어"><br>
			<a onclick="rank1();" id="top10">•1~10위</a>&nbsp;
			<a onclick="rank2();" id="top20">•11~20위</a>
			<ol class="list-unstyled mb-0" id="srchrank">
				
			</ol>
		</div>

		<div class="p-4" style="margin-left:56px;">
			<img alt="환율" src="img/exchange_img.JPG" style="margin-left: 40px; margin-top: -12px;">
			<p id="exchangeTime"></p>
			<ol class="list-unstyled" id="exchangeOl">
				
			</ol>
		</div>
	</aside>
	<!-- /.blog-sidebar -->

</div>
<!-- /.row --> 
</main>
<!-- /.container -->
