<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/member/login_enroll.css" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
String msg = (String) session.getAttribute("msg");
if(msg != null) session.removeAttribute("msg");

Member member = (Member) request.getAttribute("member");
%>


 	<div class="login_wrap">
			<strong class="tit01" style="margin-bottom:25px;">비밀번호 재설정</strong>
			<div class="login_box clearfix" >
				<form id="findFrm" name="findFrm" class="ajax-form" action="<%= request.getContextPath() %>/member/memberFindPwdResult" method="POST">
	
				<span class="input-text-outer-wrap is-login-text">
					<span class="is-text-inner-wrap">
						<input type="hidden" name="memberId" value="<%= member.getMemberId() %>" />
						<input type="password" placeholder="영문, 숫자 포함하여 8자 이상" name="newPassword" id="newPassword" required>
					</span>
				</span>
				<span class="input-text-outer-wrap is-login-text">
					<span class="is-text-inner-wrap">
						<input type="password" placeholder="새로운 비밀번호 다시 입력" id="newPasswordCheck" required style="margin-bottom: 70px;">
					</span>
				</span>
					<input type="submit" value="변경" class="btn btn-l btn-color-type07 btn-login"/>
				</form>
					<input type="button" value="취소" class="btn btn-l btn-color-type07 btn-login" style="margin-top: 10px; background-color: #b3b3b3;" onclick="history.back()">
			</div>		
		</div>

<script>

<% if(msg != null) { %>
// 사용자 메세지 전달
alert("<%= msg %>");
<% } %>
 
	$("#newPasswordCheck").blur(passwordValidate);

	/**
	 * 신규비밀번호 일치 검사 
	 */
	function passwordValidate() {
		var $newPassword = $("#newPassword");
		var $newPasswordCheck = $("#newPasswordCheck");
		if ($newPassword.val() != $newPasswordCheck.val()) {
			alert("입력한 비밀번호가 일치하지 않습니다.");
			$newPassword.select();
			return false;
		}
		return true;
	}
	
</script>

	 
<%@ include file="/WEB-INF/views/common/footer.jsp"%>