package com.kh.semi.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.member.model.service.MemberService;

/**
 * Servlet implementation class AdminMemberRoleUpdateServlet
 */
@WebServlet("/admin/updateMemberRole")
public class AdminMemberRoleUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.사용자입력값 처리 : memberId, memberRole
		String memberId = request.getParameter("memberId");
		String memberRole = request.getParameter("memberRole");
		
		//2.업무로직
		int result = memberService.updateMemberRole(memberId, memberRole);
		String msg = result > 0 ? 
						"사용자 권한 변경 성공" : 
							"사용자 권한 변경 실패";
		
		//3.리다이렉트 및 사용자 피드백
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/admin/adminMemberList");
		
	}

}
