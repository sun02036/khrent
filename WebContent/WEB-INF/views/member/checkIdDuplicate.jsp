<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/login_enroll.css" />

<%
	String memberId = request.getParameter("memberId");
	boolean available = (boolean) request.getAttribute("available");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 검사</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
	<div class="checkIdContainer">
	
<% if(available) { %>

	<%-- 아이디가 사용가능한 경우 --%>
	<span id="unduplicated"><%= memberId %></span> 는 사용 가능한 아이디입니다.
	<br /><br />
	<button class="checkIdBtn" onclick="popupClose();">닫기</button>

<% } else { %>

	<%-- 아이디가 이미 사용중인 경우 --%>
	<span id="duplicate"><%= memberId %></span>는 이미 사용중인 아이디입니다.
	<br /><br />
	<form name="checkIdDuplicateFrm" action="<%= request.getContextPath() %>/member/checkIdDuplicate" method="POST">
		<input type="text" name="memberId" />
		<button type="submit" class="duplicateIdBtn">중복확인</button>
	</form>
	
<% } %>

	</div>
	<script>
	const popupClose = () => {
		// 부모창 opener 의 #_memberId value 설정
		$("#memberId", opener.document).val("<%= memberId %>");
		
		// 부모창의 #idValid value 1로 설정
		$("#idValid", opener.document).val("1");
		
		// popup 닫기
		close();
	};
	</script>
</body>
</html>


