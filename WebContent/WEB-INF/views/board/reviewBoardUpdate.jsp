<%@page import="com.kh.semi.board.model.vo.ReviewBoard"%>
<%@page import="com.kh.semi.board.model.vo.Attachment"%>
<%@page import="com.kh.semi.board.model.vo.NoticeBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/customerCenterNav.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/boardForm.css" />

<%
	ReviewBoard reviewBoard = (ReviewBoard) request.getAttribute("reviewBoard");
%>
<div class="wrap clearfix" style="width: 1417px; margin:auto; display: flex;">
	<%@ include file="/WEB-INF/views/board/customerCenterNav.jsp" %>
<section id="board-container">
<h1 class="board-title">대여후기</h1>
<form 
	name="boardUpdateFrm" 
	action="<%=request.getContextPath() %>/board/reviewBoardUpdate"
	enctype="multipart/form-data" 
	method="post"
	id="boardUpdateFrm">
	<input type="hidden" name="no" value="<%= reviewBoard.getReviewNo() %>" />
	<table id="tbl-board-view">
		<tr>
			<th>제 목</th>
			<td><input id="title" type="text" name="title" value="<%= reviewBoard.getReviewTitle() %>" required></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>
				<input id="writer" type="text" name="writer" value="<%= reviewBoard.getReviewWriter() %>" readonly/>
			</td>
		</tr>
		<tr>
			<th>이용차량</th>
			<td><input id="car" type="text" name="car" value="<%= reviewBoard.getCarName() %>" readonly/></td>
		</tr>
		<tr>
			<th>점수</th>
			<td>
				<select name="score" id="score" style="border: 1px solid lightgray; margin-left: 30px; width: 87px; height: 47px;">
					<option value="<%= reviewBoard.getScore() %>" selected><%= reviewBoard.getScore() %></option>
					<option value="0.5">0.5점</option>
					<option value="1">1점</option>
					<option value="1.5">1.5점</option>
					<option value="2">2점</option>
					<option value="2.5">2.5점</option>
					<option value="3">3점</option>
					<option value="3.5">3.5점</option>
					<option value="4">4점</option>
					<option value="4.5">4.5점</option>
					<option value="5">5점</option>
				</select>			
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td style="display: flex;">
				<div class="updateFilebox">
				<label for="ex_file">사진</label>			
				<input type="file" name="upFile" id="ex_file">
				</div>
<%
	Attachment attach = reviewBoard.getAttach();
	if(attach != null) {
%>		
				<span id="fname"><%= attach.getOriginalFilename() %></span>
				
				<div id="checkbox">
					<input type="checkbox" name="delFile" id="delFile" value="<%= attach.getNo() %>" />
					<label for="delFile">기존파일삭제</label>
				</div>
<%
	}
%>
			</td>
		</tr>
		<tr>
			<th>내 용</th>
			<td><textarea id="textarea" rows="5" cols="40" name="content"><%= reviewBoard.getReviewContent() %></textarea></td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="button" value="취소" onclick="history.go(-1);" class="cancelBtn"/>
				<input type="submit" value="수정하기" class="writeBtn" />
			</th>
		</tr>
	</table>
</form>
</section>
</div>
<script>
// 첨부파일 관련
<% if(attach != null) { %>
$("[name=upFile]").change(e => {
	// 파일 선택여부
	const $file = $(e.target);
	console.log($file);
	console.log($file.val());
	
	if($file.val() != "") {
		// 파일을 새로 선택한 경우
		$(fname).hide();
		$(delFile)
			.prop("checked", true)
			.hide()
			.next()
			.hide();
	}
	else {
		// 파일선택을 취소한 경우
		$(fname).show();
		$(delFile)
			.prop("checked", false)
			.show()
			.next()
			.show();
	}
});
<% } %>

$(document.boardUpdateFrm).submit(function (){
	const $title = $("[name=title]");
	const $content = $("[name=content]");
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	if(!/^.+$/.test($title.val())){
		alert("제목을 입력하세요.");
		return false;
	}
					   
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	// .(임의의 문자)에는 \n(개행문자)가 포함되지 않는다.
	if(!/^(.|\n)+$/.test($content.val())){
		alert("내용을 입력하세요.");
		return false;
	}
	return true;
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>