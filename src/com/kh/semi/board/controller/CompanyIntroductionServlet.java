package com.kh.semi.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CompanyIntroductionServlet
 */
@WebServlet("/board/companyIntroduction")
public class CompanyIntroductionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// view단 처리 : session의 loginMember 데이터 활용
		request
			.getRequestDispatcher("/WEB-INF/views/board/companyIntroduction.jsp")
			.forward(request, response);
	}

}
