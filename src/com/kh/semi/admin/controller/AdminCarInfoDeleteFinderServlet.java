package com.kh.semi.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.common.MvcUtils;
import com.kh.semi.reservation.model.vo.CarInfo;

/**
 * Servlet implementation class AdminCarInfoFinderServlet
 */
@WebServlet("/admin/carInfoDeleteFinder")
public class AdminCarInfoDeleteFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 사용자입력값처리 cPage numPerPage
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");

		int cPage = 1;
		int numPerPage = 10;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			// 처리코드 없음.
		}
		int start = cPage * numPerPage - (numPerPage - 1);
		int end = cPage * numPerPage;
		
		Map<String, Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		param.put("start", start);
		param.put("end", end);
		System.out.println("param@servlet = " + param);
		
		// 2. 업무로직
		// 	가. content영역
		List<CarInfo> list = adminService.searchCarInfo(param);
		System.out.println(list);
		
		// 	나. pagebar영역
		//  totalContents

		int totalContents = adminService.searchCarInfoCount(param);
		
		String queryString = String.format("?searchType=%s&searchKeyword=%s", searchType, searchKeyword);
		String url = request.getRequestURI() + queryString; 
		
		String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContents, url);
		System.out.println("pagebar@servlet = " + pagebar);
		
		// 3. view단처리 (html)
		request.setAttribute("list", list);
		request.setAttribute("pagebar", pagebar);
		request
			.getRequestDispatcher("/WEB-INF/views/admin/adminCarInfoDeleteList.jsp")
			.forward(request, response);
	}

}
