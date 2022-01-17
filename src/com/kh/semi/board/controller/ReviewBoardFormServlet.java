package com.kh.semi.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.service.ReviewBoardService;
import com.kh.semi.reservation.model.vo.Reservation;

/**
 * Servlet implementation class reviewBoardFormServlet
 */
@WebServlet("/board/reviewBoardForm")
public class ReviewBoardFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	private ReviewBoardService reviewBoardService = new ReviewBoardService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String reserNo = request.getParameter("reserNo");
		
		// 2. 업무로직 - 해당 id의 예약내역을 가져옴
		List<Reservation> list = reviewBoardService.selectOneReservation(reserNo);
		System.out.println("reviewBoardFormServlet@list" + list);
		
		// 3. view단 처리: session의 loginMember 데이터 활용
		request.setAttribute("list", list);
		request
			.getRequestDispatcher("/WEB-INF/views/board/reviewBoardForm.jsp")
			.forward(request, response);
	}


}
