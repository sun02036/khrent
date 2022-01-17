package com.kh.semi.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.MvcUtils;
import com.kh.semi.board.model.service.NoticeBoardService;
import com.kh.semi.board.model.vo.NoticeBoard;
import com.kh.semi.board.model.vo.QuestionBoard;

/**
 * Servlet implementation class QuestionDetailViewServlet
 */
@WebServlet("/board/noticeView")
public class NoticeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	NoticeBoardService noticeBoardService = new NoticeBoardService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 1. 파라미터 글 번호
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 2. 비즈니스 로직 호출
		// 읽음여부 확인(cookie)
		Cookie[] cookies = request.getCookies();
		boolean hasRead = false;
		String noticeBoardValue = "";
		
		if(cookies != null) {
			for(Cookie c : cookies) {
				String name = c.getName();
				String value = c.getValue();
				System.out.println(name + " : " + value);
				
				if("board".equals(name)) {
					noticeBoardValue = value;
					// 현재 게시글 읽음여부
					if(value.contains("|" + no + "|")) {
						hasRead = true;
					}
					break;
				}
			}
		}
		System.out.printf("hasRead = %b, noticeBoardValue = %s%n", hasRead, noticeBoardValue);
		int result = 2;
		// 게시글을 처음 읽는 경우
		if(!hasRead) {
			// 게시글 Cookie
			Cookie cookie = new Cookie("board", noticeBoardValue + "|" + no + "|");
			cookie.setMaxAge(365 * 24 * 60 * 60);
			cookie.setPath(request.getContextPath() + "/board/noticeView"); // 해당 요청시만 cookie전송
			response.addCookie(cookie);
			
			//조회수 증가
			result = noticeBoardService.updateNoticeBoardReadCount(no);			
		}
		System.out.println("조회수 등록 여부 : " + result);
		
		// 게시글 하나 가져오기
		NoticeBoard noticeBoard = noticeBoardService.selectOneBoard(no);
		System.out.println("noticeBoard@servlet = "+ noticeBoard);
		
		//게시글 가져오기에 실패한경우
		if(noticeBoard == null){
			request.getSession().setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
			response.sendRedirect(request.getContextPath() + "/board/notice");
			return;
		}
		
		// XSS공격대비 
		// cross-site script공격. 악성코드를 웹페이지삽입하여 클라이언트의 개인정보탈취하는 공격법
		String content = MvcUtils.escapeHtml(noticeBoard.getNoticeContent());
		
		// 개행문자 br태그 변환처리
		content = MvcUtils.convertLineFeedToBr(content);
		noticeBoard.setNoticeContent(content);
		System.out.println(content);
		
		
		request.setAttribute("noticeBoard", noticeBoard);
		request.getRequestDispatcher("/WEB-INF/views/board/noticeView.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
