package com.kh.semi.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.service.QuestionBoardService;
import com.kh.semi.board.model.vo.QuestionBoardComment;

/**
 * Servlet implementation class QnaBoardCommentEnroll
 */
@WebServlet("/board/qnaBoardCommentEnroll")
public class QnaBoardCommentEnroll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	QuestionBoardService questionBoardService = new QuestionBoardService();
	
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
		
		
		QuestionBoardComment questionBoardComment = new QuestionBoardComment(0,boardNo, writer,content, null, commentLevel, commentRef);		
		System.out.println("questionBoardComment = " + questionBoardComment);
		//업무로직
		int result = questionBoardService.insertQnaBoardComment(questionBoardComment);
		
		String msg = "";
		
		if(result>0)
			msg = "댓글이 등록되었습니다.";
		else
			msg = "댓글이 등록되지 않았습니다.";
		
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath()+"/board/questionView?no=" + boardNo;
		response.sendRedirect(location);
	}

}
