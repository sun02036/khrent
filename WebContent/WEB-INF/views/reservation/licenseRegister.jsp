<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/login_enroll.css" />

<%
	String result = (String)request.getAttribute("result");
	System.out.println("result@licenseRegister.jsp = " + result);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>면허 등록 결과</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
	<div class="checkIdContainer">
	
<% if(result.equals("ok")) { %>

	<%-- 면허정보가 등록된 경우 --%>
	<span id="unduplicated">면허정보가 등록되었습니다.</span>
	<br /><br />
	<button class="checkIdBtn" onclick="popupClose1();">닫기</button>

<% } else { %>

	<%-- 등록되지 않은 경우 --%>
	<span id="notRegistered">면허정보가 등록되지 않았습니다. 다시 입력해 주세요.</span>
	<br /><br />
	<button class="checkIdBtn" onclick="popupClose2();">닫기</button>
	
<% } %>

	</div>
	
<script>
const popupClose1 = () => {
	// 부모창 opener 의 면허등록버튼 비활성화
	$(".licenseRegisterBtn", opener.document).attr('disabled', true);
	
	// popup 닫기
	close();
};

const popupClose2 = () => {
	// popup 닫기
	close();
};
</script>
</body>
</html>