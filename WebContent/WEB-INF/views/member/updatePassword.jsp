<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/member/updatePassword.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/member/myPageMenu.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>



<div class="updatePwd_wrap">
	<div class="wrap clearfix">

		<%@ include file="/WEB-INF/views/member/memberPageMenu.jsp"%>


		<div class="pop_header">
			<strong>비밀번호 변경</strong>
		</div>

		<div class="pop_cont">
			<form name="frmPwdUpdate" id="frmPwdUpdate" method="POST"
				action="<%=request.getContextPath()%>/member/updatePassword">

				<div class="inputWrap">
					<ul>
						<li><label for="name">현재 비밀번호</label> <input type="password"
							placeholder="" name="oldPassword" id="oldPassword" required></li>
						<li><label for="phone">새 비밀번호</label> <input type="password"
							placeholder="영문, 숫자 포함하여 8자 이상" name="newPassword"
							id="newPassword" required></li>
						<li><label for="phone">비밀번호 재입력</label> 
						<input type="password" placeholder="새로운 비밀번호 다시 입력" id="newPasswordCheck" required style="margin-bottom: 70px;"></li>
					</ul>
				</div>
				<div class="pop_footer">
					<input type="submit" value="변경" class="updatePwdBtn" />
				</div>
			</form>
		</div>
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


	/**
	 * 폼제출 유효성검사
	 */
	$("[name=updatePwdFrm]").submit(function() {
		var $oldPassword = $("#oldPassword");
		var $newPassword = $("#newPassword");

		if (!passwordValidate()) {
			return false;
		}

		if ($oldPassword.val() == $newPassword.val()) {
			alert("기존비밀번호와 신규비밀번호는 같을 수 없습니다.");
			$oldPassword.select();
			return false;
		}

		return true;

	});

	/**
	 * 
	 */
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