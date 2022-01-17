package com.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.common.MvcUtils;
import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */

@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("test");
		// view단 연결
		request
			.getRequestDispatcher("/WEB-INF/views/member/memberLogin.jsp")
			.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. encoding처리
		request.setCharacterEncoding("utf-8");
		
		// 2. 사용자입력값처리
		String memberId = request.getParameter("memberId");
		String password = MvcUtils.getEncryptedPassword(request.getParameter("password"));
		String saveId = request.getParameter("saveId"); 
		
		System.out.println("memberId@servlet = " + memberId);
		System.out.println("password@servlet = " + password);
		System.out.println("saveId@servlet = " + saveId);
		
		// 3. 업무로직 실행 
		// a. db에서 memberId와 일치하는 회원 조회
		Member member = memberService.selectOneMember(memberId);
		System.out.println("member@servlet = " + member);
		
		HttpSession session = request.getSession();
		System.out.println(session.getId());

		// b. 리턴된 회원객체에서 비밀번호 일치여부 검사
		String location = "";
		if(member != null && password.equals(member.getPassword())) {
			// 로그인 성공
			// session객체에 로그인 정보 기록
			session.setAttribute("loginMember", member);
			session.setAttribute("msg", "KH렌트에 오신 것을 환영합니다.");
			
			// session유효시간
			// session.setMaxInactiveInterval(60); // 60초
			
			// 아이디저장 #saveId 처리
			// 응답메세지에 Set-cookie헤더값으로 전송
			Cookie cookie = new Cookie("saveId", memberId);
			cookie.setPath(request.getContextPath()); // /mvc로 시작하는 경로에 사용할 쿠키
			if(saveId != null) {
				// 체크한 경우
				cookie.setMaxAge(7 * 24 * 60 * 60); // 7일을 초로 지정 
			}
			else {
				// 체크하지 않은 경우
				cookie.setMaxAge(0); // 즉시 삭제
			}
			response.addCookie(cookie);
			
//			location = request.getHeader("Referer");
			location = request.getContextPath() + "/";
		}
		else {
			// 로그인 실패
			session.setAttribute("msg", "아이디, 비밀번호가 일치하지 않습니다.");	
			location = request.getContextPath() + "/member/login";
		}
		
		// 4. 리다이렉트
		response.sendRedirect(location);
	}

}






