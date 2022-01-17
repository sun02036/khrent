package com.kh.semi.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.service.ReviewBoardService;
import com.kh.semi.board.model.vo.ReviewBoardComment;

/**
 * Servlet implementation class QnaBoardCommentEnroll
 */
@WebServlet("/board/reviewBoardCommentEnroll")
public class ReviewBoardCommentEnroll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ReviewBoardService reviewBoardService = new ReviewBoardService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력값 가져오기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String writer = request.getParameter("writer");
		int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
		int commentRef = Integer.parseInt(request.getParameter("commentRef"));
		String content = request.getParameter("content");
		
		
		ReviewBoardComment reviewBoardComment = new ReviewBoardComment(0, boardNo, writer,content, null, commentLevel, commentRef);		
		System.out.println("reviewBoardComment = " + reviewBoardComment);
		//업무로직
		int result = reviewBoardService.insertReviewBoardComment(reviewBoardComment);
		
		String msg = "";
		
		if(result > 0)
			msg = "댓글이 등록되었습니다.";
		else
			msg = "댓글이 등록되지 않았습니다.";
		
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath()+"/board/reviewBoardView?no=" + boardNo;
		response.sendRedirect(location);
	}

}
