package com.kh.semi.reservation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.reservation.model.service.ReservationService;
import com.kh.semi.reservation.model.vo.Car;

/**
 * Servlet implementation class CarSearchServlet
 */
@WebServlet("/reservation/carSearch")
public class CarSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ReservationService reservationService = new ReservationService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자입력값 처리 - 날짜검색에서 받아온 예약시작일과 종료일
		String startDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		System.out.println(startDate);
		System.out.println(endDate);
		
		// 업무로직 - 받아온 시작일부터 종료일까지 대여가능한 차량을 조회  
		List<Car> list = reservationService.searchCar(startDate, endDate);
		
		
		// 중복된 차량을 제거
		// 아래 방법 쓰면 될 줄 알았는데 멀쩡한것까지 왕창 삭제된다. 이유도 모르겠다.
//		int size = list.size();
//		for(int i = 0; i < size - 1; i++) {
//			System.out.println(size);
//			for(int j = 1; j < size; j++) {
//				if(list.get(j).getCarCode().equals(list.get(i).getCarCode())) {
//					System.out.println("j = " + j);
//					list.remove(j);
//				}
//			}
//		}
		
		// 이게 리스트에서 중복제거하는 방법이라는데 안됨
//		List<Car> list = list_.stream().distinct().collect(Collectors.toList());
		
		// 이것도 안됨
//		List<Car> list = new ArrayList<Car>();
//		for(int i = 0; i < list_.size(); i++) {
//			if(!list.contains(list_.get(i))) {
//				list.add(list_.get(i));		
//			}
//		}
		
		// 이것도 안됨. 자바에서는 어떻게 해도 같은 객체를 중복으로 인식을 못하는거같다. 그래서 sql디벨로퍼에서 distinct를 이용해서 걸러냈다.
//		Set<Car> set = new LinkedHashSet(list_);
//		List<Car> list = new ArrayList<Car>(set);
		
		
		System.out.println(list);
		System.out.println(list.size());
		
		// view단 연결
		request.setAttribute("start_date", startDate);
		request.setAttribute("end_date", endDate);
		request.setAttribute("list", list);
		request
			.getRequestDispatcher("/WEB-INF/views/reservation/carSearch.jsp")
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
