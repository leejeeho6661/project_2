/**
 * naver.com
 */
$(function(){
	$("#naver_search_result").hide();
	$("#naver_search_container").hide();
	slideShow();
	newsYoutube();
})
function naver_search(){
		$("#naver_search_result").empty();
		var need = $("#need").val();
		var search = '<h3 class="text-center">\''+need+'\'에 대한 검색 결과</h3>';
		$.ajax({
			url:"naver.do",
			type:"get",
			data:{"search":need},
			dataType:"json",
			success:function(data){
				$("#naver_search_result").append(search);
				var items = data.items;
				for(var i=0;i<items.length;i++){
					var title=items[i].title;
					var originlink = items[i].originallink;
					var link = items[i].link;
					var desc = items[i].description.substring(0,60)+"...";
					var year = items[i].pubDate.split(" ")[3];
					var month =items[i].pubDate.split(" ")[2];
					var day = items[i].pubDate.split(" ")[1];
					var time = items[i].pubDate.split(" ")[4].substring(0,5);
					switch (month) {
					case "Jan":
						month = '01';
						break;
					case "Feb":
						month = '02';
						break;
					case "Mar":
						month = '03';
						break;
					case "Apr":
						month = '04'; 
						break;
					case "May":
						month = '05'; 
						break;
					case "Jun":
						month = '06'; 
						break;
					case "Jul":
						month = '07'; 
						break;
					case "Aug":
						month = '08'; 
						break;
					case "Sep":
						month = '09'; 
						break;
					case "Oct":
						month = '10'; 
						break;
					case "Nov":
						month = '11'; 
						break;
					case "Dec":
						month = '12'; 
						break;
					}
					var result = 
						'<div class="result_box">'+
							'<a target="_blank" class="result_box_a" href="'+link+'">'+
								'<span class="caption_layer">'+
									'<strong class="result_box_caption">'+title+'</strong>'+
								'</span>'+
							'</a>'+
							'<p class="result_box_time">'+desc+'<br>'+'등록시간  |  '+year+'-'+month+'-'+day+' '+time+'</p>'+
						'</div>';
				$("#naver_search_result").append(result);
				}  // for문 끝 !!! 
				$(".result_box_a").css({"text-decoration":"none","color":"black","font-size":"20px"});
				$(".result_box_time").css({"color":"gray","margin-left":"10px"});
				$("#naver_search_container").show();
				$("#naver_search_result").show();
			
			}
		});
}

$.ajax({
	url : 'corona.do',
    type : 'get',
    dataType:'xml',
    success : function(data) {
        if($(data).find('item').length>=2){
        	//인덱스 0이 오늘 / 인덱스1이 어제
        	var decideCnt = new Array();
        	var clearCnt = new Array();
        	var deathCnt = new Array();
        	var stateDt = new Array();
        	var examCnt = new Array();
        	var stateTime = new Array();
        	var cont=0;
        	$(data).find('item').each(function(){
     		   decideCnt[cont] = $(this).find('decideCnt').text();
     		  clearCnt[cont] = $(this).find('clearCnt').text();
     		  deathCnt[cont] = $(this).find('deathCnt').text();
     		  stateDt[cont] = $(this).find('stateDt').text();
     		  stateTime[cont] = $(this).find('stateTime').text();
     		  cont+=1;
     	   })
     	   $("#decideCnt").text(numberWithCommas(decideCnt[0]));
           $("#decideCntGap").text("+"+numberWithCommas(decideCnt[0]-decideCnt[1]));
  		   $("#clearCnt").text(numberWithCommas(clearCnt[0]));
  		   $("#clearCntGap").text("+"+numberWithCommas(clearCnt[0]-clearCnt[1]));
  		   $("#deathCnt").text(numberWithCommas(deathCnt[0]));
  		   $("#deathCntGap").text("+"+numberWithCommas(deathCnt[0]-deathCnt[1]));
  		   $("#stateDate").text(stateDt[0].substring(4,6)+'.'+stateDt[0].substring(6,8)+'일 / '+stateTime[0].substring(0,2)+'시 보건복지부기준');
        	
     	   
       }else if($(data).find('item').length==1){
    	   $(data).find('item').each(function(){
    	   var decideCnt = $(this).find('decideCnt').text();
 		   var clearCnt = $(this).find('clearCnt').text();
 		   var deathCnt = $(this).find('deathCnt').text();
 		   var stateDt = $(this).find('stateDt').text();
 		   var stateTime = $(this).find('stateTime').text();
 		 
 		   $("#decideCnt").text(numberWithCommas(decideCnt));
 		   $("#decideCntGap").text("-");
 		   $("#clearCnt").text(numberWithCommas(clearCnt));
 		   $("#clearCntGap").text("-");
 		   $("#deathCnt").text(numberWithCommas(deathCnt));
 		   $("#deathCntGap").text("-");
 		   $("#stateDate").text(stateDt.substring(4,6)+'.'+stateDt.substring(6,8)+'일 / '+stateTime.substring(0,2)+'시 보건복지부기준');
    	   })
       }
   }, // success
    
    error : function(xhr, status) {
       console.log(xhr+" : "+status); // 에러 코드
   }

});

function numberWithCommas(x) {
    x = x.toString();
    var pattern = /(-?\d+)(\d{3})/;
    while (pattern.test(x))
        x = x.replace(pattern, "$1,$2");
    return x;
}

$.ajax({
	url :  'https://apis.naver.com/mobile_main/srchrank/srchrank?frm=main&ag=all&gr=0&ma=-2&si=-2&en=-2&sp=-2',
	type : 'get',
	dataType : 'json',
	success : function(data){
		for(var i=0;i<10;i++){
			var rank = data.data[i].rank;
			var keyword = data.data[i].keyword;
			$("#srchrank").append('<li><strong>'+rank+'.   </strong><a href = "https://search.naver.com/search.naver?ie=utf8&query='+keyword+'" class="text-muted" target="_sub"> '+keyword+'</a></li>');
		}
		
	},
	error : function(xhr, status) {
	       console.log(xhr+" : "+status); // 에러 코드
	}
});


// 네이버 실시간 순위
function rank1(){
	$.ajax({
		url :  'https://apis.naver.com/mobile_main/srchrank/srchrank?frm=main&ag=all&gr=0&ma=-2&si=-2&en=-2&sp=-2',
		type : 'get',
		dataType : 'json',
		success : function(data){
			for(var i=0;i<10;i++){
				var rank = data.data[i].rank;
				var keyword = data.data[i].keyword;
				if(i==0)
					$("#srchrank").html('<li><strong>'+rank+'.   </strong><a href = "https://search.naver.com/search.naver?ie=utf8&query='+keyword+'" class="text-muted" target="_sub"> '+keyword+'</a></li>');
				else
					$("#srchrank").append('<li><strong>'+rank+'.   </strong><a href = "https://search.naver.com/search.naver?ie=utf8&query='+keyword+'" class="text-muted" target="_sub"> '+keyword+'</a></li>');
			}
			
		},
		error : function(xhr, status) {
		       console.log(xhr+" : "+status); // 에러 코드
		}
	});
}

// 네이버 실시간순위2
function rank2(){
	$.ajax({
		url :  'https://apis.naver.com/mobile_main/srchrank/srchrank?frm=main&ag=all&gr=0&ma=-2&si=-2&en=-2&sp=-2',
		type : 'get',
		dataType : 'json',
		success : function(data){
			for(var i=10;i<20;i++){
				var rank = data.data[i].rank;
				var keyword = data.data[i].keyword;
				if(i==10)
					$("#srchrank").html('<li><strong>'+rank+'.   </strong><a href = "https://search.naver.com/search.naver?ie=utf8&query='+keyword+'" class="text-muted" target="_sub"> '+keyword+'</a></li>');
				else
					$("#srchrank").append('<li><strong>'+rank+'.   </strong><a href = "https://search.naver.com/search.naver?ie=utf8&query='+keyword+'" class="text-muted" target="_sub"> '+keyword+'</a></li>');
			}
			
		},
		error : function(xhr, status) {
		       console.log(xhr+" : "+status); // 에러 코드
		}
	});
}

/* exchange.init
 * 환율정보 */
	var svc_exchange = '//ars.yna.co.kr/api/v2/svc.exchange?names=USD,JPY,EUR,CNY,GBP,AUD,CAD,NZD';
	var country_exchange = ["미국","일본","유럽연합","중국","영국","호주","캐나다","뉴질랜드"];
	var country_exchange_img = ["exchange_us.png","exchange_jp.png","exchange_eu.png","exchange_ch.png","exchange_uk.png","exchange_ast.png","exchange_ca.png","exchange_nzd.png"];
	var exchange_count=0;
	$.ajax({
		url: encodeURI(svc_exchange),
		type: 'GET',
		success: function (data) {
			if (data != null && data.DATA != '') {
				var dataApi = data.DATA;
				var dateTime = dataApi[0].DATETIME;
				$("#exchangeTime").html(dateTime.substring(0,4)+"."+dateTime.substring(4,6)+"."+dateTime.substring(6,8)+" "+dateTime.substring(8,10)+"시 기준");
				$.each(dataApi, function(key,value){
					var text="<li class='exchangeItem'>";
					text+="<p class='exchangeName'><span><img src='img/"+country_exchange_img[exchange_count]+"'>"+country_exchange[exchange_count]+"("+value.NAME+")</p>";
					text+="<p class='exchangeValue'>"+numberWithCommas(value.RATE)+"</p>";
					$("#exchangeOl").append(text+"</li>");
					exchange_count++;
				})
			}
		}//success 끝
	});
	
	var slideIndex=0;
	function slideShow(){
		var i;
		var x = document.getElementsByClassName("exchangeItem");
		for(i=0;i<x.length;i++){
			x[i].style.display="none";
		}
		slideIndex++;
		if(slideIndex>x.length)
			slideIndex=1;
		x[slideIndex-1].style.display="block";
		setTimeout(slideShow,3000);
	}
	//youtube
	function newsYoutube(){
		var youtubeCnt=0;
		$.ajax({
			url:"https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PLVwwxTsiD3WWqNwf1cWASJm4J9OqiOGxF&maxResults=25&key=AIzaSyBkvL-d3dzLDP2qeW3TNslI8sICAT__2C0",
			type: 'GET',
			success: function(data){
				if (data != null && data.DATA != '') {
					$.each(data.items,function(key,value){
						var title = value.snippet.title.split('/')[0].trim();
						var videoUrl = value.snippet.resourceId.videoId;
						var uploadTime = value.snippet.publishedAt.split('T')[0]+" "+value.snippet.publishedAt.split('T')[1].substring(0,8);
						var Thumnail = value.snippet.thumbnails.standard.url;
						var ThumnailWidth = value.snippet.thumbnails.standard.width;
						var ThumnailHeight = value.snippet.thumbnails.standard.height;
						if(youtubeCnt==0){
							$("#youtubeUl").append('<li data-target="#youtubeDiv" dataslide-to="'+youtubeCnt+'" class="active"');
							var text = '<div class="carousel-item active">';
							text +='<a href="https://www.youtube.com/watch?v='+videoUrl+'" target="_blank" ><img src="'+Thumnail+'"></a></div>';
							$("#youtubeItems").append(text);
						}
						else{
							$("#youtubeUl").append('<li data-target="#youtubeDiv" dataslide-to="'+youtubeCnt+'"');
							var text = '<div class="carousel-item">';
							text +='<a href="https://www.youtube.com/watch?v='+videoUrl+'" target="_blank" ><img src="'+Thumnail+'"></a></div>';
							$("#youtubeItems").append(text);
						}
						youtubeCnt++;
					});
				}
			}
		});
	}
