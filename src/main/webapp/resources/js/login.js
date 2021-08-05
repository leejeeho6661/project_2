$(document).ready(function() {
		
		 // 필드 공백 제거
		 $("#username").val().replace(/\s/g, "");
		 $("#password").val().replace(/\s/g, "");
		 
		 $("#login_button").click(function() {
			
			// 공백 여부 점검
			if ($.trim($("#username").val()) == "" || 
				$.trim($("#password").val()) == "")   {
				
				alert("공백이 입력되었습니다. 다시 입력하십시오.");
				$("#username").val("");
				$("#password").val("");
				
			} else {
				
				
				$.ajax({
		    		url : "/project/login/idCheck",
		    	    type: "get",
		    	    dataType: "text",
		        	data : {
		        		username : $("#username").val()
		        	},
		        	success : function(data) {
		        		alert(data);
		        		if (data.trim() == "true") {
			       			 document.loginForm.submit();
			           	} else {
		        			$("#username").focus();
		        			location.href="login";
			           	}
			        },
			       /* error : function(xhr, status){
			        	console.log(xhr+" : "+status);
			        	alert($("${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}"));
			        	location.href="login";
			        }*/
			        
		        	
		    	}); // $.ajax
		    	
		    	
			} // if
	    	
	    }) // login
	    
	}) //