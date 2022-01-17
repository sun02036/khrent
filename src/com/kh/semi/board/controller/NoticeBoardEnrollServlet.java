package com.kh.semi.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.board.model.service.NoticeBoardService;
import com.kh.semi.board.model.vo.NoticeBoard;

/**
 * Servlet implementation class QnaBoardEnrollServlet
 */
@WebServlet("/board/noticeBoardEnroll")
public class NoticeBoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private NoticeBoardService noticeBoardService = new NoticeBoardService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 1. 사용자입력값처리
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		NoticeBoard noticeBoard = new NoticeBoard(0, title, content, null, 0);
		
		System.out.println("noticeBoard@servlet = " + noticeBoard);
		
		// 2. 업무로직
		int result = noticeBoardService.insertNoticeBoard(noticeBoard);
//		String msg = result > 0 ? "게시물 등록 성공!" : "게시물 등록 실패!";
//		session.setAttribute("msg", msg);
		
		// 3. 응답처리 : redirect
		HttpSession session = request.getSession();
		String location = request.getContextPath() + "/board/noticeView?no=" + noticeBoard.getNoticeNo();
		response.sendRedirect(location);
	}

}
