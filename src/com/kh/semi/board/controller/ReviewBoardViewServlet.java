package com.kh.semi.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.service.ReviewBoardService;
import com.kh.semi.board.model.vo.ReviewBoard;
import com.kh.semi.board.model.vo.ReviewBoardComment;
import com.kh.semi.reservation.model.vo.Reservation;

/**
 * Servlet implementation class CommunityDetailViewServlet
 */
@WebServlet("/board/reviewBoardView")
public class ReviewBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ReviewBoardService reviewBoardService = new ReviewBoardService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 읽은 여부 확인(cookie)
		Cookie[] cookies = request.getCookies();
		boolean hasRead = false;
		String reviewBoardValue = "";
		
		if(cookies != null) {
			for(Cookie c : cookies) {
				String name = c.getName();
				String value = c.getValue();
				System.out.println(name + " : " + value);
				
				if("board".equals(name)) {
					reviewBoardValue = value;
					if(value.contains("|"+ no + "|")) {
					hasRead = true;
				}
				break;
			}
		}
	}
	
	System.out.printf("hasRead = %b, reviewBoardValue = %s%n", hasRead, reviewBoardValue);
	
	int result = 2;
	// 게시글을 처음 읽는 경우
	if(!hasRead) {
		// 게시글 Cookie
		Cookie cookie = new Cookie("board", reviewBoardValue + "|" + no + "|" );
		cookie.setMaxAge(365 * 24 *60 *60);
		cookie.setPath(request.getContextPath() + "/board/reviewBoardView");
		response.addCookie(cookie);
		
		// 조회수 증가
		result = reviewBoardService.updateReviewBoardReadCount(no);		
	}
	
	System.out.println("조회수 등록 여부 :" + result);
	
	ReviewBoard reviewBoard = reviewBoardService.selectOneReviewBoard(no);
	System.out.println("selectOneReviewBoard@servlet = " + reviewBoard);
	
	// reviewBoard에 등록된 예약번호가 있으면 reservation테이블의 같은 예약번호의 review_status가 Y로 바뀜
	String isThereReserveNumber = "";

	String reserveNumber = reviewBoard.getReservNo();
	System.out.println("예약번호는?" + reserveNumber);
	List<Reservation> reservation = reviewBoardService.selectOneReservation(reserveNumber);
	System.out.println("나오는 예약리스트는? " + reservation);
	System.out.println("reservation의 reserNo만 뽑아내기 = " + reservation.get(0).getReserNo());
	if(reservation.size() >= 1) {
		isThereReserveNumber = "Y";
	} else {
		isThereReserveNumber = "N";
	}
	
	int result2 = reviewBoardService.updateReservationReviewSatatus(isThereReserveNumber, reservation.get(0).getReserNo());
	
	// 댓글목록가져오기
	List<ReviewBoardComment> commentList = reviewBoardService.selectReviewCommentList(no);
	System.out.println("ReviewBoardCommentList@servlet = " + commentList);
	
	
	
	request.setAttribute("commentList", commentList);
	request.setAttribute("reviewBoard", reviewBoard);
	request.getRequestDispatcher("/WEB-INF/views/board/reviewView.jsp").forward(request, response);
	
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
