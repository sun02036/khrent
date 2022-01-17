package com.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;

/**
 * Servlet implementation class MemberLicenseRegisterAtReservationServlet
 */
@WebServlet("/member/memberLicenseRegisterAtReservation")
public class MemberLicenseRegisterAtReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		String memberId = request.getParameter("memberId");
		String issue_date = request.getParameter("issue_date");
		String license_type = request.getParameter("license_type");
		String license_no = request.getParameter("license_no");
	
		Member member = new Member(memberId, null, MemberService.MEMBER_ROLE, null, null, 0, null, issue_date, license_type, license_no);

		System.out.println("member@MemberLicenseRegisterAtReservationServlet = " + member);
 
		
		// 2. 업무로직 - 면허정보 업데이트
		int result_ = memberService.LicenseRegister(member);
		
		if(result_ > 0) {
			HttpSession session = request.getSession();
			Member newMember = memberService.selectOneMember(memberId);
			session.setAttribute("loginMember", newMember);
		}
		
		String result = result_ > 0 ? "ok" : "no";
		
		// 3. forward
		request.setAttribute("result", result);
		request
			.getRequestDispatcher("/WEB-INF/views/reservation/licenseRegister.jsp")
			.forward(request, response);
	}

}
