<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="../css/member_update.css" rel="stylesheet">
<script type="text/javascript" src="../js/member_update.js"></script>
<jsp:include page="../Header.jsp"></jsp:include>

<div class="container-fluid">
<div id="contentBody">
	<div id="contentWrap">
		<div class="mem_wrap">
			<h3 class="mem_tit_h1">
				<img src="../img/member_update.JPG" id="member_update" alt="회원정보수정">
			</h3>
			<div class="member_join_wrap">
				<h3 class="mem_tit_h3 mem_tit_b_none mt50">
					<img src="../img/sign_2.gif" alt="필수 정보 입력">
				</h3>
				<form name="JoinForm" method="post" action="updateProc.do">
					<table id="sign1_form" class="table_comm_row">
						<colgroup>
							<col width="140">
							<col width="180">
							<col width="140">
						</colgroup>

						<tbody>
							<tr>
								<th>아이디</th>
								<td colspan="3">
								<input type="text" id="Id" name="Id" class="m_input" size="25" value="${member.id}" readOnly>
								</td>
							</tr>

							<tr>
								<th>비밀번호</th>
								<td colspan="3">
								<input type="Password" id="Password" name="Password" class="m_input" size="25" 
								pattern="(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{8,20}" required maxlength="20" > &nbsp; 
								<span id="checkMsgPass1" class="t_11gr">공백없는 8~20자의 영문/숫자/하나 이상의 특수문자 조합</span></td>
							</tr>

							<tr>
								<th>비밀번호확인</th>
								<td colspan="3">
									<input type="Password" id="RePassword" name="RePassword" class="m_input" size="25" maxlength="15" > &nbsp; 
									<span id="checkMsgPass2" class="t_11gr">비밀번호를 한번 더 입력해주세요.</span>
								</td>
							</tr>

							<tr>
								<th>이름</th>
								<td><input type="text" name="Name" class="m_input" size="25" value="${member.name}" readOnly></td>
								<th>생년월일</th>
								<td>
								 <input type="text" size="4" name="birthdayY" class="m_input" value="${member.birthdayY}" readOnly> 년
								 <input type="text" size="4" name="birthdayM" class="m_input" value="${member.birthdayM}" readOnly> 월
								 <input type="text" size="4" name="birthdayD" class="m_input" value="${member.birthdayD}" readOnly> 일
								 </td>
							</tr>

							
							<tr>
								<th>성별</th>
								<td colspan="1">
								<input type="hidden" id="genderhidden" value="${member.sex}">
								<input type="radio" name="Sex" value="m" id="male">
								<label for="male">남</label>
								</td>
								<td colspan="2">
								<input type="radio" name="Sex" value="f" id="female">
								<label for="female">여</label>
								</td>
							</tr>

							<tr>
								<th>닉네임</th>
								<td colspan="3">
									<input type="text" name="memNickname" id="memNickname" class="m_input" size="25" value="${member.memNickname}" readOnly> &nbsp; 
								</td>


							</tr>

							<tr>
								<th>휴대폰 번호</th>
								<td colspan="3">
									<input type="text" name="mobile1" class="m_input" size="5" maxlength="4" value="${member.mobile1}"> - 
									<input type="text" name="mobile2" class="m_input" size="5" maxlength="4" value="${member.mobile2}"> - 
									<input type="text" name="mobile3" class="m_input" size="5" maxlength="4" value="${member.mobile3}">&nbsp;
									<span class="t_11gr" id="checkPhone" ></span>
								</td>
							</tr>

							<tr>
								<th>이메일</th>
								<td colspan="3">
									<p class="mb-0">
										<input type="text" id="memEmail" name="memEmail" class="m_input" size="25" value="${member.memEmail}">
									   @<input type="text" id="emailDnsList" name="emailDnsList" class="m_input" size="25" value="${member.emailDnsList}"> 
										<br> <br>
									</p>
									<p class=" t_11gr mb-0" id="checkmsgemail">아이디 찾기, 비밀번호 찾기 시 본인확인을 위해 정확히 입력해주세요.</p>
								</td>
							</tr>
						</tbody>
					</table>

					<!--선택 정보 입력 테이블-->
					<h3 class="mem_tit_h3 mem_tit_b_none mt50">
						<img src="../img/tit_h3_join_option.gif" alt="선택 정보 입력">
					</h3>

					<table class="table_comm_row">
						<tbody>
							<tr>
								<th>주소(배송지)</th>
								<td colspan="3">
									<input type="text" name="memberzip"	class="m_input" size="7" readonly id="memberzip" pattern="\d{5}" value="${member.memberzip}">
									<a onclick="searchPost()"class="btn_w_comm btype_a2" id="zipcode">우편번호찾기</a>
									<p class="mt5 fl_clear">
										<input type="text" name="memberAddressBasic" class="m_input" size="40"	maxlength="200" id="memberAddressBasic" pattern="[\w | \W | 가-힣 | / | - | (  |  ) | ,]{2,200}" value="${member.memberAddressBasic}"> 
										<input type="text" name="detailAddress" id="detailAddress" class="m_input" size="50" maxlength="100" pattern="[\w | \W | 가-힣 | / | -]{2,100}"  placeholder="상세 주소" value="${member.detailAddress}">
									</p> 
								</td>
							</tr>
							<tr>
								<th>관심분야</th>
								<td colspan="3">
									<div class="fl_clear">
										<c:forEach items="${member.categoryCode}" var="interest" varStatus="st">
											<input type="hidden" class="interesthidden" value="${interest}">
										</c:forEach>
										<span> 
											<input type="checkbox" name="categoryCode" id="사회" value="사회" class="chk">
											<label for="사회">사회</label>&nbsp;
										</span> 
										<span> 
											<input type="checkbox" name="categoryCode" id="정치" value="정치" class="chk">
											<label for="정치">정치</label>&nbsp;
										</span> 
										<span> 
											<input type="checkbox" name="categoryCode" id="경제/경영" value="경제/경영" class="chk">
											<label for="경제/경영">경제/경영</label>&nbsp;
										</span> 
										<span> 
											<input type="checkbox" name="categoryCode" id="IT/컴퓨터" value="IT/컴퓨터" class="chk">
											<label for="IT/컴퓨터">IT/컴퓨터</label>&nbsp;
										</span> 
										<span> 
											<input type="checkbox" name="categoryCode" id="스포츠" value="스포츠" class="chk">
											<label for="스포츠">스포츠</label>&nbsp;
										</span> 
										<span> 
										<input type="checkbox" name="categoryCode" id="연예" value="연예" class="chk">
										<label for="연예">연예</label>&nbsp;
										</span> 
										<span> 
											<input type="checkbox" name="categoryCode" id="해외" value="해외" class="chk">
											<label for="해외">해외</label>&nbsp;
										</span> 
										<span> 
											<input type="checkbox" name="categoryCode" id="의학/건강" value="의학/건강" class="chk">
											<label for="의학/건강">의학/건강</label>&nbsp;
										</span>
									</div>
								</td>
							</tr>


						</tbody>

					</table>
					<div class="al_center mt20" id="registerArea">
						<button type="submit" class="btn_bu_comm btype_a3">저장</button>
						<button type="button" class="btn_bu_cancle btype_a3" onclick="cancle();">취소</button>
						<button type="button" class="btn_bu_delete btype_a3" onclick="member_delete();">삭제</button>
					</div>
				</form>

				<br> <br> <br> <br> <br>
			</div>



		</div>

	</div>
</div>
</div>
<jsp:include page="../Footer.jsp"></jsp:include>