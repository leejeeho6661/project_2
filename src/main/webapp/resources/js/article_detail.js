function article_delete(){
	if(confirm("삭제하시겠습니까?")){
		var category = $("#category").val();
		var num = $("#articleNum").val();
		location.href = "/project/article_delete.do?boardWriteNum="+num+"&boardWriteCategory="+category;
	}
	else
		return false;
}

function like_article(){
	var articleNum = $("#articleNum").val();
	var userId = $("#userId").val();
	$.ajax({
		url:"likeInsert.do",
		type:"post",
		dataType : 'text',
		data:{
			"userId":userId,
			"articleNum":articleNum, 
		},
		success:function(data){
			if(data.trim() != "실패"){
				$("#article_detail_likeCount").html(data.trim());
			}
			else if(data.trim() == "실패" && userId=="")
				alert("로그인 후 사용가능합니다.")
			else
				alert("좋아요는 한번만 가능합니다.");
		},
		error : function(xhr,status){
			console.log(xhr+" : "+status);
		}
	});
	
}
$(function(){
	var articleNum = $("#articleNum").val();
	var userId = $("#userId").val();
	$.ajax({
		url:"likeCount.do",
		type:"get",
		dataType : 'text',
		data:{
			"articleNum":articleNum
		},
		success:function(data){
			$("#article_detail_likeCount").html(data.trim());
		},
		error : function(xhr,status){
			console.log(xhr+" : "+status);
		}
	});
	
	$.ajax({
		url:"ReadArticle.do",
		type:"get",
		data:{
			"userId" : userId,
			"articleNum" : articleNum
		},
		success : function(data){
			console.log("1");
			console.log(data);
		},
		error : function(xhr,status){
			console.log(xhr+" : "+status);
		}
	});
});