<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%
	// isErrorPage="true"인 경우, WAS가 에러페이지로 연결시 발생한 예외객체에 선언없이 exception으로 접근가능
	String msg = exception.getMessage();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오류</title>
	<style>
	body {text-align: center;}
	h1 {font-size: 250px; margin: 0;}
	#msg {color: red;}
	</style>
</head>
<body>
	<h1>ㅠㅠ</h1>
	<p>이용에 불편을 드려 죄송합니다.</p>
	<p id="msg"><%= msg %></p>
	<p><a href="<%= request.getContextPath() %>">홈으로</a></p>
</body>
</html>