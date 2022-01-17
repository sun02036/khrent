package com.kh.semi.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.common.MvcUtils;
import com.kh.semi.reservation.model.vo.Reservation;

/**
 * Servlet implementation class AdminCarReturnListServlet
 */
@WebServlet("/admin/adminCarReturnList")
public class AdminCarReturnListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1.사용자입력값처리
		int cPage = 1;
		int numPerPage = 10;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			// 처리코드 없음.
		}
//		System.out.println("cPage = " + cPage);
		
		int startRownum = cPage * numPerPage - (numPerPage - 1);
		int endRownum = cPage * numPerPage;
		
		// 2.업무로직
		// a.content영역
		List<Reservation> list = adminService.selectCarReturn(startRownum, endRownum);
		System.out.println("list@servlet = " + list); // 최근가입일순
		
		// b.pagebar영역
		int carReturn = adminService.selectCarReturnContents(); 
		System.out.println("totalReservation@servlet = " + carReturn); 
		String url = request.getRequestURI();
		String pagebar = MvcUtils.getPagebar(cPage, numPerPage, carReturn, url);
		System.out.println("pagebar@servlet = " + pagebar);
		
		// 3.view단처리
		request.setAttribute("list", list);
		request.setAttribute("pagebar", pagebar);
		request
			.getRequestDispatcher("/WEB-INF/views/admin/adminCarReturnList.jsp")
			.forward(request, response);
	}

}
