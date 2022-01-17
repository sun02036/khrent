package com.kh.semi.member.controller;

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
 * Servlet implementation class MemberFindPwdResultServlet
 */
@WebServlet("/member/memberFindPwdResult")
public class MemberFindPwdResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. encoding 처리
		request.setCharacterEncoding("UTF-8");
		
		// 2. 사용자입력값 -> Member객체
		String memberId = request.getParameter("memberId");
		String newPassword = MvcUtils.getEncryptedPassword(request.getParameter("newPassword"));
	
		Member member = new Member(memberId, newPassword, null, null, null, 0, null, null, null, null);

		System.out.println("member@servlet = " + member);

		// 3.업무로직 
		int result = memberService.adminMemberUpdatePassword(member);
		String msg = result > 0 ? "비밀번호 변경 성공" : "다시 시도해주세요.";
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);

		if(result > 0) {
			Member newMember = memberService.selectOneMember(memberId);
			String location = request.getContextPath() + "/member/login";
			response.sendRedirect(location);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/member/memberFindPwd.jsp")
			.forward(request, response);
		}
	}

}
