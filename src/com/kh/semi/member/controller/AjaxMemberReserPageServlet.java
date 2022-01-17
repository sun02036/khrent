package com.kh.semi.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.reservation.model.vo.Reservation;

/**
 * Servlet implementation class AjaxMemberReserPageServlet
 */
@WebServlet("/member/ajaxMemberReserPage")
public class AjaxMemberReserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		System.out.println(memberId);
		
		List<Reservation> list = memberService.selectMyReservationList(memberId);
		System.out.println("Reservation@servlet = " + list);
		
		response.setContentType("text/text; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		for(Reservation b : list) {
			out.println(b); //toString 자동 호출
		}
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/member/ajaxMemberReserPage.jsp")
			.forward(request, response);
	}

}
