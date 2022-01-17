package com.kh.semi.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.admin.model.service.AdminService;

/**
 * Servlet implementation class AjaxMemberPageReservationCountServlet
 */
@WebServlet("/admin/ajaxMemberPageReservationCount")
public class AjaxMemberPageReservationCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		System.out.println(memberId);
		
		int myReservation = adminService.ajaxMemberPageReservationCount(memberId);
		System.out.println("myReservation@servlet = " + myReservation);
		
		response.setContentType("text/text; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		request.setAttribute("myReservation", myReservation);
		request.getRequestDispatcher("/WEB-INF/views/member/ajaxMemberPageReservationCount.jsp")
			.forward(request, response);
	}

}
