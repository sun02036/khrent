package com.kh.semi.reservation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.reservation.model.service.ReservationService;
import com.kh.semi.reservation.model.vo.Reservation;

/**
 * Servlet implementation class MakeReservationServlet
 */
@WebServlet("/reservation/makeReservation")
public class MakeReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ReservationService reservationService = new ReservationService();
	private MemberService memberService = new MemberService(); 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		String memberId = request.getParameter("member_id");
		String startDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		String carCode = request.getParameter("car_code");
		String carName = request.getParameter("car_name");
		int price = Integer.parseInt(request.getParameter("price"));
		String insuranceType_ = request.getParameter("insurance_type");
		String issueDate = request.getParameter("issue_date");
		String licenseType = request.getParameter("license_type");
		String licenseNo = request.getParameter("license_no");
		
		// 마일리지 업데이트를 위한 값들. 예약시 사용한 마일리지가 0원이면 getParameter 값이 "" 이므로 이럴 경우는 0으로 한다. 그냥 Integer~ 로만 해두면 넘버포맷익셉션이 발생한다. 
		int totalMileage = Integer.parseInt(request.getParameter("totalMileage"));
		int usedMileage = "".equals(request.getParameter("usedMileage")) ? 0 : Integer.parseInt(request.getParameter("usedMileage"));
		
		System.out.println("MakeReservationServlet@memberId = " + memberId);
		System.out.println("MakeReservationServlet@startDate = " + startDate);
		System.out.println("MakeReservationServlet@endDate = " + endDate);
		System.out.println("MakeReservationServlet@carCode = " + carCode);
		System.out.println("MakeReservationServlet@carName = " + carName);
		System.out.println("MakeReservationServlet@price = " + price);
		System.out.println("MakeReservationServlet@insuranceType_ = " + insuranceType_);
		System.out.println("MakeReservationServlet@issueDate = " + issueDate);
		System.out.println("MakeReservationServlet@licenseType = " + licenseType);
		System.out.println("MakeReservationServlet@licenseNo = " + licenseNo);
		
		System.out.println("MakeReservationServlet@totalMileage = " + totalMileage);
		System.out.println("MakeReservationServlet@usedMileage = " + usedMileage);
		
		String insuranceType = "20000".equals(insuranceType_) ? "Y" : "N";
		
		Reservation reservation = new Reservation(null, memberId, carCode, carName, startDate, endDate, price, insuranceType, issueDate, licenseType, null, null);
		Member member = new Member(memberId, null, null, null, null, totalMileage - usedMileage, null, null, null, null);
		
		
		// 2. 업무로직 - 전달받은 정보들로 예약테이블에 행 추가
		int result1 = reservationService.insertReservation(reservation);
		int result2 = memberService.adminMemberUpdateMileage(member);
		
		if(result2 > 0) {
			HttpSession session = request.getSession();
			Member newMember = memberService.selectOneMember(memberId);
			session.setAttribute("loginMember", newMember);
		}
		
		// 3. ajax에 쓰인 서블릿이므로 append로 돌려줄 값을 작성... 할 필요가 없나?
		     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		String memberId = request.getParameter("member_id");
		String startDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		String carCode = request.getParameter("car_code");
		String carName = request.getParameter("car_name");
		int price = Integer.parseInt(request.getParameter("price"));
		
		System.out.println("MakeReservationServlet@memberId = " + memberId);
		System.out.println("MakeReservationServlet@startDate = " + startDate);
		System.out.println("MakeReservationServlet@endDate = " + endDate);
		System.out.println("MakeReservationServlet@carCode = " + carCode);
		System.out.println("MakeReservationServlet@carName = " + carName);
		System.out.println("MakeReservationServlet@price = " + price);
		
		Reservation reservation = new Reservation(null, memberId, carCode, carName, startDate, endDate, price, null, null, null, null, null);
		
		// 2. 업무로직 - 전달받은 정보들로 예약테이블에 행 추가
		int result = reservationService.insertReservation(reservation);
		
		// 3. ajax에 쓰인 서블릿이므로 append로 돌려줄 값을 작성... 할 필요가 없나?
		     
	}

}
