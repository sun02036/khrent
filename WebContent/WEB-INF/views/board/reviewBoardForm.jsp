<%@page import="com.kh.semi.reservation.model.vo.Reservation"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.semi.board.model.vo.QuestionBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/customerCenterNav.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/boardForm.css" />
<%
	List<Reservation> list = (List<Reservation>) request.getAttribute("list");
%>
<script>
/**
* boardEnrollFrm 유효성 검사
*/
function boardValidate(e){
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
}

$(() => {
	$(document.boardEnrollFrm).submit(boardValidate);
});
</script>
<div class="wrap clearfix" style="width: 1417px; margin:auto; display: flex;">
	<%@ include file="/WEB-INF/views/board/customerCenterNav.jsp" %>
	<section id="board-container">
	<h1 class="board-title">대여후기</h1>
	<form
		name="boardEnrollFrm"
		action="<%=request.getContextPath() %>/board/reviewBoardEnroll" 
		method="post"
		enctype="multipart/form-data"
		id="boardEnrollFrm">
		<table id="tbl-board-view">
		<tr>
			<th>제 목</th>
			<td><input id="title" type="text" name="title" required></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>
				<input id="writer" type="text" name="writer" value="<%= loginMember.getMemberId() %>" readonly/>
			</td>
		</tr>
<%
	if(list != null) {
		for(Reservation reservation : list) {
%>
		<tr>
			<th>이용차량</th>
			<td>
				<input id="car" type="text" name="carName" value="<%= reservation.getCarName() %>" readonly/>
				<input type="hidden" name="reserNo" value="<%= reservation.getReserNo() %>" />
			</td>
		</tr>
<%
		}
	}
%>
		<tr>
			<th>점수</th>
			<td style="text-align: left;">
				<select name="score" id="score">
					<option value="" selected>선택</option>
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
			<td>			
				<div class="filebox">
				<input type="file" name="upFile" id="ex_file">
				</div>
			</td>
		</tr>
		<tr>
			<th>내 용</th>
			<td><textarea id="textarea" rows="5" cols="40" name="content"></textarea></td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="submit" value="등록하기" class="writeBtn">
			</th>
		</tr>
	</table>
	</form>
	</section>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
