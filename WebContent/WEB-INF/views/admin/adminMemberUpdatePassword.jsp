<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/member/memberDelete.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/member/myPageMenu.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>

    <%
	Member member = (Member) request.getAttribute("member");
	%>

<div class="delete_wrap">
	<div class="wrap clearfix">

		<%@ include file="/WEB-INF/views/member/memberPageMenu.jsp"%>


		<div class="pop_header">
			<strong>회원 비밀번호 변경 <br />
			</strong>

		</div>

		<div class="pop_cont">
			<form name="memberUpdatePasswordFrm" id="memberUpdatePasswordFrm" method="POST"
				action="<%=request.getContextPath()%>/admin/adminMemberUpdatePassword">

				<div class="inputWrap">
					<ul>
						<input type="hidden" name="memberId" value="<%= member.getMemberId() %>" />
						<li>
						<label for="name">변경할 비밀번호</label> 
						<input type="password" placeholder="" name="password" id="password" required>
						</li>						
					</ul>
				</div>
				<div class="pop_footer">
					<input type="submit" value="변경" class="deletePwdBtn" onclick="adminMemberUpdatePassword();" />
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

function adminMemberUpdatePassword(){
		$(document.memberUpdatePasswordFrm).submit();
	}
}

$("[memberUpdatePasswordFrm]").submit(function() {
	const $password = $("#password");
	
	if(/^[a-zA-Z0-9!@#$$%^&*()]{8,}/.test($password.val()) == false){
		alert("유효한 비밀번호를 입력해주세요.");
		$password.select();
		return false;
	}
	return true;
)};


<%@ include file="/WEB-INF/views/common/footer.jsp"%>