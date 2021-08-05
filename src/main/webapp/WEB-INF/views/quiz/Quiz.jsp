<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
    <head>
 		<c:set var="contextPath" value="${pageContext.request.contextPath}" />
        <meta content="text/html; charset=utf-8" http-equiv="content-type">

        <title>자바 </title>
		<script src="quiz/js/jquery-1.7.min.js"></script>
		<script src="quiz/js/slickQuiz-config.js"></script>
        <script src="quiz/js/slickQuiz.js"></script>
        <script src="quiz/js/master.js"></script>
        <link href="quiz/css/reset.css" media="screen" rel="stylesheet" type="text/css">
        <link href="quiz/css/slickQuiz.css" media="screen" rel="stylesheet" type="text/css">
        <link href="quiz/css/master.css" media="screen" rel="stylesheet" type="text/css">
        
      
    </head>

    <body id="slickQuiz">
    
        <h1 class="quizName"><!-- where the quiz name goes --></h1>

        <div class="quizArea">
            <div class="quizHeader">
                <!-- where the quiz main copy goes -->

                <a class="button startQuiz" href="" id="quiz">시작!</a>
            </div>

            <!-- where the quiz gets built -->
        </div>

        <div class="quizResults">
            <h3 class="quizScore">You Scored: <span><!-- where the quiz score goes --></span></h3>

            <h3 class="quizLevel"><strong>Ranking:</strong> <span><!-- where the quiz ranking level goes --></span></h3>

            <div class="quizResultsCopy">
                <!-- where the quiz result copy goes -->
            </div>
        </div>

        
        
    </body>
</html>