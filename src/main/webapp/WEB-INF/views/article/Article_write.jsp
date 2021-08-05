<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="css/article_write.css" rel="stylesheet">
<script type="text/javascript" src="js/ckeditor/ckeditor.js"></script>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
var prePhoto;
$(function(){
	function readURL(input) {
		 if (input.files && input.files[0]) {
		  var reader = new FileReader();
		  reader.onload = function (e) {
		  prePhoto = e.target.result;
		  }
 	   }
	}
	$("#boardWritePicture").change(function(){
		readURL(this);
		});
})

function preview(){
	var popup_option="width=500m height=500,top=100,left=200,location=no";
	var gsWin = window.open("Preview.do","winName",popup_option);
	console.log(document.all.BoardWriteForm);
	
	gsWin.document.all.winName.boardWriteTitle.value = document.getElementById('boardWriteTitle').value;
}
</script>
<jsp:include page="../Header.jsp"></jsp:include>

<div id="contentBody">
	<div id="contentWrap">
		<div class="mem_wrap">
			<div class="member_join_wrap">
				<!-- <h3>기사 수정</h3> -->
				<img src="${rootPath}/img/article_upload.JPG" alt="기사 등록">
				<form name="BoardWriteForm" action="article.do" method="post" enctype="multipart/form-data">
					<input type="hidden" name="prePicture">
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
								<td colspan="3"><input type="text" name="boardWriteTitle" id="boardWriteTitle" class="m_input" >
							</tr>

							<tr>
								<th>사진</th>
								<td colspan="3">
								<input type="file" name="boardWritePicture" id="boardWritePicture" class="m_input">
							</tr>

							<tr>
								<th>작성자</th>
								<td colspan="3">
								<input type="hidden" name="boardWriteWriter" id="boardWriteWriter" class="m_input" value="${pageContext.request.userPrincipal.name}">
								<span id="boardWriteWriterspan" class="t_11gr" >${pageContext.request.userPrincipal.name}</span></td>
							</tr>

							<tr>
								<th>내용</th>
								<td colspan="3">
								<textarea name="boardWriteArticle" id="boardWriteArticle"></textarea>
								<script>CKEDITOR.replace('boardWriteArticle', {
									  height: 400
								});</script>
								</td>
								
							</tr>

							
						</tbody>
					</table>
					
					<div class="d-flex al_center mt20" id="registerArea">
						<!-- <input type="button" value="미리보기" id="registerArea_2" class="btn btn-outline-secondary" onclick='preview()'> -->
						<input type="submit" value="등록"  class="btn btn-outline-secondary"/>
						<input type="reset" value="취소"  class="btn btn-outline-secondary"/>
					</div>
				
				</form>


				
				<br> <br> <br> <br> <br>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../Footer.jsp"></jsp:include>