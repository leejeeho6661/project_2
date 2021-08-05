<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="css/board_write.css" rel="stylesheet">
<script type="text/javascript" src="js/ckeditor/ckeditor.js"></script>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<jsp:include page="../Header.jsp"></jsp:include>
<div id="contentBody">
	<div id="contentWrap">
		<div class="mem_wrap">
			<div class="member_join_wrap">
				<!-- <h3>기사 등록</h3> -->
				<img src="img/free_board.JPG" alt="자유게시판">
				<form name="BoardWriteForm" method="post" action="write_Board.do" enctype="multipart/form-data">
					<table id="write_form" class="table_comm_row">
						<colgroup>
							<col width="140">
							<col width="180">
							<col width="140">
						</colgroup>

						<tbody>
							<c:if test="${pageContext.request.userPrincipal.name=='idontknownews'}">
							<tr>
								<th>카테고리</th>
								<td colspan="3">
								<select name="board_part"	id="boardWriteCategory">
										<option value="">선택</option>
										<option value="0">공지</option>
										<option value="1">일반</option>
								</select>
								</td>
							</tr>
							</c:if>
							<c:if test="${pageContext.request.userPrincipal.name !='idontknownews'}">
								<input type="hidden" name="board_part" value="1">
							</c:if>
							<tr>
								<th>제목</th>
								<td colspan="3"><input type="text" name="board_title" id="boardWriteTitle" class="m_input" >
								<!-- 작성자의 표시가 굳이 필요한가해서 여기에다가 히든으로 넣어봤습니다. -->
								<input type="hidden" name="board_name" value="${pageContext.request.userPrincipal.name}">
							</tr>

							<tr>
								<th>사진</th>
								<td colspan="3">
								<input type="file" name="board_file" id="boardWritePicture" class="m_input">
							</tr>
							<!-- 
							<tr>
								<th>작성자</th>
								<td colspan="3">
								<input type="hidden" name="board_name" id="boardWriteWriter" class="m_input" value="${pageContext.request.userPrincipal.name}">
								<span id="boardWriteWriterspan" class="t_11gr" >${pageContext.request.userPrincipal.name}</span></td>
							</tr>
 							-->
 							
							<tr>
								<th>내용</th>
								<td colspan="3">
								<textarea name="board_content" id="boardWriteArticle"></textarea>
								<script>CKEDITOR.replace('boardWriteArticle', {
									  height: 400
								});</script>
								</td>
								
							</tr>

							
						</tbody>
						
					</table>
					
					<div class="d-flex al_center mt20" id="registerArea">
						<!-- <input type="submit" value="미리보기" id="registerArea_2" class="btn btn-outline-secondary"> -->
						<input type="submit" value="등록"  class="btn btn-outline-secondary"/>
						<input type="reset" value="취소"  class="btn btn-outline-secondary"/>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../Footer.jsp"></jsp:include>