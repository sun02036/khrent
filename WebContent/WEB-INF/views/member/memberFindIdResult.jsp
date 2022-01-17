<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/member/login_enroll.css" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>

    <%
	Member member = (Member) request.getAttribute("member");
	%>
	
 	<div class="find_wrap">
			<strong class="tit01" style="margin-bottom:25px;">아이디 찾기</strong>
			<div class="login_box clearfix" >

      <%
       if (member.getMemberId() != null) {
      %>			<div class="notice">
					<span class="input-text-outer-wrap is-login-text">
						<span class="is-text-inner-wrap">
							<h4>회원님의 아이디는</h4>
							<point class="findId"><%= member.getMemberId() %></point>
							<h4>입니다.</h4>
						</span>
					</span>
			      </div>
					<input type="button" class="btn btn-l btn-color-type07 btn-login" value="로그인" onclick="location.href='<%= request.getContextPath() %>/member/login';" />
		<%} %>			

			</div>		
		</div>

	 
<%@ include file="/WEB-INF/views/common/footer.jsp"%>