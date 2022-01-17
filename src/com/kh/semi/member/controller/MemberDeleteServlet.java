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
 * Servlet implementation class MemberDeleteConfirmServlet
 */

@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberService(); 
	private Member member = new Member();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberDelete.jsp")
		   .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자 입력값 처리
		
		String memberId = request.getParameter("memberId");
		String ckPassword = MvcUtils.getEncryptedPassword(request.getParameter("password"));
		
		// 2.서비스로직호출
		int result = memberService.deleteMember(memberId);
		
		// 3.응답처리
		// 성공적으로 탈퇴했다면, 탈퇴후 session의 loginMember객체도 무효화(로그아웃)해야 한다.
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if (ckPassword.equals(loginMember.getPassword())) {
				session.setAttribute("msg", "탈퇴가 완료되었습니다.");
				//location으로 logout페이지 지정
				response.sendRedirect(request.getContextPath() + "/member/logout");
		}
		
		else {
			session.setAttribute("msg", "다시 시도해주세요.");
			response.sendRedirect(request.getContextPath() + "/");
		}

	}

}
