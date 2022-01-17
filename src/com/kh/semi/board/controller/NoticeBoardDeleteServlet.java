package com.kh.semi.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.service.NoticeBoardService;
import com.kh.semi.board.model.vo.NoticeBoard;

/**
 * Servlet implementation class NoticeBoardDeleteServlet
 */
@WebServlet("/board/noticeBoardDelete")
public class NoticeBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeBoardService noticeBoardService = new NoticeBoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.사용자 입력값
		int no = Integer.parseInt(request.getParameter("no"));
		
		//2.업무로직
		NoticeBoard noticeBoard = noticeBoardService.selectOneBoard(no);
		
		
		int result = noticeBoardService.deleteNoticeBoard(no);
		String msg = result > 0 ? "게시물이 삭제 되었습니다." : "게시물 삭제에 실패했습니다.";
		
		//3.사용자메세지 및 redirect처리
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/board/notice");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
