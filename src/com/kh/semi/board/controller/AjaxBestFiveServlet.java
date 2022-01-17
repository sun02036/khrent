package com.kh.semi.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.board.model.vo.NoticeBoard;
import com.kh.semi.reservation.model.vo.Car;

/**
 * Servlet implementation class AjaxBestFiveServlet
 */
@WebServlet("/board/ajaxBestFive")
public class AjaxBestFiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
		AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Car> list = adminService.ajaxBestFiveServlet();
		System.out.println("Car@servlet = " + list);
		
		response.setContentType("text/text; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		for(Car b : list) {
			out.println(b); //toString 자동 호출
		}
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/board/ajaxBestFive.jsp")
			.forward(request, response);
	}

}
