package com.kh.semi.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.reservation.model.vo.CarInfo;

/**
 * Servlet implementation class AdminCarInfoDeleteServlet
 */
@WebServlet("/admin/adminCarInfoDelete")
public class AdminCarInfoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String carName = request.getParameter("carName");
		
		CarInfo carInfo = adminService.selectOneCarInfo(carName);
		
		System.out.println("carInfo@servlet = " + carInfo);
		
		// view단 연결
		request.setAttribute("carInfo", carInfo);
		request.getRequestDispatcher("/WEB-INF/views/admin/adminCarInfoDelete.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String carName = request.getParameter("carName");
		System.out.println("carName@adminCarInfoDeleteServlet = " + carName);
		
		int result = adminService.adminCarInfoDelete(carName);
		
		HttpSession session = request.getSession();
		
		if(result > 0) {
			session.setAttribute("msg", "삭제 완료");
			response.sendRedirect(request.getContextPath() + "/admin/adminCarInfoDeleteList");
		}
		else {
			session.setAttribute("msg", "다시 시도해주세요.");
			response.sendRedirect(request.getContextPath() + "/admin/adminCarInfoDeleteList");
		}
	}

}
