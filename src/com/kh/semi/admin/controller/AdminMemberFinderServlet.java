package com.kh.semi.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.MvcUtils;
import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberFinderServlet
 */

@WebServlet("/admin/memberFinder")
public class AdminMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값처리 cPage numPerPage
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");

		int cPage = 1;
		int numPerPage = 10;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			// 처리코드 없음.
		}
		int start = cPage * numPerPage - (numPerPage - 1);
		int end = cPage * numPerPage;
		
		Map<String, Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		param.put("start", start);
		param.put("end", end);
		System.out.println("param@servlet = " + param);
		
		// 2. 업무로직
		// 	가. content영역
		// 		a. select * from member where member_id like '%abc%'
		// 		b. select * from member where member_name like '%길동%'
		// 		c. select * from member where gender = '남'
		List<Member> list = memberService.searchMember(param);
		System.out.println(list);
		
		// 	나. pagebar영역
		//  totalContents
		// 		a. select count(*) from member where member_id like '%abc%'
		// 		b. select count(*) from member where member_name like '%길동%'

		int totalContents = memberService.searchMemberCount(param);
		
		String queryString = String.format("?searchType=%s&searchKeyword=%s", searchType, searchKeyword);
		String url = request.getRequestURI() + queryString; // /mvc/admin/memberFinder?searchType=memberId&searchKeyword=a 
		
		String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContents, url);
		System.out.println("pagebar@servlet = " + pagebar);
		
		// 3. view단처리 (html)
		request.setAttribute("list", list);
		request.setAttribute("pagebar", pagebar);
		request
			.getRequestDispatcher("/WEB-INF/views/admin/adminMemberList.jsp")
			.forward(request, response);
		
	}

}
