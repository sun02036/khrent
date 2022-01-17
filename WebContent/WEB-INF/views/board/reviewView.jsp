<%@page import="com.kh.semi.board.model.vo.ReviewBoardComment"%>
<%@page import="com.kh.semi.board.model.vo.ReviewBoard"%>
<%@page import="com.kh.semi.board.model.vo.Attachment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/customerCenterNav.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/boardView.css" />
<%
	ReviewBoard reviewBoard = (ReviewBoard)request.getAttribute("reviewBoard");
	System.out.println("리뷰보드 보기 = " + reviewBoard.getReservNo());
	System.out.println("reviewBoard.jsp = " + reviewBoard);
	Attachment attachment = reviewBoard.getAttach();
	System.out.println(attachment);
	boolean editable = 
			loginMember != null && 
			(reviewBoard.getReviewWriter().equals(loginMember.getMemberId()) || MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole()));
	boolean editable_ = 
			loginMember != null && MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole());
	
	List<ReviewBoardComment> commentList = (List<ReviewBoardComment>) request.getAttribute("commentList");
	System.out.println("commentList = " + commentList);
%>

<script>
<% 
	String msg = (String) session.getAttribute("msg");
	
	// 그냥 두면 페이지 새로 띄울때마다 alert 메시지가 계속 뜨기 때문에, 한번만 할 수 있도록 메시지 띄우고 나면 msg가 삭제되도록 한다.
	if(msg != null) session.removeAttribute("msg");
%>

<% 
	if(msg != null) { 
%>
	// 사용자 메시지 전달
	alert("<%= msg %>");
<% 
	} 
%>
</script>


<div class="wrap clearfix" style="width: 1417px; margin:auto;">
	<%@ include file="/WEB-INF/views/board/customerCenterNav.jsp" %>
	<div class="board-container">
	<h1 class = "board-title">대여후기</h1>
<%
	if(editable) {
%>		
			<div id="writing-container">
				<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
				<input class="change" type="button" value="수정하기" onclick="updateBoard()">
			</div>
<%
	}
%>		
		<table id="tbl-board">
			<thead>
				<tr><th colspan="6"><%=reviewBoard.getReviewTitle()%></th></tr>
			</thead>
			<tbody>
				<tr style="border-top: 1px solid black;">
					<td colspan="2" style="width: 600px;"> 작성자 : <%=reviewBoard.getReviewWriter() %></td>
					<td> 작성일 : <%=reviewBoard.getRegDate()%></td>
					<td> 조회수 : <%=reviewBoard.getReadCount() %></td>
					<td> 이용차량 : <%=reviewBoard.getCarName() %></td>
					<td> 점수 : <%=reviewBoard.getScore() %></td>
				</tr>
				<tr style="text-align: center;">
					<td style="width: 70px;">첨부파일</td>
					<td colspan="3">
<%
	if(attachment != null) {
%>		
				<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
				<img alt="첨부파일" src="https://i.ibb.co/wM6QjzV/1.png" width=16px>
				<a class="fileName" href="<%= request.getContextPath() %>/reviewboard/fileDownload?no=<%= attachment.getNo() %>"><%= attachment.getOriginalFilename() %></a>
<%
	}
%>						
					</td>
				</tr>
				
				<tr>
					<td colspan = "4" id="questionViewContent" style="height: 300px;">
<%
	if(attachment != null) {
%>
					<br />
					<img src="<%= request.getContextPath() %>/upload/board/<%= attachment.getRenamedFilename() %>" height="400px;" alt="" />
					<br />
<%
	}
%>
				<%=reviewBoard.getReviewContent()%>
					</td>
				</tr>
			</tbody>
		</table>
		
		<br />
		

		<br />
		
<!-- 여기부터 댓글 -->
		
		<div class="comment-container">

			
			<table id="tbl-comment">
<%
	if(commentList != null && !commentList.isEmpty()) {   // commentList가 있고, 내용도 있을 경우
		for(ReviewBoardComment bc : commentList) {
			if(bc.getCommentLevel() == 1) {
%>
				<%-- 댓글 --%>
				<tr class="level1">
					<td>
						<sub class="comment-writer"><%= bc.getWriter() %></sub>
						<sub class="comment-date"><%= bc.getRegDate() %></sub>
						<br />
						<%-- 댓글 내용 --%>
						<%= bc.getContent() %>
					</td>
					<td>
<%
				// 댓글 작성자와 관리자계정에게만 대댓글 추가버튼, 삭제 버튼이 보인다.
				if(loginMember != null && (bc.getWriter().equals(loginMember.getMemberId()) || MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole()))) {
%>					
					<button class="btn-reply" value="<%= bc.getNo() %>">답글</button>
					<button class="btn-delete" value="<%= bc.getNo() %>">삭제</button>
<%
				}
%>					
					</td>
				</tr>

<% 				
			}
			else {
%>
				<%-- 대댓글 --%>
				<tr class="level2">
					<td>
						<sub class="comment-writer"><%= bc.getWriter() %></sub>
						<sub class="comment-date"><%= bc.getRegDate() %></sub>
						<br />
						<%-- 댓글 내용 --%>
						<%= bc.getContent() %>
					</td>
					<td>
<%
				//댓글 작성자와 관리자계정에게만 삭제 버튼이 보인다.
				if(loginMember != null && (bc.getWriter().equals(loginMember.getMemberId()) || MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole()))) {
%>				
					<button class="btn-delete" value="<%= bc.getNo() %>">삭제</button>
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
<%
	if(editable_) {
%>	
	<!-- 관리자만 댓글작성 폼이 보인다. -->
			<div class="comment-editor">
				<form 
					action="<%= request.getContextPath() %>/board/reviewBoardCommentEnroll" 
					name="reviewBoardCommentFrm" 
					method="post">
					<textarea id="insertText" name="content" cols="60" rows="3"></textarea>
					<button id="btn-insert">등록</button>
					
					<input type="hidden" name="commentLevel" value="1" />
					<input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />
					<input type="hidden" name="boardNo" value="<%= reviewBoard.getReviewNo() %>" />
					<input type="hidden" name="commentRef" value="0" />
				</form>
			</div>
<%
	}
%>					
		
		</div>
		<!-- 댓글 삭제용 폼 -->
		<form 
			action="<%= request.getContextPath() %>/board/reviewBoardCommentDelete" 
			name="reviewBoardCommentDelFrm"
			method="POST">
			<input type="hidden" name="no" value="<%= reviewBoard.getReviewNo() %>"/>
		</form>
	
	</div> <!-- </div> .board-container -->
</div> <!-- </div> .wrap clearfix -->	
	
<!-- 게시글 삭제용 폼 -->
<%-- <form action="<%= request.getContextPath() %>/board/reviewBoardDelete" name="deleteReviewBoardFrm" method="POST">
	<input type="hidden" name="no" value="<%= reviewBoard.getReviewNo() %>" />
	<input type="hidden" name="reserveNo" value="<%= reviewBoard.getReservNo() %>"/>
	<input type="hidden" name="carName" value="<%= reviewBoard.getCarName() %>"/>
</form> --%>
	
<script>
const updateBoard = () => {
	location.href = "<%= request.getContextPath() %>/board/reviewBoardUpdate?no=<%= reviewBoard.getReviewNo() %>";
};

/* const deleteBoard = () => {
	if(confirm("정말 이 게시물을 삭제하시겠습니까?")) {
		document.deleteReviewBoardFrm.submit();
	}
} */



/* 대댓글 등록 버튼 */
$(".btn-reply").click((e) => {
	const commentRef = $(e.target).val();
	const tr = `<tr>
	<td colspan="2" style="text-align: left;">
		<form 
			action="<%= request.getContextPath() %>/board/reviewBoardCommentEnroll" 
			method="post">
			<textarea name="content" cols="60" rows="1"></textarea>
			<button class="btn-insert2">등록</button>
			
			<input type="hidden" name="commentLevel" value="2" />
			<input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />
			<input type="hidden" name="boardNo" value="<%= reviewBoard.getReviewNo() %>" />
			<input type="hidden" name="commentRef" value="\${commentRef}" />
		</form>
	</td>
</tr>`

	console.log(tr);
	console.log($(tr));
	
	const $trOfBtn = $(e.target).parent().parent();
	// $trOfBtn.after(tr);
	/* console.log($trOfBtn);  */
	/* console.log($(tr).find("form")); */ 
	$(tr)
		.insertAfter($trOfBtn)
		.find("form")   
		.submit((e) => {
<% 
	if(loginMember == null) { 
%>
			loginAlert();
			return false;
<% 
	} 
%>	

			// 내용검사
			const $textarea = $("[name=content]", e.target); 

			if(!/^(.|\n)+$/.test($textarea.val())) {
				alert("댓글 내용을 작성해주세요.");
				$textarea.focus();
				return false;
			}
		})
		.find("[name=content]")
		.focus();
		
	// 현재버튼의 handler 제거(그냥 두면 대댓글 버튼 클릭할때마다 입력폼이 계속 추가됨)
	$(e.target).off('click');
	
});


// 댓글삭제버튼
$(".btn-delete").click(function(){
	if(confirm("해당 댓글을 삭제하시겠습니까?")){
		var $frm = $(document.reviewBoardCommentDelFrm);
		var no = $(this).val();
		$frm.find("[name=no]").val(no);
		$frm.submit();
	}
});	


<!-- qnaBoardCommentFrm 아래의 [name=content]를 찾는다 -->
$("[name=content]", document.reviewBoardCommentFrm).focus(e => {
<% if(loginMember == null) { %>
	loginAlert();
<% } %>	
});


$(document.reviewBoardCommentFrm).submit((e) => {   
<% if(loginMember == null) { %>
	loginAlert();
	return false;
	
<% } %>	

	// 내용검사
	const $textarea = $("[name=content]", e.target); 

	if(!/^(.|\n)+$/.test($textarea.val())) {
		alert("댓글 내용을 작성해주세요.");
		$textarea.focus();
		return false;
	}
	
});

const loginAlert = () => {
	alert("로그인 후 이용 바랍니다.");
	$("#login").get(0).click();
}

</script>	
	
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	
