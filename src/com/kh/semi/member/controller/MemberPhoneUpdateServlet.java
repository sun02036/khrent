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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberPhoneUpdate")
public class MemberPhoneUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.encoding처리
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String phone = request.getParameter("phone");
	
		Member member = new Member(memberId, null, MemberService.MEMBER_ROLE, null, phone, 0, null, null, null, null);

		System.out.println("member@servlet = " + member);

		
		int result = memberService.updateMemberPhone(member);
		String msg = result > 0 ? "핸드폰 번호 변경 성공" : "핸드폰 번호 변경 실패";
		
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
