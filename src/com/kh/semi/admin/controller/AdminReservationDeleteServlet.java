package com.kh.semi.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.reservation.model.vo.Reservation;

/**
 * Servlet implementation class AdminReservationDeleteServlet
 */
@WebServlet("/admin/adminReservationDelete")
public class AdminReservationDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reserNo = request.getParameter("reserNo");
		
		Reservation reservation = adminService.selectOneReservation(reserNo);
		
		System.out.println("reservation@servlet = " + reservation);
		
		// view단 연결
		request.setAttribute("reservation", reservation);
		request.getRequestDispatcher("/WEB-INF/views/admin/adminReservationDelete.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String reserNo = request.getParameter("reserNo");
		
		System.out.println("reserNo@AdminReservationDeleteServlet = " + reserNo);
		
		int result = adminService.deleteReservation(reserNo);
		
		HttpSession session = request.getSession();
		
		if(result > 0) {
			session.setAttribute("msg", "예약 취소 완료");
			response.sendRedirect(request.getContextPath() + "/admin/adminTotalReserList");
		}
		else {
			session.setAttribute("msg", "다시 시도해주세요.");
			response.sendRedirect(request.getContextPath() + "/admin/adminTotalReserList");
		}
	}

}
