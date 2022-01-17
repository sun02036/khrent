package com.kh.semi.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.reservation.model.vo.CarInfo;
import com.kh.semi.reservation.model.vo.CarList;

/**
 * Servlet implementation class AdminCarListDeleteServlet
 */
@WebServlet("/admin/adminCarListDelete")
public class AdminCarListDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String carCode = request.getParameter("carCode");
		
		CarList carList = adminService.selectOneCarList(carCode);
		
		System.out.println("carList@servlet = " + carList);
		
		// view단 연결
		request.setAttribute("carList", carList);
		request.getRequestDispatcher("/WEB-INF/views/admin/adminCarListDelete.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String carCode = request.getParameter("carCode");
		System.out.println("carCode@adminCarListDeleteServlet = " + carCode);
		
		int result = adminService.adminCarListDelete(carCode);
		
		HttpSession session = request.getSession();
		
		if(result > 0) {
			session.setAttribute("msg", "삭제 완료");
			response.sendRedirect(request.getContextPath() + "/admin/adminCarListDeleteList");
		}
		else {
			session.setAttribute("msg", "다시 시도해주세요.");
			response.sendRedirect(request.getContextPath() + "/admin/adminCarListDeleteList");
	}

	}
}
