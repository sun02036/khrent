<%@page import="com.kh.semi.board.model.vo.Attachment"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.semi.board.model.vo.CommunityBoardComment"%>
<%@page import="com.kh.semi.board.model.vo.CommunityBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board/board.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board/boardView.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />

<%
	CommunityBoard communityBoard = (CommunityBoard)request.getAttribute("communityBoard");
	String msg = (String)session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");
	Attachment attachment = communityBoard.getAttach();
	List<CommunityBoardComment>commentList = (List<CommunityBoardComment>)request.getAttribute("list");
	boolean editable = loginMember != null && (communityBoard.getWriter().equals(loginMember.getMemberId()) || MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole()));
%>

<div class="wrap clearfix" style="width: 1417px; margin:auto;">


<div class="board-container">
	<h1 class="board-title">커뮤니티</h1>
<%
	if(editable) {
%>		
<div id="writing-container">
		<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
			<input class="change" type="button" value="수정하기" onclick="updateBoard()">
			<input class="change" type="button" value="삭제하기" onclick="deleteBoard()">
</div>
<%
	}
%>				
	<table id="tbl-board">
		<thead>
			<tr><th colspan="4"><%=communityBoard.getTitle()%></th></tr>
		</thead>
		<tbody>
			<tr style="border-top: 1px solid black;">
				<td colspan="2" style="width: 600px;"> 작성자: <%=communityBoard.getWriter()%> </td>
				<td> 작성일 : <%=communityBoard.getRegDate()%></td>
				<td> 조회수 : <%=communityBoard.getReadCount() %></td>
			</tr>
<%
	if(attachment != null) {
%>			
			<tr>
				<td style="width: 70px;">첨부파일 </td>
				<td colspan = "3">
				<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
				<img alt="첨부파일" src="https://i.ibb.co/wM6QjzV/1.png" width=16px>
				<a class="fileName" href="<%= request.getContextPath() %>/communityboard/fileDownload?no=<%= attachment.getNo() %>"><%= attachment.getOriginalFilename() %></a>			
				</td>
			</tr>
<%
	}
%>
			<tr style="height: 300px;">
				<td id="questionViewContent" colspan="4">
<%
	if(attachment != null) {
%>	
					<br />
					<img src="<%= request.getContextPath() %>/upload/board/<%= attachment.getRenamedFilename() %>" height="400px;" alt="" />
					<br />
<%
	}
%>	
				<%=communityBoard.getContent()%>
				</td>	
			</tr>
		</tbody>
		<tfoot>
			<tr><td id="comment" colspan = "4"></td></tr>
		</tfoot>
	</table>
	<br />
		
		<div class="buttons">
		</div>
		<br />
		
		<div class="comment-container">
		<table id="tbl-comment">
	<%
	if(commentList != null && !commentList.isEmpty()){
		for(CommunityBoardComment cb : commentList){
			if(cb.getCommentLevel() == 1){
	%>
			<%-- 댓글 --%>
			<tr class = "level1">
				<td>
					<sub class="comment-writer"><%=cb.getWriter() %></sub>
					<sub class="comment-date"><%=cb.getRegDate() %></sub>	
					<br />
					<%-- 댓글 내용 --%>
					<%= cb.getContent() %>			
				</td>
				<td>
					<button class="btn-reply" value="<%= cb.getNo() %>">답글</button>
<%
				// 댓글 작성자와 관리자계정에게만 삭제 버튼이 보인다.
				if(loginMember != null && (cb.getWriter().equals(loginMember.getMemberId()) || MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole()))) {
%>	
					<button class="btn-delete" value="<%= cb.getNo() %>">삭제</button>
<%
				}
%>
				</td>
			</tr>
	
	<%
			}
			else{	
	%>
			<%-- 대댓글(답글) --%>
			<tr class="level2">
				<td>
					<sub class="comment-writer"><%= cb.getWriter() %></sub>
					<sub class="comment-date"><%= cb.getRegDate() %></sub>
					<br />
					<%-- 댓글 내용 --%>
					<%= cb.getContent() %>
				</td>
				<td>
<%
				//댓글 작성자와 관리자계정에게만 삭제 버튼이 보인다.
				if(loginMember != null && (cb.getWriter().equals(loginMember.getMemberId()) || MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole()))) {
%>				
					<button class="btn-delete" value="<%= cb.getNo() %>">삭제</button>
<%
				}
%>
				</td>
			</tr>
	
	<%			
		}
	}
}
%>		

		</table>
		<div class="comment-editor">
			<form 
				action="<%= request.getContextPath() %>/board/communityBoardCommentEnroll" 
				name="communityBoardEnroll" 
				method="POST">
				<textarea id="insertText" name="content" cols="60" rows="3"></textarea>
				<button id="btn-insert">등록</button>
				
				<input type="hidden" name="commentLevel" value="1" />
				<input type="hidden" name="writer" value="<%=loginMember != null ? loginMember.getMemberId() : "" %>" />
				<input type="hidden" name="boardNo" value="<%=communityBoard.getNo() %>" />
				<input type="hidden" name="commentRef" value="0" />
			</form>
		</div>
		</div>
	</div>
	</div>
	
	<!-- 댓글 삭제용 폼 -->
		<form 
			action="<%= request.getContextPath() %>/board/communityBoardCommentDelete" 
			name="communityBoardCommentDelFrm"
			method="POST">
			<input type="hidden" name="no" />
			<input type="hidden" name="boardNo" value="<%= communityBoard.getNo() %>"/>
		</form>
		
<br /><br />

<!-- 게시글 삭제용 폼 -->
<form action="<%= request.getContextPath() %>/board/communityBoardDelete" name="deleteCommunityBoardFrm" method="POST">
	<input type="hidden" name="no" value="<%= communityBoard.getNo() %>" />
</form>

<script>
/* 게시물 업데이트 & 수정 */
const updateBoard = () => {
	location.href = "<%= request.getContextPath() %>/board/communityBoardUpdate?no=<%= communityBoard.getNo() %>";
};

const deleteBoard = () => {
	if(confirm("정말 이 게시물을 삭제하시겠습니까?")) {
		document.deleteCommunityBoardFrm.submit();
	}
}



<% if(msg !=null){ %>
	alert("<%=msg%>");
<% } %>

$(document.communityBoardEnroll).submit((e) => {
	<% if(loginMember == null){%> 
		loginAlert();
		return false;
	<% } %>	
});

const loginAlert = () => {
	alert("로그인 후 이용 바랍니다.");
	$("#login").get(0).click();
}


// 대댓글
$(".btn-reply").click((e) => {
	const commentRef = $(e.target).val();
	const tr = `<tr>
	<td colspan="2" style="text-align: left;">
		<form 
			action="<%= request.getContextPath() %>/board/communityBoardCommentEnroll" 
			method="POST">
			<textarea id="insertText2" name="content" cols="60" rows="1"></textarea>
			<button class="btn-insert2">등록</button>
			
			<input type="hidden" name="commentLevel" value="2" />
			<input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />
			<input type="hidden" name="boardNo" value="<%= communityBoard.getNo() %>" />
			<input type="hidden" name="commentRef" value="\${commentRef}" />	
		</form>
	</td>
</tr>`;
console.log(tr);
	
	const $trOfBtn = $(e.target).parent().parent();
	// $trOfBtn.after(tr);
	$(tr)
		.insertAfter($trOfBtn)
		.find("form")
		.submit((e) => {
<% if(loginMember == null){%> 
			loginAlert();
			return false;
<% } %>	
			// 내용검사
			// const textarea = $("[name=content]", document.boardCommentFrm);	
			const $textarea = $("[name=content]", e.target);	
			
			if(!/^(.|\n)+$/.test($textarea.val())){
				alert("댓글 내용을 작성해주세요.");
				$textarea.focus();
				return false;
			}
		})
		.find("[name=content]")
		.focus();
	
	// 현재버튼의 handler 제거
	$(e.target).off('click');
});

//댓글삭제버튼
$(".btn-delete").click(function(){
	if(confirm("해당 댓글을 삭제하시겠습니까?")){
		var $frm = $(document.communityBoardCommentDelFrm);
		var no = $(this).val();
		$frm.find("[name=no]").val(no);
		$frm.submit();
	}
});	

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>