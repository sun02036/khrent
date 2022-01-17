package com.kh.semi.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.reservation.model.vo.CarList;

/**
 * Servlet implementation class AdminCarListUpdateServlet
 */

@WebServlet("/admin/adminCarListUpdate")
public class AdminCarListUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String carCode = request.getParameter("carCode");
		
		CarList carList = adminService.selectOneCar(carCode);
		
		System.out.println("carList@servlet = " + carList);
		
		// view단 연결
		request.setAttribute("carList", carList);
		request.getRequestDispatcher("/WEB-INF/views/admin/adminCarListUpdate.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			// 1. encoding 처리
			request.setCharacterEncoding("UTF-8");
			
			// 2. 사용자입력값 -> Member객체
			String carCode = request.getParameter("carCode");
			String carName = request.getParameter("carName");
			String releaseYear = request.getParameter("releaseYear");
			String option = request.getParameter("array");
			int price = Integer.parseInt(request.getParameter("price"));
			String numberPlate = request.getParameter("numberPlate");
		
			String newOption = option.replaceAll(",", " "); 
			
			CarList carList = new CarList(carCode, carName, releaseYear, newOption, price, numberPlate);

			System.out.println("carList@servlet = " + carList);

			// 3.업무로직 
			int result = adminService.adminCarListUpdate(carList);
			String msg = result > 0 ? "차량 정보 수정 성공" : "차량 정보 수정 실패";
			
			if(result > 0) {
				HttpSession session = request.getSession();
				CarList newCarList = adminService.selectOneCar(carCode);
				System.out.println("if result문 아래 = " + newCarList);
			}
			
			// 4.응답처리 msg속성 저장후 /mvc로 redirect할 것.

			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			
			String location = request.getContextPath() + "/admin/adminTotalCarList";
			response.sendRedirect(location);
			
	}

}
