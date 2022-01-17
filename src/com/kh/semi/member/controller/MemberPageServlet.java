package com.kh.semi.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.service.ReviewBoardService;
import com.kh.semi.board.model.vo.ReviewBoard;
import com.kh.semi.board.model.service.ReviewBoardService;
import com.kh.semi.board.model.vo.ReviewBoard;
import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.reservation.model.vo.Reservation;

/**
 * Servlet implementation class MemberDetailServlet
 */
@WebServlet("/member/myPage")
public class MemberPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberService();
	private ReviewBoardService reviewBoardService = new ReviewBoardService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String memberId = request.getParameter("memberId");
		System.out.println("멤버아이디 가져오니? = " + memberId);
		
		// 2. 업무로직 - 해당 id의 예약내역을 가져옴
		List<Reservation> list = memberService.selectMyReservationList(memberId);
		List<ReviewBoard> list2 = reviewBoardService.selectMyReviewBoard(memberId);
		
		System.out.println("MemeberPageServlet@list" + list);
		System.out.println("MemberPageServlet@list2" + list2);
		
		// 3. view단 처리: session의 loginMember 데이터 활용
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request
			.getRequestDispatcher("/WEB-INF/views/member/memberPage.jsp")
			.forward(request, response);
	}


}
