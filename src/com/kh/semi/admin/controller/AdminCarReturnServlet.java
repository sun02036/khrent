package com.kh.semi.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.reservation.model.vo.Reservation;

/**
 * Servlet implementation class AdminCarReturnServlet
 */
@WebServlet("/admin/adminCarReturn")
public class AdminCarReturnServlet extends HttpServlet {
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
		request.getRequestDispatcher("/WEB-INF/views/admin/adminCarReturn.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String reserNo = request.getParameter("reserNo");      
	    String returnStatus = "Y";
	      
	    Reservation reservation = new Reservation(reserNo, null, null, null, null, null, 0, null, null, null, null, returnStatus);

		System.out.println("reservation@servlet = " + reservation);

		
		int result = adminService.updateReturnStatus(reservation);
		String msg = result > 0 ? "반납 완료" : "다시 처리해주세요.";
		
		if(result > 0) {
			HttpSession session = request.getSession();
			reservation = adminService.selectOneReservation(reserNo);
			session.setAttribute("reservation", reservation);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		String location = request.getContextPath() + "/admin/adminCarReturnList";
		response.sendRedirect(location);
	}

}
