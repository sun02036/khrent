<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

	 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminCarInsertSelect.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/admin/adminMenu.jsp"%>

		<div class="mypageCont">
						
				<strong class="tit01">차량/차종 추가</strong>
				<div class="carInsertWrap">
						<p class=tit>원하시는 기능을 선택해주세요.</p>
						<div class=btnSite>
					<span class="input-text-outer-wrap__right-section"> 
						<input type="button" value="차종 추가" class="adminMemberUpdateBtn" onclick="location.href='<%= request.getContextPath() %>/admin/adminCarInfoInsert';"/>
						<input type="button" value="차량 추가" class="adminMemberUpdateBtn" onclick="location.href='<%= request.getContextPath() %>/admin/adminCarListInsert';"/>
					</span>
						<input type="button" value="취소" class="btn-cp-modify" onclick="goBack()">
						</div>
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