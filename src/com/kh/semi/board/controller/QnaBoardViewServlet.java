package com.kh.semi.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.service.QuestionBoardService;
import com.kh.semi.board.model.vo.QuestionBoard;
import com.kh.semi.board.model.vo.QuestionBoardComment;
import com.kh.semi.common.MvcUtils;
import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;

/**
 * Servlet implementation class QuestionDetailViewServlet
 */
@WebServlet("/board/questionView")
public class QnaBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private QuestionBoardService questionBoardService = new QuestionBoardService();
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		try {
			// 1. 사용자 입력값 처리
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println("no@QnaBoardViewServlet = " + no);
			
			
			// 2. 업무로직 : 
			// 읽음 여부 확인(Cookie)
			Cookie[] cookies = request.getCookies();
			boolean hasRead = false;
			String boardValue = "";
			
			if(cookies != null) {
				for(Cookie c : cookies) {
					String name = c.getName();
					String value = c.getValue();
					System.out.println(name + " : " + value);
					
					if("board".equals(name)) {
						boardValue = value;
						// 현재 게시글 읽음여부
						if(value.contains("|" + no + "|")) {
							hasRead = true;
						}
						break;
					}
				}
			}
			
			// 게시글을 처음 읽는 경우
			if(!hasRead) {
				// 게시글 Cookie
				Cookie cookie = new Cookie("board", boardValue + "|" + no + "|");   // 그냥 no만 적으면 혼동된다고? 하시면서 패딩(|)을 넣었다고 하셨다.
				cookie.setMaxAge(354 * 24 * 60 * 60);   // 1년
				cookie.setPath(request.getContextPath() + "/board/questionView");   // 해당 요청시에만 이 cookie를 전송하겠다는 뜻
				response.addCookie(cookie);
				
				
				// 조회수 1 증가 (이게 아래의 게시글 불러오기보다 먼저 처리되어야 글을 클릭했을때 1 증가한 조회수로 보이게 된다.
				int result = questionBoardService.updateQnaReadCount(no);
			}
		
			
			// 댓글목록 가져오기 
			List<QuestionBoardComment> commentList = questionBoardService.selectQnaCommentList(no);
			System.out.println("commentList@QnaBoardViewServlet = " + commentList);
			
			
			// 댓글 작성자 중에 관리자가 있으면 이 글의 답변여부를 Y로 업데이트
			// 모든 댓글의 작성자를 가져와서 회원권한이 어드민이면 체크용 변수를 y로 변경
			String isThereAdmin = "N";
			for(QuestionBoardComment bc : commentList) {
				String writer = bc.getWriter();
				Member member = memberService.selectOneMember(writer);
				if(MemberService.ADMIN_ROLE.equals(member.getMemberRole())) {
					isThereAdmin =  "Y";
				}
			}

			int result2 = questionBoardService.updateQnaAnswerStatus(isThereAdmin, no);
			
			
			// 전달된 no를 이용해서 게시글 불러오기
			QuestionBoard questionBoard = questionBoardService.selectOneQnaBoard(no);
			System.out.println("questionBoard@servlet = "+ questionBoard);
			
			
			// XSS 공격 대비
			// cross-site script 공격. 악성코드를 웹페이지에 삽입하여 클라이언트의 개인정보를 탈취하는 공격법
			// < > 이것들을 문자열로 써서 태그로 처리되지 않게 한다. &lt &gt 이것들은 vscode에서 html수업할때 배운 기억이 난다.
			String content = MvcUtils.escapeHtml(questionBoard.getQna_content());
			
			// 개행문자 br태그 변환처리
			content = MvcUtils.convertLineFeedToBr(content);
			
			questionBoard.setQna_content(content);
			
			// 3. view단 처리 위임
			request.setAttribute("questionBoard", questionBoard);
			request.setAttribute("commentList", commentList);
			request.getRequestDispatcher("/WEB-INF/views/board/questionView.jsp").forward(request, response);
		

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
}
