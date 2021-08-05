<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${rootPath}/css/board_detail.css" >
<script type="text/javascript" src="${rootPath}/js/board_detail.js"></script>

<%@ include file="../Header.jsp" %>
<div class="container">
	
	<div class="board_zone_tit">
	<c:choose>
		<c:when test="${boardVO.board_part==1}">
			<img src="${rootPath}/img/free_board.JPG" id="free_board" alt="자유게시판">
		</c:when>
		<c:otherwise>
			<img src="${rootPath}/img/big_board_notice.JPG" id="free_board" alt="공지사항">
		</c:otherwise>
	</c:choose>
	</div>


	<div id="board_detail_header">
		<input type="hidden" id="board_num" value="${boardVO.board_num}">
		<h1 id="board_detail_header_title">${boardVO.board_title}</h1>
	</div>
	<div id="board_detail_etc">
		<span id="board_detail_writer"><strong>${boardVO.board_name}</strong></span>
		<span id="board_detail_time" ><em> <fmt:formatDate value="${boardVO.board_date}" pattern="yyyy.M.d HH:mm:ss" /></em></span>
		<span id="board_detail_hit"><strong>조회수</strong> : ${boardVO.board_readcount}</span>
	</div>
	
	<div id="board_detail_article">
		<div id="board_detail">
			${boardVO.board_content}
		</div>
		<c:if test="${not empty boardVO.board_file}">
		<img alt="기사 사진" src="${rootPath}/board_img/${boardVO.board_file}" id="article_img">
		</c:if>
	</div>
	
	<div id="board_detail_buttons">
		<c:if test="${boardVO.board_name == pageContext.request.userPrincipal.name }">
		<button id="board_detail_modify" class="board_detail_button btn btn-outline-secondary" onclick="location.href='${rootPath}/board_update_view.do?board_num=${boardVO.board_num}'">수정</button>
		<button id="board_detail_delete" class="board_detail_button btn btn-outline-secondary" onclick="board_delete();">삭제</button>
		</c:if>
		<button id="board_detail_list" class="board_detail_button btn btn-outline-secondary" onclick="location.href='${rootPath}/boardList.do/1'">목록</button>
	</div>
	
	  <!--  댓글  -->
    <div class="container" id="comment_line">
        <label for="content"><strong>댓글</strong></label> 
        <span id="length_count">현재 <span id="textlimit">0</span> /최대 500</span>
        <form name="commentInsertForm">
            <div class="input-group">
               <input type="hidden" name="comment_ano" value="${boardVO.board_num}"/>
               <input type="hidden" name="comment_write" value="${pageContext.request.userPrincipal.name}"/>
               <textarea rows="3" cols="8" class="form-control"  onkeyup="updateChar(500);" id="comment_textarea" name="comment_textarea" placeholder="저작권 등 다른 사람의 권리를 침해하거나  명예를 훼손하는 게시물은 이용약관 및 관련 법률에 의해 제재를 받을수있습니다."></textarea>
               <span class="input-group-btn">
                    <button class="btn btn-default" type="button" onclick="commentInsertButton();" id="comment_button">등록</button>
               </span>
              </div>
             
        </form>
    </div>
    
    <div class="container">
        <div class="commentList"></div>
    </div>


</div>
<%@ include file="../Footer.jsp" %>
<%-- <jsp:include page="../Footer.jsp"></jsp:include> --%>