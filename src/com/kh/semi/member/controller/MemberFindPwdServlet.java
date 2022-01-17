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
 * Servlet implementation class MemberFindPwdServlet
 */
@WebServlet("/member/memberFindPwd")
public class MemberFindPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
		.getRequestDispatcher("/WEB-INF/views/member/memberFindPwd.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. encoding처리
		request.setCharacterEncoding("utf-8");
		
		// 2. 사용자입력값처리
		String memberId = request.getParameter("memberId");
		String phone = request.getParameter("phone");
		
		System.out.println("memberId@servlet = " + memberId);
		System.out.println("phone@servlet = " + phone);
		
		// 3. 업무로직 실행 
		Member member = memberService.selectOneMember(memberId);
		System.out.println("member@servlet = " + member);
		
		HttpSession session = request.getSession();

		// b. 리턴된 회원객체에서 일치여부 검사
		if(member != null && phone.equals(member.getPhone()) && memberId.equals(member.getMemberId())) {
			request.setAttribute("member", member);
			request.getRequestDispatcher("/WEB-INF/views/member/memberFindPwdResult.jsp")
			.forward(request, response);
		}
		else {
			session.setAttribute("msg", "일치하는 회원이 없습니다.");
			request.getRequestDispatcher("/WEB-INF/views/member/memberFindPwd.jsp")
			.forward(request, response);
		}
	}

}
