$(function() {
	var date = new Date();

	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDay(); //요일
	var date = date.getDate(); //일수

	if (day == 0)
		day = "Sun";
	else if (day == 1)
		day = "Mon";
	else if (day == 2)
		day = "Thes";
	else if (day == 3)
		day = "Wednes";
	else if (day == 4)
		day = "Thur";
	else if (day == 5)
		day = "Fri";
	else
		day = "Satur";

	switch (month) {
	case 1:
		month = "Jan"
		break;
	case 2:
		month = "Feb"
		break;
	case 3:
		month = "Mar"
		break;
	case 4:
		month = "Apr"
		break;
	case 5:
		month = "May"
		break;
	case 6:
		month = "Jun"
		break;
	case 7:
		month = "Jul"
		break;
	case 8:
		month = "Aug"
		break;
	case 9:
		month = "Sep"
		break;
	case 10:
		month = "Oct"
		break;
	case 11:
		month = "Nov"
		break;
	case 12:
		month = "Dec"
		break;
	}

	document.getElementById("time").innerHTML = day + "day, " + month + " "
			+ date + ", " + year;

	var popOption = "width=470px, height=430px, resizable=no, location=no, top=280px, left=740px;"
	    
	    $("#popup_quiz").click(function(e) {
	    	window.open("/project/Quiz.do","Quiz",popOption); 
	    })
	
	var sideslide = $(".sky_scrapper");
	var cont = $(".cont");
	// 페이지 열고
	$(".menu").on("click", "p[id=btn_toggle]" , function(){              
		sideslide.animate({
		    width: "304px",
		  }, 'fast');
		$('#back_icon').html("arrow_forward");
		$("#btn_toggle").attr('id','btn_open');
	    }); 
	 
	 // 닫고
	 $(".menu").on("click", "p[id=btn_open]" , function(){              
		sideslide.animate({
				    width: "44px",
				  }, 'fast');
		$(".menu").css({"z-index":"99999"})
		$('#back_icon').html("arrow_back");
		$("#btn_open").attr('id','btn_toggle');
	    }); 
	 
	 //나중에 함수로 바꿔서 코드 간략화 시키기
	 var text='<div class="sideNotice">';
	 text +='<img id="board_notice_img" src="/project/img/small_board_notice.JPG" alt="공지사항">';
	 var count=1;
	 $.ajax({
			url : "/project/SelectNotice.do",
			type : "get",
			success : function(data){
				if(data != null){
					$.each(data, function(key, value){
						text+='<div class="noticeTitle'+value.board_num+'">';
						text+='<a class="noticeA text-muted" href="/project/board_detail.do?board_num='+value.board_num+'"><p>'+count+'. '+value.board_title+'</p></a>';
						text+='</div>';
						count+=1;
					});
				}else{
					text+="<p>공지사항이 없습니다.</p>"
				}
				text+='</div>';
			    cont.html(text);
			}
		});
	 
	 //공지사항
	 $(".menu").on("click","#side_icon1", function(){
		 var text='<div class="sideNotice">';
		 text +='<img id="board_notice_img" src="/project/img/small_board_notice.JPG" alt="공지사항">';
		 var count=1;
		 $.ajax({
				url : "/project/SelectNotice.do",
				type : "get",
				success : function(data){
					if(data != null){
						$.each(data, function(key, value){
							text+='<div class="noticeTitle'+value.board_num+'">';
							text+='<a class="noticeA text-muted" href="/project/board_detail.do?board_num='+value.board_num+'"><p>'+count+'. '+value.board_title+'</p></a>';
							text+='</div>';
							count+=1;
						});
					}else{
						text+="<p>공지사항이 없습니다.</p>"
					}
					text+='</div>';
				    cont.html(text);
				}
			});
		 
	 });
	 
	//내가 읽은 기사
	 $(".menu").on("click","#side_icon2", function(){
		 console.log($("#login_id").val());
		 if($("#login_id").val() != null){
			 var text='<div class="sideReadArticle">';
			 text +='<img id="sideReadArticleImg" src="/project/img/ReadArticle.JPG" alt="내가 본 기사">';
			 $.ajax({
				url:"/project/readArticle.do",
				data:{'userId':$("#login_id").val()},
				type:"get",
				success:function(data){
					if(data != null){
						$.each(data, function(key, value){
							console.log(value.boardWriteNum);
							text+='<a href="/project/Article_detail.do?boardWriteNum='+value.boardWriteNum+'">';
							text+='<img src="/project/article_img/'+value.boardWritePicture+'" alt="기사 사진">';
							text+='<p class="text-muted">'+value.boardWriteTitle+'</p></a>';
						});
						text+='</div>';
					    cont.html(text);
					}
					else{
						console.log("본 기사가 없습니다.");
						text+="<p>본 기사가 없습니다.</p>"
						text+='</div>';
					    cont.html(text);
					}
				
				},
				error : function(xhr, status) {
		            console.log(xhr+" : "+status); // 에러 코드
		        } 
			 });
		 }
		 else{
			 alert("로그인후 이용하기시 바랍니다.");
		 }
	 });
	 
	 //내가 좋아요 누른 기사
	 $(".menu").on("click","#side_icon3", function(){
		 console.log($("#login_id").val());
		 if($("#login_id").val() != null){
			 var text='<div class="sideMyArticle">';
			 text +='<img id="sideMyArticleImg" src="/project/img/MyArticle.JPG" alt="좋아요 한 기사">';
			 $.ajax({
				url:"/project/myArticle.do",
				data:{'userId':$("#login_id").val()},
				type:"get",
				success:function(data){
					if(data != null){
						$.each(data, function(key, value){
							text+='<a href="/project/Article_detail.do?boardWriteNum='+value.boardWriteNum+'">';
							text+='<img src="/project/article_img/'+value.boardWritePicture+'" alt="기사 사진">';
							text+='<p class="text-muted">'+value.boardWriteTitle+'</p></a>';
						});
						text+='</div>';
					    cont.html(text);
					}
					else{
						console.log("좋아요한 기사가 없습니다.");
						text+="<p>좋아요한 기사가 없습니다.</p>"
						text+='</div>';
					    cont.html(text);
					}
				
				},
				error : function(xhr, status) {
		            console.log(xhr+" : "+status); // 에러 코드
		        } 
			 });
		 }
		 else{
			 alert("로그인후 이용하기시 바랍니다.");
		 }
	 });
});


//날씨 api
//지역 좌표
var region = [[60,127],[61,126],[98,76],[99,75],[89,90],
	 	 	 [54,125],[60,74],[67,101],[102,84],[66,103],
	  		 [60,120],[58,123],[62,121],[73,134],[69,107],
	  		 [68,100],[63,89],[51,67],[89,91],[91,77],
	  		 [52,38],[144,123]];
//지역 명
var kor_zone = ["서울","강남구","부산","해운대","대구",
				"인천","광주","대전","울산","세종특별시",
				"경기도","목감","풍덕천","강원도","충청북도",
				"충청남도","전라북도","전라남도","경상북도","경상남도",
				"제주도","독도"];
//루프를 돌기 위한 인덱스 번호
var regionIndex=0;
function getWeather(){
	
	$.ajax({
		url : '/project/weather2.do',
		type: 'get',
		data:{
			"x" : region[regionIndex][0],
			"y" : region[regionIndex][1]
		},
		dataType: 'json',
		success : function(data){
			
			var obj = JSON.parse(data);
			//강수 형태
			var pty;
			//하늘 형태
			var sky;
			//최저 기온
			var tmn;
			//최고 기온
			var tmx;

			$.each(obj.response.body.items.item,function(key,value){
				
				if(value.category=="PTY")
					pty = value.fcstValue;
				else if(value.category=="SKY")
					sky = value.fcstValue;
				else if(value.category=="TMN")
					tmn = value.fcstValue.substring(0,2);
				else if(value.category=="TMX")
					tmx = value.fcstValue.substring(0,2);
			});
			if(pty==0){
				if(sky==1)
					$("#weather_img").attr("src","/project/img/sun_weather.JPG");
				else if(sky==2)
					$("#weather_img").attr("src","/project/img/minclould_weather.JPG");
				else
					$("#weather_img").attr("src","/project/img/clould_weather.JPG");
			}
			else if(pty==1)
				$("#weather_img").attr("src","/project/img/rain_weather.JPG");
			else if(pty==2)
				$("#weather_img").attr("src","/project/img/rainsnow_weather.JPG");
			else if(pty==3)
				$("#weather_img").attr("src","/project/img/snow_weather.JPG");
			
			$("#weather_location").html("<strong>"+kor_zone[regionIndex]+"</strong>");
			$("#weather_temp").html(tmn+"°C / "+tmx+"°C");
		},
		error : function(xhr, status){
			console.log(xhr+ ":" +status);
		}
	});
	
	regionIndex++;
	if(regionIndex>=region.length)
		regionIndex=0;
}
getWeather();
//1500 단위로 바뀌게 하는 jquery함수
setInterval(getWeather,1500);

function checkForm(){
	
	var agree = document.getElementsByName("agree");
	for(var i=0; i<agree.length; i++){
		if(agree[i].checked== false){
			alert("약관 사항을 확인해주세요.");
			agree[i].focus();				
			return false;
		
		}
	}
}
