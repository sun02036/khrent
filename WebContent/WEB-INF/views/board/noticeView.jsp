<%@page import="com.kh.semi.board.model.vo.NoticeBoard"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.semi.board.model.vo.QuestionBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/customerCenterNav.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/boardView.css" />

<%
	List<NoticeBoard> list = (List<NoticeBoard>) request.getAttribute("list");
%>

<div class="wrap clearfix" style="width: 1417px; margin:auto;">

<%@ include file="/WEB-INF/views/board/customerCenterNav.jsp" %>

<%
	NoticeBoard noticeBoard = (NoticeBoard)request.getAttribute("noticeBoard");
	boolean editable = loginMember != null && MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole());
	//System.out.println("ediatable@boardView.jsp = " + editable);
	
	String msg = (String) session.getAttribute("msg");
	// System.out.println("msg@header.jsp = " + msg);
	if(msg != null) session.removeAttribute("msg");
%>

<div class="wrap clearfix" style="width: 1417px; margin:auto;">

	<div class="board-container">
	<h1 class="board-title">공지사항</h1>
	
<% 	if(editable){ %>
		<div id="writing-container">
			<tr>
				<%-- 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
				<th colspan="2">
					<input class="change" type="button" value="수정하기" onclick="updateBoard()">
					<input class="change" type="button" value="삭제하기" onclick="deleteBoard()">
				</th>
			</tr>
		</div>
<% 	} %>	

		<table id="tbl-board">
			<thead>
			<tr style="border-top: 1px solid black;"><th colspan="4"><%= noticeBoard.getNoticeTitle()%></th></tr>
			</thead>
			<tbody>
				<tr style="border-top: 1px solid black;">
					<td style="width: 970px;"> 작성일 : <%= noticeBoard.getRegDate()%></td>
					<td> 조회수 : <%= noticeBoard.getReadCount() %></td>
				</tr>
				<tr style="height: 300px;">
					<td colspan="4" id="questionViewContent"><%= noticeBoard.getNoticeContent()%></td>
				</tr>
			</tbody>
		</table>
		
		<br />
		
		<div class="space"></div>
	
</div> 
</div>
<% if(editable){ %>
<form action="<%= request.getContextPath() %>/board/noticeBoardDelete" name="deleteBoardFrm">
	<input type="hidden" name="no" value="<%= noticeBoard.getNoticeNo() %>" />
</form>
<script>
const updateBoard = 
() => location.href = "<%= request.getContextPath() %>/board/noticeBoardUpdate?no=<%= noticeBoard.getNoticeNo() %>";
/**
 * 삭제할 때 저장된 첨부파일이 있다면, 파일삭제!
 * - java.io.File API참조
 */
const deleteBoard = () => {
	if(confirm("정말 이 게시물을 삭제하시겠습니까?")){
		$(document.deleteBoardFrm).submit();
	}
};

<% if(msg != null) { %>
alert("<%= msg %>");
<% } %>


</script>
<% } %>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>