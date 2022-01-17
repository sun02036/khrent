<%@page import="java.util.List"%>
<%@page import="com.kh.semi.board.model.vo.QuestionBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/boardForm.css" />

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
<div class="wrap clearfix" style="width: 1417px; margin:auto;">
	<section id="board-container">
	<h1 class="board-title">커뮤니티</h1>
	<form
		name="boardEnrollFrm"
		action="<%=request.getContextPath() %>/board/communityBoardEnroll" 
		method="post"
		enctype="multipart/form-data" id="boardEnrollFrm">
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
