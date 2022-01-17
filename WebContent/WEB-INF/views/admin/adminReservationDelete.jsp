<%@page import="com.kh.semi.reservation.model.vo.Reservation"%>
<%@page import="com.kh.semi.member.model.vo.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <%
	Reservation reservation = (Reservation) request.getAttribute("reservation");
	%>
	
	 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminMemberUpdate.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/admin/adminMenu.jsp"%>

		<div class="mypageCont">
					<form name="reservationDeleteFrm"
						action="<%=request.getContextPath()%>/admin/adminReservationDelete"
						method="POST">
						
				<strong class="tit01">예약 취소</strong>
				<div class="mypageDesc2">
					<div class="topArea clearfix">
<!-- 						<strong class="tit04 clearfix"> 기본정보 </strong> -->
					</div>

						<p>예약 취소하시겠습니까?</p>
						<span>예약 취소 시 복구할 수 없습니다.</span>
						<input type="hidden" name="reserNo" value="<%= reservation.getReserNo() %>" />
						</div>
						<div class="adminMemberUpdateBtn" style="width: 280px;">
					<span class="input-text-outer-wrap__right-section"> 
						<input type="submit" value="예약 취소" class="btn-cp-modify">
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