<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/member/login_enroll.css" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
String msg = (String) session.getAttribute("msg");
if(msg != null) session.removeAttribute("msg");
%>

 	<div class="login_wrap">
			<strong class="tit01" style="margin-bottom:25px;">비밀번호 찾기</strong>
			<div class="login_box clearfix" >
				<form id="findFrm" name="findFrm" class="ajax-form" action="<%= request.getContextPath() %>/member/memberFindPwd" method="POST">

					<span class="input-text-outer-wrap is-login-text">
						<span class="is-text-inner-wrap">
							<input placeholder="아이디를 입력해 주세요" name="memberId" id="memberId" type="text">
						</span>
					</span>
					<span class="input-text-outer-wrap is-login-text">
						<span class="is-text-inner-wrap">
							<input placeholder="(- 없이) 휴대폰 번호를 입력해주세요" name="phone" id="phone" type="text" > 
						</span>
					</span>

					<input type="button" value="찾기" class="btn btn-l btn-color-type07 btn-login" onclick="pwd_search()" />
				</form>
					<input type="button" value="취소" class="btn btn-l btn-color-type07 btn-login" style="margin-top: 10px; background-color: #b3b3b3;" onclick="history.back()">
			</div>		
		</div>

<script>

<% if(msg != null) { %>
// 사용자 메세지 전달
alert("<%= msg %>");
<% } %>

</script>

<script>

function pwd_search() { 
	 	var frm = document.findFrm;

	 	if (frm.memberId.value.length < 1) {
		  alert("아이디를 입력해주세요");
		  return;
		 }

		 if (frm.phone.value.length != 11) {
			  alert("휴대폰 번호를 정확하게 입력해주세요");
			  return;
		 }

	 frm.submit();  
}

</script>
	 
<%@ include file="/WEB-INF/views/common/footer.jsp"%>