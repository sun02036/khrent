<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/member/login_enroll.css" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/nav.jsp" %>

<div action="<%=request.getContextPath()%>/member/memberEnrollCompleted"></div>

<div class="content_wrap join_complete">
			<h1 class="title" style="margin-bottom: 32px; margin-top: 80px;">예약이 완료되었습니다.<br></h1>
	<div class="join_complete_wrap">
		<div class="join_Complete">			
			<div class="speechBubble">
				<strong class="point" style="font-weight: bolder;"> KH렌트 </strong>를 이용해주셔서 감사합니다. 
			</div>
		</div>
	</div>
			<div class="homeBtn">
				<a href="<%= request.getContextPath() %>" class="goHome"> <span> < 홈으로 가기</span>
				</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="<%= request.getContextPath() %>/member/myPage?memberId=<%= loginMember.getMemberId() %>" class="goHome"> <span> 예약내역 확인하기></span>
				</a>
			</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>