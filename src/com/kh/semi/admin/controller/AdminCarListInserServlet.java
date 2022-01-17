package com.kh.semi.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.reservation.model.vo.CarList;

/**
 * Servlet implementation class AdminCarListInserServlet
 */

@WebServlet("/admin/adminCarListInsert")
public class AdminCarListInserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/admin/adminCarListInsert.jsp")
		   .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.encoding처리
		request.setCharacterEncoding("UTF-8");

		// 2.사용자입력값 -> Member객체
		String carName = request.getParameter("carName");
		String releaseYear = request.getParameter("releaseYear");
		String option = request.getParameter("array");
		int price = Integer.parseInt(request.getParameter("price"));
		String numberPlate = request.getParameter("numberPlate");
	
		String newOption = option.replaceAll(",", " "); 
		
		CarList carList = new CarList(null, carName, releaseYear, newOption, price, numberPlate);

		System.out.println("carList@servlet = " + carList);

		// 3.업무로직 
		int result = adminService.insertCarList(carList);
		String msg = result > 0 ? "차량 등록 성공" : "차량 등록 실패";
	
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);

		// 4.응답처리
		if(result == 1){ // 성공
			String location = request.getContextPath() + "/admin/adminTotalCarList";
			response.sendRedirect(location);
			
		} else { // 실패
			String location = request.getContextPath() + "/admin/adminCarListInsert";
			response.sendRedirect(location);
		}
	}

}
