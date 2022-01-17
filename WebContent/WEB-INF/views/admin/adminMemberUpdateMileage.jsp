<%@page import="com.kh.semi.member.model.vo.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <%
	Member member = (Member) request.getAttribute("member");
	%>
	
	 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminMemberUpdate.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/admin/adminMenu.jsp"%>

		<div class="mypageCont">
				<strong class="tit01">마일리지 관리</strong>
				<div class="mypageDesc2">
				
					<div class="topArea clearfix">
						<strong class="tit04 clearfix"> 현재 마일리지 </strong>
					</div>

					<form name="memberUpdateMileageFrm"
						action="<%=request.getContextPath()%>/admin/adminMemberUpdateMileage"
						method="POST">
						
				<div class="info_content">
					<dl>
						<dt>이름</dt>
						<dd><%= member.getMemberName()%></dd>
					</dl>

					<dl>
						<dt>현재 마일리지</dt>
						<dd>
							<%= member.getMileage() %>
						</dd>
					</dl>
				<img src="https://i.ibb.co/c86xcZM/image.png" alt="" style="width: 50px; margin: 70px 0 0 80px;"/>
				</div>
				

				<div>
					<div class="topArea clearfix">
						<strong class="tit04 clearfix"> 변경 마일리지 </strong>
					</div>
						
						<dl style="padding: 0;">
								<span class="input-text-outer-wrap is-login-text have-right-section">
									<span class="is-text-inner-wrap is-login-text"> 
									<input type="hidden" name="memberId" id="memberId" value="<%= member.getMemberId() %>" />
									<input type="text" name="mileage" id="mileage" value="" placeholder="변경할 마일리지 입력">
									</span> 
								</span>
						</dl>
						</div>
						<div class="adminMemberUpdateBtn" style="width: 249px;">
					<span class="input-text-outer-wrap__right-section"> 
						<input type="submit" value="변경" class="btn-cp-modify">
					</span>
						</div>
					</form>
					</div>												
				</div>
				
<script>

<% 
String msg = (String) session.getAttribute("msg");
if(msg != null) session.removeAttribute("msg");

%>

<% if(msg != null) { %>
// 사용자 메세지 전달
alert("<%= msg %>");
<% } %>

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>