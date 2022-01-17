package com.kh.semi.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kh.semi.board.model.service.CommunityBoardService;
import com.kh.semi.board.model.vo.CommunityBoard;
import com.kh.semi.board.model.vo.CommunityBoardComment;

/**
 * Servlet implementation class CommunityDetailViewServlet
 */
@WebServlet("/board/boardView")
public class CommunityViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CommunityBoardService boardService = new CommunityBoardService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩
		request.setCharacterEncoding("utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 읽은 염부 확인(cookie)
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
					// 현재 게시글 읽음처리
					if(value.contains("|"+ no + "|")) {
						hasRead = true;
					}
					break;
				}
			}
		}
		
		System.out.printf("hasRead = %b, boardValue = %s%n", hasRead, boardValue);
		int result = 2;
		// 게시글을 처음 읽는 경우
		if(!hasRead) {
			// 게시글 Cookie
			Cookie cookie = new Cookie("board", boardValue + "|" + no + "|" );
			cookie.setMaxAge(365 * 24 *60 *60);
			cookie.setPath(request.getContextPath() + "/board/boardView");
			response.addCookie(cookie);
			
			// 조회수 증가
			result = boardService.updateReadCount(no);		
		}
		
		System.out.println("조회수 등록 여부 ::" + result);
				
		CommunityBoard communityBoard = boardService.selectOneBoard(no);
		System.out.println("selectOneBoard@servlet = "+ communityBoard);
		
		// 댓글목록 가져오기
		List<CommunityBoardComment> commentList = boardService.selectCommentList(no);
		System.out.println("commentList@servlet = " + commentList);
		
		request.setAttribute("list", commentList);
		request.setAttribute("communityBoard", communityBoard);
		request.getRequestDispatcher("/WEB-INF/views/board/communityView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
