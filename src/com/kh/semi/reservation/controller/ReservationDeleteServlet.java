package com.kh.semi.reservation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.reservation.model.service.ReservationService;

/**
 * Servlet implementation class ReservationDeleteServlet
 */
@WebServlet("/reservation/ReservationDelete")
public class ReservationDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ReservationService reservationService = new ReservationService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String reserNo = request.getParameter("reserNo");
		String memberId = request.getParameter("memberId");
		System.out.println("예약취소 시 예약번호?@ReservationDeleteServlet = " + reserNo);
		
		// 2. 업무로직
		int result = reservationService.deleteRservation(reserNo);
		
		
		// 3. view단 처리 . 예약내역으로 리다이렉트
		HttpSession session = request.getSession();
		String location = request.getContextPath() + "/member/myPage?memberId=" + memberId;
		response.sendRedirect(location);
	}

}
