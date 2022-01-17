package com.kh.semi.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.common.MvcUtils;
import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollServlet
 */

@WebServlet("/member/enroll")
public class MemberEnrollServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// view단 연결
		request
			.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1.encoding처리
		request.setCharacterEncoding("UTF-8");

		// 2.사용자입력값 -> Member객체
		String memberId = request.getParameter("memberId");
		String password = MvcUtils.getEncryptedPassword(request.getParameter("password"));
		String memberName = request.getParameter("memberName");
		String phone = request.getParameter("phone");
		int mileage = 1000;
	
		Member member = new Member(memberId, password, MemberService.MEMBER_ROLE, memberName, phone, mileage, null, null, null, null);

		System.out.println("member@servlet = " + member);

		// 3.업무로직 
		int result = memberService.insertMember(member);
	
		HttpSession session = request.getSession();

		// 4.응답처리
		if(result == 1){ // 성공
			session.setAttribute("loginMember", member);
			String location = request.getContextPath() + "/member/memberEnrollCompleted";
			response.sendRedirect(location);
		} else { // 실패
			String location = request.getContextPath() + "/";
			response.sendRedirect(location);
		}
		
	}

}
