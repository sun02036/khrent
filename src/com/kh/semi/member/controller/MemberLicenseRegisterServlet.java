package com.kh.semi.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;

/**
 * Servlet implementation class MemberLicenseRegisterServlet
 */
@WebServlet("/member/memberLicenseRegister")
public class MemberLicenseRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberLicenseRegister.jsp")
		   .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.encoding처리
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String issue_date = request.getParameter("issue_date");
		String license_type = request.getParameter("type");
		String license_no = request.getParameter("license_no");
	
		Member member = new Member(memberId, null, MemberService.MEMBER_ROLE, null, null, 0, null, issue_date, license_type, license_no);

		System.out.println("member@servlet = " + member);

		
		int result = memberService.LicenseRegister(member);
		String msg = result > 0 ? "면허 등록 성공" : "다시 시도해주세요.";
		
		if(result > 0) {
			HttpSession session = request.getSession();
			Member newMember = memberService.selectOneMember(memberId);
			session.setAttribute("loginMember", newMember);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		String location = request.getContextPath() + "/member/memberDetail";
		response.sendRedirect(location);
	}

}
