<%@page import="com.kh.semi.board.model.vo.ReviewBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/customerCenterNav.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/board.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/boardList.css" />

<%
	List<ReviewBoard> list = (List<ReviewBoard>) request.getAttribute("list");
	String msg = (String)session.getAttribute("msg");
	
	// 그냥 두면 페이지 새로 띄울때마다 alert 메시지가 계속 뜨기 때문에, 한번만 할 수 있도록 메시지 띄우고 나면 msg가 삭제되도록 한다.
		if(msg != null) session.removeAttribute("msg");
%>

<script>
	<% if(msg != null) { %>
	// 사용자 메시지 전달
	alert("<%= msg %>");
	<% } %>
</script>

<div class="wrap clearfix" style="width: 1417px; margin:auto; display: flex;">

	<%@ include file="/WEB-INF/views/board/customerCenterNav.jsp" %>

	<div class="board-container">
	
	<h1 class="board-title">대여후기</h1>
	
	
		<table id="tbl-board">
			<thead>
				<tr class="board-tr"> 
					<th style="width:30px;">번호</th>
					<th>제목</th>
					<th>이용차량</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>첨부파일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
<%
	for(ReviewBoard board : list) {
%>
				<tr class="board-tr">
		       		<td><%= board.getReviewNo() %></td>
		       		<td class="subject">
		       			<a href="<%= request.getContextPath() %>/board/reviewBoardView?no=<%= board.getReviewNo() %>"><%= board.getReviewTitle() %></a>
		       			<!-- 글제목 오른쪽에 댓글수를 표시함 -->
		       			<%= board.getBoardCommentCount() != 0 ? "(" + board.getBoardCommentCount() + ")" : "" %>
		       		</td>
		       		<td><%= board.getCarName()%></td>
		       		<td><%= board.getReviewWriter() %></td>
		       		<td><%= board.getRegDate() %></td>
		       		<td>
<% 
		if(board.getAttach() != null){
%>
				<img src="https://i.ibb.co/wM6QjzV/1.png" alt="" width="16px"/>			
<%
		}
%>			       		
		       		</td>
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