<%@page import="com.kh.semi.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/member/login_enroll.css" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
HttpSession session2 = request.getSession();
System.out.println(session2.getId());

String msg = (String) session.getAttribute("msg");

// 그냥 두면 페이지 새로 띄울때마다 alert 메시지가 계속 뜨기 때문에, 한번만 할 수 있도록 메시지 띄우고 나면 msg가 삭제되도록 한다.
if(msg != null) session.removeAttribute("msg");
%>

<script>
<% if(msg != null) { %>
	// 사용자 메시지 전달
	alert("<%= msg %>");
<% } %>
</script>

	<div class="login_wrap">
			<strong class="tit01" style="margin-bottom:25px;">로그인</strong>
			<div class="login_box clearfix" >
				<form id="loginFrm" class="ajax-form" action="<%= request.getContextPath() %>/member/login" method="POST">

					<span class="input-text-outer-wrap is-login-text">
						<span class="is-text-inner-wrap">
							<input placeholder="아이디를 입력해 주세요" name="memberId" id="memberId" type="text">
						</span>
					</span>
					<span class="input-text-outer-wrap is-login-text">
						<span class="is-text-inner-wrap">
							<input placeholder="비밀번호를 입력해 주세요" name="password" id="password" type="password" > 
						</span>
					</span>
					<div class="inp_chk_wrap">
					<span class="check-box have-text">
						<input type="checkbox" name="saveId" id="saveId">
						<label for="remember">자동로그인</label>

						<input type="button" class="findBtn" value="아이디 찾기" onclick="location.href='<%= request.getContextPath() %>/member/memberFindId';"/>
						<input type="button" class="findBtn" value="비밀번호 찾기" onclick="location.href='<%= request.getContextPath() %>/member/memberFindPwd';"/>						

					</span>
					</div>
					<input type="submit" value="로그인" class="btn btn-l btn-color-type07 btn-login" />
				</form>
				<br />
					<p style="text-align: center;">아직 <span style="font-weight: bolder; color: #7398c3;">KHrent</span> 회원이 아니신가요?</p>
					<input type="button" value="회원가입" class="btn btn-l btn-color-type07 btn-login" style="margin-top: 10px; background-color: #b3b3b3;" onclick="location.href='<%= request.getContextPath() %>/member/enroll';">
			</div>		
		</div>

<script>



$(() => {
	
	/**
	* loginFrm 유효성 검사
	*/
	
	$(document.loginFrm).submit(e => {
		const $memberId = $(memberId);
		const $password = $(password);
		// memberId 체크
		if(!/^.{4,}$/.test($memberId.val())){
			alert("아이디를 입력하세요.");
			$memberId.select();
			e.preventDefault();
			return;
		}
		
		// password 체크
		if(!/^.{8,}$/.test($password.val())){
			alert("비밀번호를 입력하세요.");
			$password.select();
			e.preventDefault();
			return;
		}
	});

});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
