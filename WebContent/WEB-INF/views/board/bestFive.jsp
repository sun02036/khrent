<%@page import="java.util.List"%>
<%@page import="com.kh.semi.board.model.vo.CommunityBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/footer.css" />
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/header.css" />
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/nav.css" />
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/bestFive.css" />
 
<div id="allSection">
	<section class="bfSection">
	
		<h2 class="top">
			<span class="font_c_red">KH렌트</span>
			 인기 
		 	<span class="txtBold">TOP5</span>
	 	</h2>
	 	<p class="top_pick">
	 		가장 많이 사랑받는 고객님의 PICK!
		</p>
		
		<div id="bfSection">
		
		</div>
	</section>
</div>

<script>

$(document).ready(function () {
	$.ajax({
		url:"<%=request.getContextPath()%>/board/ajaxBestFive",
		method: "GET",
		success: function(data) {
			$("#bfSection").html(data);
		},
		complete: function() {
			console.log("complete")
		}
	});
});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>