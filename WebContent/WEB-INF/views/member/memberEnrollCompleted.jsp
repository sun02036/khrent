<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/member/login_enroll.css" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/nav.jsp" %>

<div action="<%=request.getContextPath()%>/member/memberEnrollCompleted"></div>

<div class="content_wrap join_complete">
			<h1 class="title" style="margin-bottom: 32px; margin-top: 80px;">환영합니다!<br></h1>
			<h2 class="subTitle">회원가입 완료</h2>
	<div class="join_complete_wrap">
		<div class="join_Complete">			
			<div class="speechBubble">
				<strong class="point" style="font-weight: bolder; color: #538797;"> KH렌트 </strong> 
				에 가입해주셔서 감사드립니다. <br /><br />
				저희 사이트에서 간편한 렌트카 대여 서비스를 이용해보세요.
			</div>
		</div>
	</div>
			<div class="homeBtn">
				<a href="<%= request.getContextPath() %>" class="goHome"> <span> < 홈으로 가기</span>
				</a>
			</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>