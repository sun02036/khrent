package com.kh.semi.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.service.QuestionBoardService;

/**
 * Servlet implementation class QnaBoardCommentDeleteServlet
 */
@WebServlet("/board/qnaBoardCommentDelete")
public class QnaBoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	QuestionBoardService questionBoardService = new QuestionBoardService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			// 1. 사용자입력값 처리
			int no = Integer.parseInt(request.getParameter("no"));
			
			// 2. 업무로직
			int result = questionBoardService.deleteQnaBoardComment(no);
			String msg = result > 0 ? "댓글이 삭제되었습니다." : "댓글 삭제에 실패했습니다.";
			request.getSession().setAttribute("msg", msg);
			
			// 3. 리다이렉트
			String location = request.getHeader("Referer");   
			response.sendRedirect(location);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;   // WAS에게 던진다. (WAS에게 예외페이지 연결알림)
		}
	}

}
