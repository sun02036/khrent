<%@page import="com.kh.semi.board.model.vo.QuestionBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<QuestionBoard> list = (List<QuestionBoard>) request.getAttribute("list");
%>
	
					<a href="<%= request.getContextPath() %>/board/questionList" class="listItem"> 
<%
	for(QuestionBoard board : list) {
%>
						<p class="itemTitle">[문의] <%= board.getQna_title() %></p>
						<p class="itemDate"><%= board.getRegDate() %></p>
<% } %>
					</a>
					