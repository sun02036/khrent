package com.kh.semi.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.vo.Attachment;
import com.kh.semi.common.MvcFileRenamePolicy;
import com.kh.semi.board.model.service.NoticeBoardService;
import com.kh.semi.board.model.vo.NoticeBoard;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class NoticeBoardUpdateServlet
 */
@WebServlet("/board/noticeBoardUpdate")
public class NoticeBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeBoardService noticeBoardService = new NoticeBoardService(); // 의존객체
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 2.업무로직
		NoticeBoard noticeBoard = noticeBoardService.selectOneBoard(no);
		System.out.println("noticeBoard@servlet = " + noticeBoard);
		
		// 3.view단 위임
		request.setAttribute("noticeBoard", noticeBoard);
		request
			.getRequestDispatcher("/WEB-INF/views/board/noticeBoardUpdate.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 사용자입력
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		NoticeBoard noticeBoard = new NoticeBoard(no, title, content, null, 0);
		
		System.out.println("noticeBoard@servlet = " + noticeBoard);
		
		// 2. 업무로직 
		int result = 0;
		
		// 게시물 수정 + 첨부파일 등록
		result = noticeBoardService.updateNoticeBoard(noticeBoard);
		String msg = result > 0 ? "게시물이 수정되었습니다." : "게시물 수정에 실패했습니다.";
		
		// 3. redirect
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath() + "/board/noticeView?no=" + no;
		response.sendRedirect(location);
				
	}

}
