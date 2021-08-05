<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<link href="${rootPath}/css/article_list.css" rel="stylesheet">

<jsp:include page="../Header.jsp"></jsp:include>
<div class="containew-fluid">

	<div class="container" id="article_title">
	<!--  사회 -->
		<img src="${rootPath}/img/article_social.JPG" id="article_it_img" alt="사회">
	</div>
	<div class="container" id="article_content">
		<div class="article_board" id="article_board">
			<section class="box-type" id="box_main_list">
			<c:if test='${pageContext.request.userPrincipal.name=="idontknownews"}'>
				<div id="write_btn">
					<a class="btn btn-sm btn-outline-secondary" href="${rootPath}/Article_write.do">기사 등록</a>
				</div>
			</c:if>
			<c:if test="${not empty article_list || pageVO.listCount>0}">
				<c:forEach var="article" items="${article_list}" varStatus="st">
				<div class="item">
					<div class="item_number">
						<strong >${st.count + (pageVO.page-1)*10}</strong>
					</div>
					<figure class="img_box" >
						<a href="${rootPath}/Article_detail.do?boardWriteNum=${article.boardWriteNum}"  ><img src="${rootPath}/article_img/${article.boardWritePicture}"></a>
					</figure>
					<div class="news_box" id="news_a">
						<a href="${rootPath}/Article_detail.do?boardWriteNum=${article.boardWriteNum}"><strong class="news_title">${article.boardWriteTitle}</strong></a>
						<span class="txt-time">${article.boardInsertDate}</span>
					</div>
				</div>
				</c:forEach>
				<!--  페이징 -->
				<c:if test="${search_YN == 'search'}">
					<%@ include file="paging_search.jspf"%>
				</c:if>
				<c:if test="${search_YN != 'search'}">
					<%@ include file="paging.jspf"%>
				</c:if>
				
				</c:if>
			</section>
		</div>
			
			
		
			<!-- 등록글 없을 경우 -->
					<c:if test="${empty article_list || pageVO.listCount==0}">
						<div id="articleEmptyArea" class="text-center">등록된 글이 없습니다.</div>
					</c:if>

					<form method="post"
						action="${pageContext.request.contextPath}/article_listSearch.do"
						id="select_form">
						<div class="row">

							<input type="hidden" name="page" value="${pageVO.page}" /> <select
								id="search_kind" name="search_kind" class="shadow-sm">
								<option>제목</option>
								<option>내용</option>
							</select> 
							<input type="text" 
								   id="search_word" 
								   name="search_word"
								   class="article_text" 
								   placeholder="검색 ..." 
								   required="true"
								   title="검색어를 입력하십시오"> 
							<input type="submit" id="search_article_btn" class="btn_article_search" value="검색">
						</div>
					</form>
		
	</div>
</div>
<jsp:include page="../Footer.jsp"></jsp:include>