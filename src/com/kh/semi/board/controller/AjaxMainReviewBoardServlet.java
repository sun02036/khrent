package com.kh.semi.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.service.ReviewBoardService;
import com.kh.semi.board.model.vo.ReviewBoard;

/**
 * Servlet implementation class AjaxMainReviewBoardServlet
 */
@WebServlet("/board/ajaxMainReviewBoard")
public class AjaxMainReviewBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ReviewBoardService reviewBoardService = new ReviewBoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ReviewBoard> list = reviewBoardService.ajaxMainReviewBoardList();
		System.out.println("ReviewBoard@servlet = " + list);
		
		response.setContentType("text/text; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		for(ReviewBoard b : list) {
			out.println(b); //toString 자동 호출
		}
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/board/ajaxMainReviewBoardList.jsp")
			.forward(request, response);
	}

}
