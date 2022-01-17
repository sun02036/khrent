package com.kh.semi.admin.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.board.model.vo.QuestionBoard;
import com.kh.semi.common.MvcFileRenamePolicy;
import com.kh.semi.reservation.model.vo.CarInfo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class AdminCarInfoInsert
 */

@WebServlet("/admin/adminCarInfoInsert")
public class AdminCarInfoInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/admin/adminCarInfoInsert.jsp")
		   .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 0. 파일업로드
		
		// b. 파일저장경로
		// ServletContext객체로부터  /WebContent/upload/board 절대경로 참조
		ServletContext application = getServletContext(); 
		String saveDirectory = application.getRealPath("/upload/car");
		System.out.println("saveDirectory = " + saveDirectory);
		
		// c.최대파일용량 10mb = 1kb * 1000 * 10
		int maxPostSize = 1024 * 1024 * 10;
		
		// d.인코딩
		String encoding = "utf-8";
		
		MultipartRequest multipartRequest = 
				new MultipartRequest(
						request, 
						saveDirectory, 
						maxPostSize, 
						encoding
					);
		
		// 파일정보 가져오기
		String originalFilename = multipartRequest.getOriginalFileName("img");
		
		// 1. 사용자입력값처리
		String carName = multipartRequest.getParameter("carName");
		String maker = multipartRequest.getParameter("maker");
		String fuel = multipartRequest.getParameter("fuel");
		String carSize = multipartRequest.getParameter("carSize");
		String img = multipartRequest.getParameter("img");
		
		CarInfo carInfo = new CarInfo(0, carName, maker, fuel, carSize, originalFilename, 0, 0, 0);
		
		System.out.println("AdminCarInfoInsertServlet@servlet = " + carInfo);
		
		// 2. 업무로직
		int result = adminService.insertCarInfo(carInfo);
		String msg = result > 0 ? "차종 등록 성공" : "차종 등록 실패";
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		System.out.println("AdminCarInfoInsertServlet@servlet = " + carInfo);
		
		// 3. 응답처리 : redirect
		String location = request.getContextPath() + "/admin/adminTotalCarList";
		response.sendRedirect(location);
	}

}