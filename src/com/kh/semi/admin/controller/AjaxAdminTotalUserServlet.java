package com.kh.semi.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.board.model.vo.NoticeBoard;
import com.kh.semi.member.model.service.MemberService;

/**
 * Servlet implementation class AjaxAdminTotalUser
 */
@WebServlet("/admin/ajaxAdminTotalUser")
public class AjaxAdminTotalUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int totalContents = memberService.selectTotalContents();
		System.out.println("totalContents@servlet = " + totalContents);
		
		response.setContentType("text/text; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		request.setAttribute("totalContents", totalContents);
		request.getRequestDispatcher("/WEB-INF/views/admin/ajaxAdminTotalUser.jsp")
			.forward(request, response);
	}

}
