<%@page import="com.kh.semi.board.model.vo.ReviewBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<ReviewBoard> list = (List<ReviewBoard>) request.getAttribute("list");
%>

					<a href="<%= request.getContextPath() %>/board/reviewList" class="listItem">
<%
	for(ReviewBoard board : list) {
%>
						<p class="itemTitle">[공지] <%= board.getReviewTitle() %></p>
						<p class="itemDate"><%= board.getRegDate() %></p>
<% } %>

					</a>