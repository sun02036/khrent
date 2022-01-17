<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/memberDetail.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/myPageMenu.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>

<section id="content" style="height: 1200px;">
<div class="detail_wrap">
	<div class="wrap clearfix" style="display: flex;">

		<!-- 공통 메뉴 -->
		<%@ include file="/WEB-INF/views/member/memberPageMenu.jsp"%>

		<div class="mypageCont">
			<strong class="tit01">내 정보</strong>
			<div class="mypageDesc2">
				<div class="topArea clearfix">
					<strong class="tit04 clearfix"> 기본정보 </strong>
				</div>
				
				<dl>
					<dt>이름</dt>
					<dd><%= loginMember.getMemberName() %></dd>
				</dl>
				
				<dl>
					<dt>비밀번호</dt>
					<dd>
						<span
							class="input-text-outer-wrap is-login-text have-right-section">
							<span class="is-text-inner-wrap is-login-text"> 
							<input type="password" data-text-module="" readonly="">
							</span> 
						<span class="input-text-outer-wrap__right-section"> 
						<a href="<%= request.getContextPath() %>/member/updatePassword" class="btn-pwd-pop">변경</a>
						</span>
						</span>
					</dd>
				</dl>
					<form name="memberUpdateFrm"
						action="<%=request.getContextPath()%>/member/memberPhoneUpdate"
						method="POST">
						<dl>
							<dt>휴대폰번호</dt>
							<dd>
								<span
									class="input-text-outer-wrap is-login-text have-right-section">
									<span class="is-text-inner-wrap is-login-text">
									<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" /> 
									<input type="text" name="phone" id="phone" value="<%=loginMember.getPhone()%>">
									</span> 
								<span class="input-text-outer-wrap__right-section"> 
								<input type="submit" value="변경" class="btn-cp-modify" >
								</span>
								</span>
							</dd>
						</dl>
					</form>
				</div>

			<div class="mypageDesc2 license active">
				<div class="topArea clearfix">
					<strong class="tit04 clearfix"> 운전면허 정보 </strong>
				</div>
				<p><%= loginMember.getLicense_no() == null ? "등록된 면허 정보가 없습니다." : "등록된 면허가 있습니다." %></p>
<%
if (loginMember.getLicense_no() == null) {
%>
				<div class="btnGroup">
					<a href="<%= request.getContextPath() %>/member/memberLicenseRegister" class="infoBtn btn-license">면허정보 등록</a>
				</div>
<% } else {%>
				<p>면허 종류 : <%= loginMember.getLicense_type() %></p>
				<p>면허 번호 : <%= loginMember.getLicense_no() %></p>
				<p>면허 발급일 : <%= loginMember.getIssue_date() %></p>

<%
	}
%>
			</div>

			<div class="mypageDesc2">
				<div class="topArea clearfix">
					<strong class="tit04 clearfix"> 회원탈퇴 </strong>
				</div>
				<p>
					KH렌트 이용에 불편하신 점이 있으셨다면, 탈퇴 전 고객센터로 문의 주시면 답변 드리겠습니다. <br>
				</p>
				<div class="btnGroup">
					<a href="<%= request.getContextPath() %>/member/memberDelete" class="infoBtn">회원탈퇴</a>
				</div>
			</div>
		</div>
	</div>
</div>
</section>

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