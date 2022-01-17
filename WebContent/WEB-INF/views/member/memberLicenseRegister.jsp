<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/memberLicenseRegister.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/myPageMenu.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>

<section id="content" style="height: 1200px;">
<div class="register_wrap">
	<div class="wrap clearfix" style="display: flex;">
	
		<!-- 공통 메뉴 -->
		<%@ include file="/WEB-INF/views/member/memberPageMenu.jsp"%>
	
	<div class="iziModal-wrap" style="height: auto;"><div class="iziModal-content" style="padding: 0px;">
        <div class="pop_header">
            <strong class="tit01">운전면허 정보 등록</strong>
        </div>

        <div class="pop_cont">

            <div class="inputWrap">
   
                <form action="<%= request.getContextPath() %>/member/memberLicenseRegister" name="frmDomestic" id="frmDomestic" method="post" >

                    <div class="locallicense active">
                    <input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" /> 
                        <ul>
                            <li>    
                                <label for="licenseType">면허종류</label>
                                <div class="licenseList clearfix">
                                    <input type="radio" id="stick" name="type" value="stick" checked="checked">
                                    <label for="stick">
                                    <span class="license_type">1종 보통</span>
                                    </label>
                                    <input type="radio" id="auto" name="type" value="auto">
                                    <label for="auto">
                                    <span class="license_type">2종 보통</span>
                                    </label>
                                </div>
                            </li>
                            
                            <li>
                                <label for="licenseNo">면허번호</label>
                                <div class="licenseNoInput clearfix">
                                    <div style="width:200px;">
                                        <input type="text" name="license_no" id="license_no" style="width:318px;" value="" maxlength="12" placeholder="(-) 없이 숫자만 입력해주세요.">
                                    </div>
                                </div>
                            </li>
                  
                            <li class="mb50">
                                <label for="validFrom">면허 발급일</label>
                                <div class="validInput">
									<input type="text" id="issue_date" name="issue_date" class="issue_date_index" placeholder="날짜 선택" required>
                                </div>
                            </li>
                        </ul>
                    </div>
		            <div class="pop_footer">
		                <input type="submit" value="등록하기" class="licenseRegisterBtn" />
		            </div>
                </form>
            </div>
        </div>
    </div></div>
	
	</div>
	</div>
	</section>
	
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


</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	