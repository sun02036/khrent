package com.kh.semi.reservation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.reservation.model.service.ReservationService;
import com.kh.semi.reservation.model.vo.Car;

/**
 * Servlet implementation class ReservationPageServlet
 */
@WebServlet("/reservation/reservationPage")
public class ReservationPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ReservationService reservationService = new ReservationService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		String startDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		String carCode = request.getParameter("car_code");
		int price = Integer.parseInt(request.getParameter("price"));
		int days = Integer.parseInt(request.getParameter("days"));
		
//		System.out.println("ReservationPageServlet@startDate = " + startDate);
//		System.out.println("ReservationPageServlet@endDate = " + endDate);
//		System.out.println("ReservationPageServlet@carCode = " + carCode);
		
		// 2. 업무로직 - 전달받을 car_code로 해당 차량의 정보를 db에서 가져옴
		Car car = reservationService.selectOneCar(carCode);
		System.out.println(car);
		
		// 3. view단 연결
		request.setAttribute("start_date", startDate);
		request.setAttribute("end_date", endDate);
		request.setAttribute("car", car);
		request.setAttribute("price", price);
		request.setAttribute("days", days);
		request
			.getRequestDispatcher("/WEB-INF/views/reservation/reservationPage.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
