package com.kh.semi.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.MvcUtils;
import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberListServlet
 */

@WebServlet("/admin/adminMemberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1.사용자입력값처리
		int cPage = 1;
		int numPerPage = 10;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			// 처리코드 없음.
		}
//		System.out.println("cPage = " + cPage);
		
		int startRownum = cPage * numPerPage - (numPerPage - 1);
		int endRownum = cPage * numPerPage;
		
		// 2.업무로직
		// a.content영역
		List<Member> list = memberService.selectAllMember(startRownum, endRownum);
		System.out.println("list@servlet = " + list); // 최근가입일순
		
		// b.pagebar영역
		int totalContents = memberService.selectTotalContents(); // 총회원수
		System.out.println("totalContents@servlet = " + totalContents); // 114
		String url = request.getRequestURI();
		String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContents, url);
		System.out.println("pagebar@servlet = " + pagebar);
		
		// 3.view단처리
		request.setAttribute("list", list);
		request.setAttribute("pagebar", pagebar);
		request
			.getRequestDispatcher("/WEB-INF/views/admin/adminMemberList.jsp")
			.forward(request, response);
	}

}
