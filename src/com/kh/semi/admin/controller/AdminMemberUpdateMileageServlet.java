package com.kh.semi.admin.controller;

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
 * Servlet implementation class AdminMemberUpdateMileage
 */
@WebServlet("/admin/adminMemberUpdateMileage")
public class AdminMemberUpdateMileageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		
		Member member = memberService.selectOneMember(memberId);
		
		System.out.println("member@servlet = " + member);
		
		// view단 연결
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/views/admin/adminMemberUpdateMileage.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. encoding 처리
		request.setCharacterEncoding("UTF-8");
		
		// 2. 사용자입력값 -> Member객체
		String memberId = request.getParameter("memberId");
		int mileage = Integer.parseInt(request.getParameter("mileage"));
	
		Member member = new Member(memberId, null, null, null, null, mileage, null, null, null, null);

		System.out.println("member@servlet = " + member);

		
		// 3.업무로직 
		int result = memberService.adminMemberUpdateMileage(member);
		String msg = result > 0 ? "마일리지 변경 성공" : "마일리지 변경 실패";
		
		if(result > 0) {
			HttpSession session = request.getSession();
			Member newMember = memberService.selectOneMember(memberId);
			request.setAttribute("member", newMember);
		}
		
		// 4.응답처리 msg속성 저장후 /mvc로 redirect할 것.

		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		String location = request.getContextPath() + "/admin/adminMemberList";
		response.sendRedirect(location);
	}
}


