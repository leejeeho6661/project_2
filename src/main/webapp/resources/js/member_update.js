/**
 * 
 */
function cancle(){
	if (confirm("정말 취소하시겠습니까??") == true){    //확인
	    history.back();
	}else{   //취소
	    return;
	}
}

function member_delete(){
	var input = prompt("삭제하시겠습니까?\n삭제를 원하신다면 '삭제하겠습니다' 를 입력하세요");
	if (input=="삭제하겠습니다"){    //확인
		location.href = "/project/member/deleteProc.do?Id="+$("#Id").val();
	}else{   //취소
		alert("삭제를 취소하였습니다.")
	    return;
	}
}
$(function(){
	var gender = $.trim($("#genderhidden").val());
		$("input:radio[name='Sex']:radio[value='"+gender+"']").prop('checked', true); // 선택하기
	
		$(".interesthidden").each(function(){
			$("input:checkbox[value='"+this.value+"']").prop('checked', true);
		})
	// 변경 가능 
		// 이메일 중복 점검 플래그
		var emailCheckFlag = false;
		// 연락처 중복 점검 플래그
		var phoneCheckFlag = false;
		
		//연락처 중복점검
		$("input[name=mobile3]").blur(function() {
	    	
	    	console.log("연락처 중복점검");
	    	
	    	// 정규식 점검으로 유효 데이터 전송
	    	var regexPhone = new RegExp("01[0-9]{1}-[0-9]{3,4}-[0-9]{4}");
	    	var memberPhone = $("input[name=mobile1]").val()+"-"+$("input[name=mobile2]").val()+"-"+$("input[name=mobile3]").val();
			alert($('#Id').val());
	    	if (regexPhone.test(memberPhone)) { // 정규식 점검 통과
	    	
		    	$.ajax({
		    		 // url : '${pageContext.request.contextPath}/member/phoneCheck.do',
		    		 url : rootPath + '/member/phoneCheckUpdate.do',
		             type : 'post',
		             dataType:'text',
		             data : {
		            	 Id : $('#Id').val(),
		                 memberPhone : memberPhone
		             },
		             success : function(data) {
		               
		                console.log("연락처 중복 점검 수신 !");
		                
		            	// 중복 점검 체크 플래그 재설정
	                    if (data.trim() == '사용할 수 있는 연락처입니다.') {
	                	   // 메시지 초기화 : 정상적일 경우는 메시지 표기 불필요할 경우
	                	   $("#checkPhone").text("사용 가능한 연락처입니다.");
	                	   $("#checkPhone").css({"color":"blue"});
	                	   phoneCheckFlag = true;
	                    } else {
	                       $("#checkPhone").text("중복된 연락처입니다.");
	                 	   $("#checkPhone").css({"color":"red"});
	                 	   $("select[name=mobile1]").val("");
	                 	   $("input[name=mobile2]").val("");
	                       $("input[name=mobile3]").val("");
	                       $("select[name=mobile1]").focus();
	                       phoneCheckFlag = false;
	                    }
		            	
	                 	// 플래그 인쇄
	 	                console.log("emailCheckFlag : "+ emailCheckFlag);
	 	                console.log("phoneCheckFlag : "+ phoneCheckFlag);
		               
		            }, // success
		             
		             error : function(xhr, status) {
		                console.log(xhr+" : "+status); // 에러 코드
		            } 
		 
		    	});
	    	
	    	} else { // 정규식 점검 실패
	    		
	    		// 에러 메시징
	    		//$("#memberPhone_err").text(($("#memberPhone").attr("title")));
	    		// 재입력 대기
	    		$("#checkPhone").text("양식에 맞게 입력해주세요.");
	            $("#checkPhone").css({"color":"red"});
	    		$("#checkPhone").focus();
	    		
	    	} //
	    	
	    });
	    
	 // 이메일 중복 점검
	    $("#emailDnsList").blur(function() {
	    	
	    	console.log("이메일 중복점검");
	    	
	    	var frontEmail = $('#memEmail').val();
	    	var backEmail = $('#emailDnsList').val();
	    	var regexEmail = new RegExp("[a-zA-Z0-9_+.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}");
	    	// 정규식 점검으로 유효 데이터 전송
	    	var memberEmail = frontEmail+"@"+backEmail;
			alert(frontEmail+"@"+backEmail);
	    	if (regexEmail.test(memberEmail)) { // 정규식 점검 통과
	    	
		    	$.ajax({
		    		 // url : '${pageContext.request.contextPath}/member/emailCheck.do',
		    		 url : rootPath + '/member/emailCheckUpdate.do',
		             type : 'post',
		             dataType:'text',
		             data : {
		            	 Id : $('#Id').val(),
		            	 memberEmail : $('#memEmail').val()+'@'+$('#emailDnsList').val()
		                 
		             },
		             success : function(data) {
		               
		                console.log("이메일 중복 점검 수신 !");
		                
		             	// 중복 점검 체크 플래그 재설정
	                    if (data.trim() == '사용할 수 있는 이메일입니다.') {
	                	   // 메시지 초기화 : 정상적일 경우는 메시지 표기 불필요할 경우
	                	   $("#checkmsgemail").text("사용가능한 이메일입니다.");
	                	   $("#checkmsgemail").css({"color":"blue"});
	                	   emailCheckFlag = true;
	                    } else {
	                       $("#checkmsgemail").text("중복된 이메일입니다.");
	                       $('#memEmail').val("");
	                       $("#emailDnsList").val("");
	                       $("select[name=emailGb]").val("self");
	                       $('#memEmail').focus();
	                       $("#checkmsgemail").css({"color":"red"});
	                       emailCheckFlag = false;
	                    }
		             	
	                    // 플래그 인쇄
	 	                console.log("emailCheckFlag : "+ emailCheckFlag);
	 	                console.log("phoneCheckFlag : "+ phoneCheckFlag);
		               
		            }, // success
		             
		             error : function(xhr, status) {
		                console.log(xhr+" : "+status); // 에러 코드
		            }
		 
		    	}); // ajax
	    
	    	} else { // 정규식 점검 실패
	    		  $("#checkmsgemail").text("형식에 맞게 작성해 주세요.");
	    		  $("#checkmsgemail").css({"color":"red"});
	    		// 에러 메시징
	    		//$("#memberEmail_err").text(($("#memberEmail").attr("title")));
	    		// 재입력 대기
	    		$("#memberEmail").focus();
	    	} //
	    	
	    });
	 
	 // 비밀번호 일치 체크 
		$('#RePassword').blur(function(){
				
				console.log("비밀번호 일치점검");
				if($("#Password").val() != $('#RePassword').val()){
					
					$('#checkMsgPass2').text("비밀번호가 일치하지 않습니다.")
					$('#checkMsgPass2').css({"color":"red"});
					alert("비밀번호가 일치하지 않습니다.");
					$('#checkMsgPass2').val("");
				}else{
					$('#checkMsgPass2').text("비밀번호가 일치합니다.");
					$('#checkMsgPass2').css({"color":"blue"});
				}
		})
		
		
		//폼 전송 전 점검
		$("form[name=JoinForm]").submit(function(){
	 		
	 		alert("폼 전송");
	 		
	 		// 아이디/이메일/연락처 중복점검 플래그 점검
	 		if (emailCheckFlag == false || phoneCheckFlag == false) {
	 			
	 			
	 			if (emailCheckFlag == false) {
	 				alert("중복되지 않는 이메일을 입력하십시오.");
	 				$("#memberEmail").focus();
	 			}
	 			
	 			if (phoneCheckFlag == false) {
	 				alert("중복되지 않는 연락처를 입력하십시오.");
	 				$("select[name=mobile1]").focus();
	 			}
	 			
	 			return false; // 전송금지
	 		}
	 		
	 		// 우편번호/주소/상세주소 점검
	 		// 주소 성분이 필수 사항이 아닐지라도 입력할 경우 상세주소 누락되는지 최종적으로 점검
	 		var regexAddressDetail = new RegExp($("#detailAddress").attr("pattern"));
	    	var memberAddressDetail = $("#detailAddress").val();
	    	
	    	//alert("우편번호 : "+$("#memberZip").val());
	    	//alert("기본주소 : "+$("#memberAddressBasic").val());
	    	//alert("상세주소 : "+$("#memberAddressDetail").val());
	 	    	
	 		if ($("#memberzip").val() != "" &&
				$("#memberAddressBasic").val() != "" &&
				!regexAddressDetail.test(memberAddressDetail) ) 
	 		{
	    		alert("상세주소를 입력하십시오.");
				return false; // 전송 금지
	    		$("#detailAddress").focus();
	 		} // if
	 		
	 		// TODO
	 		// 우편번호/기본주소가 비었는데 상세주소에 데이터가 입력되었을 때
	 		// 1) 상세주소 정규식 점검 -> 오류 : 비움(초기화)
	 		// 2) 전송 금지
	 		// 3) 메시징 : 기본 주소를 검색하여 입력하십시오.
	 		// 4) focus : 검색 버튼
	 		
	 		// alert($("#memberAddressDetail").val().trim())
	 		
	 		if ($("#memberzip").val() == "" &&
				$("#memberAddressBasic").val() == "" &&
				$("#detailAddress").val().trim() != "" ) 
	 		{
	 			alert("우편번호와 기본 주소를 입력하십시오."); 
	 			return false; // 전송 금지    	 							
	    		$("#zipcode").focus();
	 		} // if
	 		
	 	});
})

	// 우편번호
	function searchPost() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var roadAddr = data.roadAddress; // 도로명 주소 변수
	            var extraRoadAddr = ''; // 참고 항목 변수
	
	            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                extraRoadAddr += data.bname;
	            }
	            // 건물명이 있고, 공동주택일 경우 추가한다.
	            if(data.buildingName !== '' && data.apartment === 'Y'){
	               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	            }
	            
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('memberzip').value = data.zonecode;
	            document.getElementById("memberAddressBasic").value = roadAddr;
	            document.getElementById("detailAddress").focus();
	            
	            
	        }
	    }).open();
	}