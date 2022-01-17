<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% DecimalFormat fomatter = new DecimalFormat("###,###.##"); %>
    
    		<div class="lnb type03 borderNone clearfix">

			<strong class="myName"><span><%=loginMember.getMemberName()%></span>님</strong>
			<span class="division"> <%="U".equals(loginMember.getMemberRole()) == true ? "일반회원" : "관리자"%>
			</span> 
	
			<a href="<%=request.getContextPath()%>/member/memberDetail" class="btn btn-xxs btn-color-type02 btn-radius btn-line goToMyInfo">내정보</a>
			
			<div class="boxArea">
				<dl class="reservation">
					<dt>예약</dt>
					<dd>
						<a href="<%= request.getContextPath() %>/member/myPage?memberId=<%= loginMember.getMemberId() %>" id="reserv">
						
						</a>
					</dd>

					
					<dt>내 마일리지</dt>
					<dd style="text-decoration: underline; color: #2980b9;"><%= fomatter.format(loginMember.getMileage()) %></dd>
				</dl>
			</div>
			
			<hr class="memTypeDivider">
			
			<ul class="menuList">
				<li class="active"><a href="<%= request.getContextPath() %>/member/myPage?memberId=<%=loginMember.getMemberId()%>">예약내역</a></li>
				
				<li class="">
				<a href="<%=request.getContextPath()%>/member/memberDetail">내정보</a></li>
				<li><a id="kakao-link-btn" href="<%= request.getContextPath() %>/member/myPage?memberId=<%=loginMember.getMemberId()%>"> 리뷰 쓰기 </a></li>
			</ul>

			<div class="bannerArea"></div>
		</div>