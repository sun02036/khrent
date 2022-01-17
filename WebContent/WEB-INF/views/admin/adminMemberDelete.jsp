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
					<form name="memberDeleteFrm"
						action="<%=request.getContextPath()%>/admin/adminMemberDelete"
						method="POST">
						
				<strong class="tit01">회원 삭제</strong>
				<div class="mypageDesc2">
					<div class="topArea clearfix">
<!-- 						<strong class="tit04 clearfix"> 기본정보 </strong> -->
					</div>

						<p>정말 삭제하시겠습니까?</p>
						<span>삭제 시 복구할 수 없습니다.</span>
						<input type="hidden" name="memberId" value="<%= member.getMemberId() %>" />
						</div>
						<div class="adminMemberUpdateBtn">
					<span class="input-text-outer-wrap__right-section"> 
						<input type="submit" value="삭제" class="btn-cp-modify">
						<input type="button" value="취소" class="btn-cp-modify" onclick="goBack()">
					</span>
						</div>
					</form>
					</div>												
				</div>
				
<script>

function goBack() {
	window.history.back();
}

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