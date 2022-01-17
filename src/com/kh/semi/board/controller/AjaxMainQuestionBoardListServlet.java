package com.kh.semi.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.service.QuestionBoardService;
import com.kh.semi.board.model.vo.QuestionBoard;

/**
 * Servlet implementation class HomeQnaBoardListServlet
 */
@WebServlet("/board/ajaxMainQuestionBoard")
public class AjaxMainQuestionBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	QuestionBoardService questionBoardService = new QuestionBoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<QuestionBoard> list = questionBoardService.ajaxMainQuestionBoardList();
		System.out.println("QnaBoard@servlet = " + list);
		
		response.setContentType("text/text; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		for(QuestionBoard b : list) {
			out.println(b); //toString 자동 호출
		}
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/board/ajaxMainQuestionBoardList.jsp")
			.forward(request, response);
	}

}
