package com.kh.semi.admin.controller;

import java.io.IOException;

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
 * Servlet implementation class AdminMemberUpdate
 */

@WebServlet("/admin/adminMemberUpdate")
public class AdminMemberUpdateServlet extends HttpServlet {
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
		request.getRequestDispatcher("/WEB-INF/views/admin/adminMemberUpdate.jsp")
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
		String password = request.getParameter("password");
		String memberName = request.getParameter("memberName");
		String phone = request.getParameter("phone");
		String license_type = request.getParameter("license_type");
		String license_no = request.getParameter("license_no");
		String issue_date = request.getParameter("issue_date");
	
		Member member = new Member(memberId, null, memberName, MemberService.MEMBER_ROLE, phone, 0, null, license_type, license_no, issue_date);

		System.out.println("member@servlet = " + member);

		
		// 3.업무로직 
		int result = memberService.adminMemberUpdate(member);
		String msg = result > 0 ? "회원정보 수정 성공" : "회원정보 수정 실패";
		
		if(result > 0) {
			HttpSession session = request.getSession();
			Member newMember = memberService.selectOneMember(memberId);
		}
		
		// 4.응답처리 msg속성 저장후 /mvc로 redirect할 것.

		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		String location = request.getContextPath() + "/";
		response.sendRedirect(location);
	}
}
