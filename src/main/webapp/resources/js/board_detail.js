$(function(){
	
    commentList(); //페이지 로딩시 댓글 목록 출력 
});

/**
 * 기사의 글자수를 제한
 */
function updateChar(length_limit)
{
   var form = document.bbsForm;
   var length = calculate_msglen(document.getElementById("comment_textarea").value);
   textlimit.innerText = length;
   if (length > length_limit) {
       alert("최대 " + length_limit + "byte이므로 초과된 글자수는 자동으로 삭제됩니다.");
       form1.comment.value = form1.comment.value.replace(/\r\n$/, "");
       form1.comment.value = assert_msglen(form1.comment.value, length_limit);
   }
}

function calculate_msglen(message)
{
   var nbytes = 0;

   for (i=0; i<message.length; i++) {
       var ch = message.charAt(i);
       if(escape(ch).length > 4) {
           nbytes += 2;
       } else if (ch == '\n') {
           if (message.charAt(i-1) != '\r') {
               nbytes += 1;
           }
       } else if (ch == '<' || ch == '>') {
           nbytes += 4;
       } else {
           nbytes += 1;
       }
   }

   return nbytes;
}

function assert_msglen(message, maximum)
{
   var inc = 0;
   var nbytes = 0;
   var msg = "";
   var msglen = message.length;

   for (i=0; i<msglen; i++) {
       var ch = message.charAt(i);
       if (escape(ch).length > 4) {
           inc = 2;
       } else if (ch == '\n') {
           if (message.charAt(i-1) != '\r') {
               inc = 1;
           }
       } else if (ch == '<' || ch == '>') {
           inc = 4;
       } else {
           inc = 1;
       }
       if ((nbytes + inc) > maximum) {
           break;
       }
       nbytes += inc;
       msg += ch;
   }
   textlimit.innerText = nbytes;
   return msg;
}

function board_delete(){
	if(confirm("삭제하시겠습니까?")){
		location.href = "/project/board_delete.do?board_num="+$("#board_num").val();
	}
	else
		return false;
			
}


function commentInsertButton(){	//버튼 클릭시
    var insertData = $('[name=commentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴
    commentInsert(insertData); //Insert 함수호출(아래)
};

//댓글 등록
function commentInsert(insertData){
    $.ajax({
        url : '/project/CommentInsert.do',
        type : 'post',
        data : insertData,
        success : function(data){
        	if(data.trim() =="성공"){
                commentList(); //댓글 작성 후 댓글 목록 reload
                $('[name=comment_textarea]').val('');
        	}
        	else{
        		alert("댓글은 로그인후 사용 가능합니다.");
        		commentList();
        	}
        },
        error : function(xhr, status) {
            console.log(xhr+" : "+status); // 에러 코드
        } 
    });
}

function commentList(){
	var ano = $("#board_num").val(); //게시글 번호
    $.ajax({
        url : '/project/CommentList.do',
        type : 'get',
        data : {'ano':ano},
        success : function(json){
        	//console.log("===========날라온 데이터 : "+json);
            var a =''; 
            //console.log("asdfasd: "+json[0]);
            $.each(json, function(key, value){
            	var date = new Date(value.cdate);
                a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px; font-size: 0.8rem;">';
                a += '<div class="commentInfo'+value.cno+'">'+ '<p class="commentWriter"><strong>'+value.writer +'</strong><br>'+date_to_str(date)+'</p>'; 
     			/*a += '<a onclick="commentUpdate('+value.cno+',\''+value.ccontent+'\');"> 수정 </a>'; */
     			a += '<a class="commentDeleteButton" onclick="commentDelete('+value.cno+');"> 삭제 </a> </div>';
                a += '<div class="commentContent'+value.cno+'"> <p> '+value.ccontent +'</p>';
                a += '</div></div>';
            });
            $(".commentList").html(a);
        	
        },
        error : function(xhr, status) {
            console.log(xhr+" : "+status); // 에러 코드
        } 
    });
}
function commentDelete(comment_ano){
	var ano = $("#board_num").val(); //게시글 번호
	if(confirm("삭제 하시겠습니까? ")){
	   $.ajax({
	       url : '/project/CommentDelete.do/'+comment_ano,
	       type : 'post',
	       success : function(data){
	       	console.log(data.trim());
	           if(data.trim() == "성공") commentList(ano); //댓글 삭제후 목록 출력
	           else{
	           	alert("삭제에 실패하였습니다.");
	           	commentList(ano);
	           }
	       },
	       error : function(xhr, status) {
	            console.log(xhr+" : "+status); // 에러 코드
	        }
	   });
	}else{
		return;
	}
}

function date_to_str(format)
{
    var year = format.getFullYear();
    var month = format.getMonth() + 1;
    if(month<10) month = '0' + month;
    var date = format.getDate();
    if(date<10) date = '0' + date;
    var hour = format.getHours();
    if(hour<10) hour = '0' + hour;
    var min = format.getMinutes();
    if(min<10) min = '0' + min;
    return year + "-" + month + "-" + date + " " + hour + ":" + min;
}

