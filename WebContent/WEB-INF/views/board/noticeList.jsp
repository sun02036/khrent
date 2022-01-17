<%@page import="com.kh.semi.board.model.vo.NoticeBoard"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.semi.board.model.vo.QuestionBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/customerCenterNav.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/boardList.css" />
<%
	List<NoticeBoard> list = (List<NoticeBoard>) request.getAttribute("list");

	String msg = (String) session.getAttribute("msg");
	// System.out.println("msg@header.jsp = " + msg);
	if(msg != null) session.removeAttribute("msg");
%>

<script>
<% if(msg != null) { %>
    alert("<%= msg %>");
<% } %>
</script>

<div class="wrap clearfix" style="width: 1417px; margin:auto; display: flex;">

<%@ include file="/WEB-INF/views/board/customerCenterNav.jsp" %>

	<div class="board-container">
	
	<h1 class="board-title">공지사항</h1>
<% if(loginMember != null && MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())){ %>	
	<div id="writing-container">
	<input 
		type="button" 
		value="글쓰기" 
		id="btn-add" 
		onclick="location.href='<%= request.getContextPath() %>/board/noticeBoardForm';" />
	</div>
	
<% } %>

	<!-- <div class="board-box"> -->
		<table id="tbl-board">
			<thead>
				<tr class="board-tr">
					<th>번호</th>
					<th>제목</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
<%
	for(NoticeBoard board : list) {
%>
				<tr class="board-tr">
		       		<td><%= board.getNoticeNo() %></td>
		       		<td class="subject">
		       			<a href="<%= request.getContextPath() %>/board/noticeView?no=<%= board.getNoticeNo() %>"><%= board.getNoticeTitle() %></a>
		       		</td>
		       		<td><%= board.getRegDate() %></td>
		       		<td><%= board.getReadCount() %></td>
       			</tr>
<%
	}
%>       							
			</tbody>
		</table>
		
		<div id='pageBar'>
			<%= request.getAttribute("pagebar") %>
		</div>
	
</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>