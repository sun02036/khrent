package com.kh.semi.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.admin.model.service.AdminService;

/**
 * Servlet implementation class AjaxAdminTodayEnroll
 */
@WebServlet("/admin/ajaxAdminTodayEnroll")
public class AjaxAdminTodayEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int todayEnroll = adminService.ajaxAdminTodayEnroll();
		System.out.println("todayEnroll@servlet = " + todayEnroll);
		
		response.setContentType("text/text; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		request.setAttribute("todayEnroll", todayEnroll);
		request.getRequestDispatcher("/WEB-INF/views/admin/ajaxAdminTodayEnroll.jsp")
			.forward(request, response);
	}

}
