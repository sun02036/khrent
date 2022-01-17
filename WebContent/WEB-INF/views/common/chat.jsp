<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/member/login_enroll.css" />

<section style="text-align: center;">
	<div class="container">
		<h1 style="margin-top: 50px; font-size: 24px;">궁금한 점을 실시간으로 물어보세요</h1>
		<iframe src="https://service.dongledongle.com/khrent" frameborder="0" width="500px" height="800vh" style="margin-top: 30px; margin-bottom: 50px;"></iframe>
			
			<div style="display: flex; justify-content: center">
			<input type="submit" id="btnBack" value="뒤로가기" class="btn btn-l btn-color-type07 btn-login" onClick="history.back()" style="margin: 0; background-color: #b3b3b3; margin-bottom: 50px; margin-right: 50px;"> 
			<input type="button" value="회원가입" class="btn btn-l btn-color-type07" onclick="location.href='<%= request.getContextPath() %>/member/enroll';" style="margin: 0"/>
			</div>
	</div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
    