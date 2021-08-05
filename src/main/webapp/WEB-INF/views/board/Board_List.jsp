<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${rootPath}/css/board_list.css" >

<%@ include file="../Header.jsp" %>
<div class="sub_content container">
	<div class="content">
		<div class="board_zone_sec">
			<div class="board_zone_tit">
				<img src="${rootPath}/img/free_board.JPG" id="free_board" alt="자유게시판">
			</div>
			<c:if test="${not empty pageContext.request.userPrincipal.name}">
			<div id="board_write_button">
				<button class="btn_board_search" onclick="location.href='${rootPath}/board_write_view.do'">글쓰기</button>
			</div>
			</c:if>
			<div class="board_zone_cont">
				<div class="board_zone_list" align="center">
					<c:if test="${not empty board_list && pageVO.listCount > 0}">
						<table class="board_list_table" style="width: 100%">
							<colgroup>
								<col style="width: 10%">
								<col style="width: 33%;">
								<col style="width: 18%">
								<col style="width: 12%">
								<col style="width: 10%">
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>날짜</th>
									<th>작성자</th>
									<th>조회</th>
								</tr>
							</thead>

							<tbody>

								<c:forEach var="board" items="${board_list}" varStatus="st">
								<input type="hidden" id="part" value="${board.board_part}">
								<input type="hidden" id="list_comment_num" value="${board.board_num}">
										<c:choose>
											<c:when test="${board.board_part == 0}">
											<tr data-sno="${board.board_num}" class="board_row">
												<td><strong class="board_notice">공지</strong></td>
												<td class="board_tit">
													<a href="${rootPath}/board_detail.do?board_num=${board.board_num}" id="boardNum_${board.board_num}"> 
														<strong class="board_notice">${board.board_title}</strong>
														<span class="comment_count">(${board.recnt})</span>
													</a>
												</td>
											</c:when>
											<c:otherwise>
											<tr data-sno="${board.board_num}">
												<td>${st.count + (pageVO.page-1)*10}</td>
												<td class="board_tit">
												<a href="${rootPath}/board_detail.do?board_num=${board.board_num}" id="boardNum_${board.board_num}">${board.board_title}
												<span class="comment_count">(${board.recnt})</span>
												</a></td>
											</c:otherwise>
										</c:choose>
										<td><fmt:formatDate value="${board.board_date}"
												pattern="yyyy.M.d HH:mm" /></td>
										<td>${board.board_name}</td>
										<td id="boardReadCount_${board.board_num}">
											${board.board_readcount}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>

						<!--  페이징 -->
						<c:if test="${search_YN == 'search'}">
							<%@ include file="paging_search.jspf"%>
						</c:if>
						<c:if test="${search_YN != 'search'}">
							<%@ include file="paging.jspf"%>
						</c:if>

						<!-- //pagination -->
					</c:if>

					<!-- 등록글 없을 경우 -->
					<c:if test="${empty board_list || pageVO.listCount==0}">
						<div id="boardEmptyArea">등록된 글이 없습니다.</div>
					</c:if>

					<form method="post"
						action="${pageContext.request.contextPath}/boardListbySearch.do"
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
								   class="board_text" 
								   placeholder="검색 ..." 
								   required="true"
								   title="검색어를 입력하십시오"> 
							<input type="submit" id="search_board_btn" class="btn_board_search" value="검색">
						</div>
					</form>
				<!-- 검색 끝  -->
					<!-- //board_search_box -->
				</div>
				<!-- //board_zone_list -->
			</div>
			<!-- //board_zone_cont -->
		</div>
	</div>
</div>
<%@ include file="../Footer.jsp" %>