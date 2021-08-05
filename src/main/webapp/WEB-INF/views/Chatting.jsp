<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script type="text/javascript"	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
<link href="css/chatting.css" rel="stylesheet">
	<div id="messageArea">
	</div>
	<input type="text" id="message" onkeydown="javascript:if(event.keyCode== 13) {sendMessage(); $('#message').val(''); return false;}"/>
	<input type="button" id="sendBtn" value="전송"/>
	
	
<script type="text/javascript">
	$("#sendBtn").click(function() {
		sendMessage();
		$('#message').val('')
	});

	let sock = new SockJS("http://192.168.150.27:8181/project/echo");
	//let sock = new SockJS("http://localhost:8181/project/echo");
	sock.onmessage = onMessage;
	sock.onclose = onClose;
	// 메시지 전송
	function sendMessage() {
		sock.send($("#message").val());
	}
	// 서버로부터 메시지를 받았을 때
	function onMessage(msg) {
		var data = msg.data;
		var sessionId = data.split('|')[0];
		var message = data.split('|')[1];
		$("#messageArea").append(sessionId+" : "+message + "<br/>");
	}
	// 서버와 연결을 끊었을 때
	function onClose(evt) {
		$("#messageArea").append("연결 끊김");
	}
</script>