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
 * Servlet implementation class MemberDeleteConfirmServlet
 */

@WebServlet("/admin/adminMemberDelete")
public class AdminMemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		
		Member member = memberService.selectOneMember(memberId);
		
		System.out.println("member@servlet = " + member);
		
		// view단 연결
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/views/admin/adminMemberDelete.jsp")
		.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		System.out.println("memberId@AdminMemberDeleteServlet = " + memberId);
		
//		int result = memberService.deleteMember(memberId);
		int result = memberService.deleteMember(memberId);
		
		HttpSession session = request.getSession();
		
		if(result > 0) {
			session.setAttribute("msg", "삭제 완료");
			response.sendRedirect(request.getContextPath() + "/admin/adminDeleteMemberList");
		}
		else {
			session.setAttribute("msg", "다시 시도해주세요.");
			response.sendRedirect(request.getContextPath() + "/admin/adminMemberList");
		}
	}
}