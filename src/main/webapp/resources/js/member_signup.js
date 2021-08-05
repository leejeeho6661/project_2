/**
 * 
 */

//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
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

$(document).ready(function() { 
	// 아이디 중복 점검 플래그
	var idCheckFlag = false;
	// 이메일 중복 점검 플래그
	var emailCheckFlag = false;
	// 연락처 중복 점검 플래그
	var phoneCheckFlag = false;
	// 닉네임 중복 점검 플래그
	var nicknameCheckFlag = false;
	
	// 아이디 중복 점검
    $("#Id").blur(function() {
    	
    	console.log("아이디 중복점검");
    	
    	// 정규식 점검으로 유효 데이터 전송
    	// var regexId = new RegExp('[a-zA-Z]{1}\w{7,19}');
    	// var regexId = new RegExp(/[a-zA-Z]{1}\w{7,19}/);
    	// var regexId = /[a-zA-Z]{1}\w{7,19}/;
    	var regexId = new RegExp($("#Id").attr("pattern"));
    	
    	var memberId = $("#Id").val();

    	if (regexId.test(memberId)) { // 정규식 점검 통과
	    		
    		$.ajax({
    			// 주의사항) 외장화할 경우 코드 단절현상으로 인해 문제 발생 -> 광역 javascript 변수로 패치
	       		// url : '${pageContext.request.contextPath}/member/idCheck.do',
    			url : '/project/member/idCheck.do',
                type : 'post',
                dataType:'text',
                data : {
                    Id : $('#Id').val()
                },
                success : function(data) {
                  
                   console.log("아이디 중복 점검 수신 !");

                   // 중복 점검 체크 플래그 재설정
                   if (data.trim() == '사용할 수 있는 아이디입니다.') {
                	   // 메시지 초기화 : 정상적일 경우는 메시지 표기 불필요할 경우
                	  // $("#memberId_err").text("");
                   		$("#checkMsgId").text("사용가능한 아이디입니다.")
                   		$('#checkMsgId').css({"color":"blue"});
                	    idCheckFlag = true;
                   } else {
                      // $("#memberId_err").text(data);
                       $("#checkMsgId").text("중복된 아이디입니다.")
                       $('#Id').val("");
                   	   $('#checkMsgId').css({"color":"red"});
                   	   $('#Id').focus();
                       idCheckFlag = false;
                   }
                                  	
	               // 플래그 인쇄
                   console.log("idCheckFlag : "+ idCheckFlag);
	                console.log("emailCheckFlag : "+ emailCheckFlag);
	                console.log("phoneCheckFlag : "+ phoneCheckFlag);
	                console.log("nicknameCheckFlag : "+ nicknameCheckFlag);
                   
               }, // success
                
                error : function(xhr, status) {
                   console.log(xhr+" : "+status); // 에러 코드
               }
	    
	       	}); // ajax
    		
    	} else { // 정규식 통과 실패
    		
    		// 에러 메시징
    		//$("#Id_err").text(($("#Id").attr("title")));
    		$("#checkMsgId").text("양식에 맞게 입력바랍니다.")
        	$('#checkMsgId').css({"color":"red"});
    		// 재입력 대기
    		$("#Id").focus();
    		
    	} // 
    	
    });
    
	// 연락처 중복 점검
    $("input[name=mobile3]").blur(function() {
    	
    	console.log("연락처 중복점검");
    	
    	// 정규식 점검으로 유효 데이터 전송
    	var regexPhone = new RegExp("01[0-9]{1}-[0-9]{3,4}-[0-9]{4}");
    	var memberPhone = $("select[name=mobile1]").val()+"-"+$("input[name=mobile2]").val()+"-"+$("input[name=mobile3]").val();
    	//alert(regexPhone.test(memberPhone));
    	if (regexPhone.test(memberPhone)) { // 정규식 점검 통과
    	
	    	$.ajax({
	    		 // url : '${pageContext.request.contextPath}/member/phoneCheck.do',
	    		 url : '/project/member/phoneCheck.do',
	             type : 'post',
	             dataType:'text',
	             data : {
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
 	                console.log("idCheckFlag : "+ idCheckFlag);
 	                console.log("emailCheckFlag : "+ emailCheckFlag);
 	                console.log("phoneCheckFlag : "+ phoneCheckFlag);
 	                console.log("nicknameCheckFlag : "+ nicknameCheckFlag);
	               
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
            $("select[name=mobile1]").focus();
    		
    	} //
    	
    });
    
 // 이메일 중복 점검
    $("#emailDnsList").blur(function() {
    	
    	console.log("이메일 중복점검");
    	
    	var frontEmail = $('#memEmail').val();
    	var backEmail = $('#emailDnsList').val();
    	var regexEmail = /[a-zA-Z0-9_+.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}/;
    	// 정규식 점검으로 유효 데이터 전송
    	var memberEmail = frontEmail+"@"+backEmail;
		/*alert(frontEmail+"@"+backEmail);
		alert(regexEmail.test(memberEmail));*/
    	if (regexEmail.test(memberEmail)) { // 정규식 점검 통과
    	
	    	$.ajax({
	    		 // url : '${pageContext.request.contextPath}/member/emailCheck.do',
	    		 url : '/project/member/emailCheck.do',
	             type : 'post',
	             dataType:'text',
	             data : {
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
                    console.log("idCheckFlag : "+ idCheckFlag);
 	                console.log("emailCheckFlag : "+ emailCheckFlag);
 	                console.log("phoneCheckFlag : "+ phoneCheckFlag);
 	                console.log("nicknameCheckFlag : "+ nicknameCheckFlag);
	               
	            }, // success
	             
	             error : function(xhr, status) {
	                console.log(xhr+" : "+status); // 에러 코드
	            }
	 
	    	}); // ajax
    
    	} else { // 정규식 점검 실패
    		  $("#checkmsgemail").text("형식에 맞게 작성해 주세요.");
    		  $("#checkmsgemail").css({"color":"red"});
    		  $('#memEmail')
    		// 에러 메시징
    		//$("#memberEmail_err").text(($("#memberEmail").attr("title")));
    		// 재입력 대기
    		$("#memEmail").focus();
    	} //
    	
    });
	
 	// 닉네임 중복점검
 	$("#memNickname").blur(function() {
    	
    	console.log("닉네임 중복점검");
    	
    	var Nickname = $('#memNickname').val();
    	var regexNickname = /[가-힣]{2,25}|[a-zA-Z]{2,50}|\s[a-zA-Z]{3,50}/;
    	// 정규식 점검으로 유효 데이터 전송
    	
    	if (regexNickname.test(Nickname)) { // 정규식 점검 통과
    	
	    	$.ajax({
	    		 // url : '${pageContext.request.contextPath}/member/emailCheck.do',
	    		 url : '/project/member/nicknameCheck.do',
	             type : 'post',
	             dataType:'text',
	             data : {
	            	 Nickname : $('#memNickname').val()
	             },
	             success : function(data) {
	               
	                console.log("닉네임 중복 점검 수신 !");
	                
	             	// 중복 점검 체크 플래그 재설정
                    if (data.trim() == '사용할 수 있는 닉네임입니다.') {
                	   // 메시지 초기화 : 정상적일 경우는 메시지 표기 불필요할 경우
                	   $("#checkNickname").text("사용가능한 닉네임입니다.");
                	   $("#checkNickname").css({"color":"blue"});
                	   nicknameCheckFlag = true;
                    } else {
                       $("#checkNickname").text("중복된 닉네임입니다.");
                       $('#memNickname').val("");
                       $('#memNickname').focus();
                       $("#checkNickname").css({"color":"red"});
                       nicknameCheckFlag = false;
                    }
	             	
                    // 플래그 인쇄
 	                console.log("idCheckFlag : "+ idCheckFlag);
 	                console.log("emailCheckFlag : "+ emailCheckFlag);
 	                console.log("phoneCheckFlag : "+ phoneCheckFlag);
 	                console.log("nicknameCheckFlag : "+ nicknameCheckFlag);
 	                
	               
	            }, // success
	             
	             error : function(xhr, status) {
	                console.log(xhr+" : "+status); // 에러 코드
	            }
	 
	    	}); // ajax
    
    	} else { // 정규식 점검 실패
    		
    		// 에러 메시징
	    	$("#checkNickname").text("형식에 맞게 작성해 주세요.");
    		$("#checkNickname").css({"color":"red"});
    		// 재입력 대기
    		$('#memNickname').focus();
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
				$("#Password").focus();
			}else{
				$('#checkMsgPass2').text("비밀번호가 일치합니다.");
				$('#checkMsgPass2').css({"color":"blue"});
			}
	})
	
	//생년월일 중 월과 일 점검
	$("input[name=birthdayM]").blur(function(){
		if($("input[name=birthdayM]").val()>13 || $("input[name=birthdayM]").val()<1){
			$("input[name=birthdayM]").val("");
			$("input[name=birthdayM]").focus();
			$("#birthMonthCheck").css({"color":"red"});
		}
		else
			$("#birthMonthCheck").css({"color":"black"});
	})
	
	$("input[name=birthdayD]").blur(function(){
		if($("input[name=birthdayD]").val()>32 || $("input[name=birthdayD]").val()<1){
			$("input[name=birthdayD]").val("");
			$("input[name=birthdayD]").focus();
			$("#birthDayCheck").css({"color":"red"});
		}
		else
			$("#birthDayCheck").css({"color":"black"});
	})
	
	//폼 전송 전 점검
	$("form[name=JoinForm]").submit(function(){
 		
 		/*alert("폼 전송"); 이거였군 .. 찾았다! ㅋㅋㅋㅋㅋ*/
 		
 		// 아이디/이메일/연락처 중복점검 플래그 점검
 		if (idCheckFlag == false || emailCheckFlag == false || phoneCheckFlag == false || nicknameCheckFlag == false) {
 			
 			if (idCheckFlag == false) {
 				alert("중복되지 않은 아이디를 입력하십시오.");
 				$("#Id").focus();
 			}
 			
 			if (emailCheckFlag == false) {
 				alert("중복되지 않는 이메일을 입력하십시오.");
 				$("#memberEmail").focus();
 			}
 			
 			if (phoneCheckFlag == false) {
 				alert("중복되지 않는 연락처를 입력하십시오.");
 				$("select[name=mobile1]").focus();
 			}
 			
 			if (nicknameCheckFlag == false) {
 				alert("중복되지 않는 닉네임 입력하십시오.");
 				$('#memNickname').focus();
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
