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
				<strong class="tit01">회원정보수정</strong>
				<div class="mypageDesc2">
					<div class="topArea clearfix">
						<strong class="tit04 clearfix"> 기본정보 </strong>
					</div>

					<form name="memberUpdateFrm"
						action="<%=request.getContextPath()%>/admin/adminMemberUpdate"
						method="POST">
						
				<div class="info_content">
					<dl>
						<dt>이름</dt>
						<dd><%= member.getMemberName()%></dd>
					</dl>

					<dl>
						<dt>비밀번호</dt>
						<dd>
							<span
								class="input-text-outer-wrap is-login-text have-right-section">
								<span class="is-text-inner-wrap is-login-text"> 
								<input type="hidden" name="memberId" value="<%= member.getMemberId() %>" />
								<input type="password" placeholder="" name="password" id="password" value="<%= member.getPassword()%>">
								</span> 
							</span>
						</dd>
					</dl>
					
						<dl>
							<dt>휴대폰번호</dt>
							<dd>
								<span class="input-text-outer-wrap is-login-text have-right-section">
									<span class="is-text-inner-wrap is-login-text"> 
									<input type="hidden" name="memberId" value="<%=member.getMemberId()%>" /> 
									<input type="hidden" name="member_role" value="<%= member.getMemberRole() %>" />

									
									<input type="text" name="phone" id="phone" value="<%=member.getPhone()%>">
									</span> 
								</span>
							</dd>
						</dl>
				</div>

				<div>
					<div class="topArea clearfix">
						<strong class="tit04 clearfix"> 면허정보 </strong>
					</div>
						
						
						<dl>
							<dt>면허종류</dt>
							<dd>
								<span class="input-text-outer-wrap is-login-text have-right-section">	                                
								<div class="licenseList clearfix">
                                    <input type="radio" id="stick" name="license_type" value="stick">
                                    <label for="stick">
                                    <span class="license_type">1종 보통</span>
                                    </label>
                                    <input type="radio" id="auto" name="license_type" value="auto">
                                    <label for="auto">
                                    <span class="license_type">2종 보통</span>
                                    </label>
                                </div>
								</span>
							</dd>
						</dl>
						
						<dl>
							<dt>면허번호</dt>
							<dd>
								<span class="input-text-outer-wrap is-login-text have-right-section">
									<span class="is-text-inner-wrap is-login-text"> 
									<input type="text" name="license_no" id="license_no" value="<%= member.getLicense_no() %>">
									</span> 
								</span>
							</dd>
						</dl>
						
						<dl>
							<dt>면허발급일</dt>
							<dd>
								<span class="input-text-outer-wrap is-login-text have-right-section">
									<span class="is-text-inner-wrap is-login-text"> 
									<input type="text" name="issue_date" id="issue_date" value="<%=member.getIssue_date()%>">
									</span> 
								</span>
							</dd>
						</dl>
						</div>
						<div class="adminMemberUpdateBtn">
					<span class="input-text-outer-wrap__right-section"> 
						<input type="submit" value="변경" class="btn-cp-modify">
					</span>
						</div>
					</form>
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

// 유효성 검사
$("[name=frmDomestic]").submit((e) => {
	const $licenseNo = $("#license_no");
	
	if(/^[0-9]{12}$/.test($licenseNo.val()) == false){
		alert("숫자(정수)만 입력 가능합니다.");
		$licenseNo.select();
		return false;
	}
	return true;
});

$('input:radio[name="license_type"][value="<%= member.getLicense_type()%>"]').prop('checked', true);


</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>