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
 * Servlet implementation class UpdatePasswordServlet
 */
@WebServlet("/member/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberService();

	/**
	 * GET /member/updatePassword
	 * jsp 연결(forwarding)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/updatePassword.jsp")
		   .forward(request, response);
	}

	/**
	 * POST /member/updatePassword
	 * db 정보 update
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String location = request.getContextPath();
		String msg = null;
		int result = 0;
		
		// 1. 사용자 입력값 처리 : 기존비밀번호/신규비밀번호 암호화처리 필수
		String oldPassword = MvcUtils.getEncryptedPassword(request.getParameter("oldPassword"));
		String newPassword = MvcUtils.getEncryptedPassword(request.getParameter("newPassword"));
		
		// 2. 기존비밀번호 비교 : session의 loginMember객체 이용할 것
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");

		if(oldPassword.equals(loginMember.getPassword())) {
			
			// 3. 업무로직 : 기존비밀번호가 일치한 경우만 신규비밀번호로 업데이트한다.
			loginMember.setPassword(newPassword);
			result = memberService.updatePassword(loginMember);
			msg = (result > 0) ? "비밀번호 변경 성공" : "비밀번호 변경 실패";
			session.setAttribute("msg", msg);
			location += "/member/memberDetail";
		}
		else {
			msg = "비밀번호가 일치하지 않습니다.";				
			session.setAttribute("msg", msg);
			location += "/member/updatePassword";
		}
		
		// 4. 사용자 경고창 및 리다이렉트 처리

		response.sendRedirect(location);
		
	
	}
}
