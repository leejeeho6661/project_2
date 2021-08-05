<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/ckeditor/ckeditor.js"></script>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<link href="${rootPath}/css/article_update.css" rel="stylesheet">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	// 내용 가져오기
	CKEDITOR.replace('boardWriteArticle', {height: 400});
	var articleContent = "${ArticleVO.boardWriteArticle}";
	CKEDITOR.instances.boardWriteArticle.setData(articleContent);
	$("#boardWriteCategory").val("${ArticleVO.boardWriteCategory }").prop("selected", true);
})
</script>
<jsp:include page="../Header.jsp"></jsp:include>

<div id="contentBody">
	<div id="contentWrap">
		<div class="mem_wrap">
			<div class="member_join_wrap">
				<!-- <h3>기사 등록</h3> -->
				<img src="${rootPath}/img/article_correction.PNG" alt="기사 수정" id="article_correction_img">
				<form name="BoardWriteForm" method="post" action="article_update.do?boardWriteNum=${ArticleVO.boardWriteNum}" enctype="multipart/form-data">
					<table id="write_form" class="table_comm_row">
						<colgroup>
							<col width="140">
							<col width="180">
							<col width="140">
						</colgroup>

						<tbody>
							<tr>
								<th>카테고리</th>
								<td colspan="3">
								<select name="boardWriteCategory"	id="boardWriteCategory">
										<option value="">선택</option>
										<option value="it">IT</option>
										<option value="economy">경제</option>
										<option value="society">사회</option>
										<option value="sport">스포츠</option>
								</select>
								</td>
							</tr>
							<tr>
								<th>제목</th>
								<td colspan="3"><input type="text" name="boardWriteTitle" id="boardWriteTitle" class="m_input" value="${ArticleVO.boardWriteTitle}" >
								<input type="hidden" name="boardWriteWriter" id="boardWriteWriter" class="m_input" value="${ArticleVO.boardWriteWriter}">
							</tr>

							<tr>
								<th>신규 사진</th>
								<td colspan="3">
								<input type="file" name="boardWritePicture" id="boardWritePicture" class="m_input" ><br>
								</td>
							</tr>
							<tr>
								<th>이전 사진</th>
								<td colspan="3">
								<img id="beforePicture" src="${rootPath}/article_img/${ArticleVO.boardWritePicture}">
							</tr>
							<!-- 
							<tr>
								<td colspan="4">
								<img src="${rootPath}/article_img/${ArticleVO.boardWritePicture}">
								</td>
							</tr>
							 -->
								

							<tr>
								<th>내용</th>
								<td colspan="3">
								<textarea name="boardWriteArticle" id="boardWriteArticle"></textarea>
								</td>
								
							</tr>

							
						</tbody>
					</table>
					
					<div class="d-flex al_center mt20" id="registerArea">
						<!-- <input type="submit" value="미리보기" id="registerArea_2" class="btn btn-outline-secondary"> -->
						<input type="submit" value="등록"  class="btn btn-outline-secondary"/>
						<input type="reset" value="취소"  class="btn btn-outline-secondary" onclick="history.go(-1);"/>
					</div>
				
				</form>


				
				<br> <br> <br> <br> <br>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../Footer.jsp"></jsp:include>