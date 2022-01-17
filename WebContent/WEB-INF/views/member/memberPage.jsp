<%@page import="com.kh.semi.board.model.vo.ReviewBoard"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.semi.reservation.model.vo.Reservation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/myPage.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/myPageMenu.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/memberReserPage.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>

<%
String msg = (String) session.getAttribute("msg");
if(msg != null) session.removeAttribute("msg");
%>


<div class="detail_wrap">
	<div class="wrap clearfix" style="width: 1417px; margin: auto;">

		<%@ include file="/WEB-INF/views/member/memberPageMenu.jsp"%>

		<div class="mypageCont">
			<strong class="tit01">예약내역</strong>
			<div class="sortBtn clearfix" id="this">



			</div>
		</div>
	</div>
</div>
       							

<script>

<% if(msg != null) { %>
// 사용자 메세지 전달
alert("<%= msg %>");
<% } %>

</script>

<script>

$(document).ready(function () {
$.ajax({
	url:"<%=request.getContextPath()%>/admin/ajaxMemberPageReservationCount",
	method: "GET",
	data: {
		memberId: "<%= loginMember.getMemberId() %>"
	},
	success: function(data) {
		$("#reserv").html(data);
	},
	complete: function() {
		console.log("complete")
	}
});
});

</script>

<script>

$(document).ready(function () {
$.ajax({
	url:"<%=request.getContextPath()%>/member/ajaxMemberReserPage",
	method: "GET",
	data: {
		memberId: "<%= loginMember.getMemberId() %>"
	},
	success: function(data) {
		$("#this").html(data);
	},
	complete: function() {
		console.log("complete")
	}
});
});

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>