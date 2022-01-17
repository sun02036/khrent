<%@page import="com.kh.semi.reservation.model.vo.CarList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <%
	CarList carList = (CarList) request.getAttribute("carList");
	%>
	
	 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminMemberUpdate.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/admin/adminMenu.jsp"%>

		<div class="mypageCont">
					<form name="carInfoDeleteFrm"
						action="<%=request.getContextPath()%>/admin/adminCarListDelete"
						method="POST">
						
				<strong class="tit01">차량 삭제</strong>
				<div class="mypageDesc2">

						<p>정말 삭제하시겠습니까?</p>
						<span>삭제 시 복구할 수 없습니다.</span>
						<input type="hidden" name="carCode" value="<%= carList.getCarCode() %>" />
						</div>
						<div class="adminMemberUpdateBtn">
					<span class="input-text-outer-wrap__right-section"> 
						<input type="submit" value="삭제" class="btn-cp-modify">
						<input type="button" value="취소" class="btn-cp-modify" onclick="goBack()">
					</span>
						</div>
					</form>
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