package com.kh.semi.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.reservation.model.vo.Reservation;

/**
 * Servlet implementation class AjaxAdminFiveRecentReservation
 */
@WebServlet("/admin/ajaxAdminFiveRecentReservation")
public class AjaxAdminFiveRecentReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Reservation> list = adminService.ajaxAdminFiveRecentReservation();
		System.out.println("list@servlet = " + list);
		
		response.setContentType("text/text; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		for(Reservation b : list) {
			out.println(b); //toString 자동 호출
		}
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/admin/ajaxAdminFiveRecentReservation.jsp")
			.forward(request, response);
	}

}
