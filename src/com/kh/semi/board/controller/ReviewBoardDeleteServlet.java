package com.kh.semi.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.board.model.service.ReviewBoardService;
import com.kh.semi.board.model.vo.Attachment;

/**
 * Servlet implementation class ReviewBoardDeleteServlet
 */
@WebServlet("/board/reviewBoardDelete")
public class ReviewBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ReviewBoardService reviewBoardService = new ReviewBoardService();
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		String reserveNo = request.getParameter("reserveNo");
		String carName = request.getParameter("carName");
		System.out.println("예약번호@ReviewBoardDeleteServlet  = " + reserveNo);
		System.out.println("차이름@ReviewBoardDeleteServlet  = " + carName);
				
				
		// 2.업무로직
		Attachment attach = reviewBoardService.selectOneReviewBoardAttachment(no);
		
		// 글에 딸려있는 댓글과 첨부파일은 db에서 자동으로 삭제되지만(fk를 게시글번호 on delete cascade 했으므로) 컴퓨터에 저장되어있는 첨부파일은 따로 삭제해야한다.
		if(attach != null) {
			ServletContext application = getServletContext(); 
			String saveDirectory = application.getRealPath("/upload/board");
			String filename = attach.getRenamedFilename();
			File delFile = new File(saveDirectory, filename);
			boolean result = delFile.delete();
			System.out.printf("첨부파일[%s] 삭제여부 : %b%n", filename, result);
		}
		String review_status = "N";
		int result = reviewBoardService.deleteReviewBoard(no);
		// 삭제할 때 해당 예약번호가 있는 예약테이블의 review_status가 Y가 되야한다.
		int result2 = reviewBoardService.updateReservationReviewSatatus(review_status, reserveNo);
		
		// 이용후기 삭제할 때 해당 car_info의 해당 차이름 carName의 assess_cnt 가 -1이 되야함.
		int result3 = reviewBoardService.minusOneAssessCnt(carName);
		
		String msg = result > 0 ? "게시물 삭제 성공!" : "게시물 삭제 실패!";
		
		
		// 3. view단 처리. BoardListServlet으로 리다이렉트한다.
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		String location = request.getContextPath() + "/board/reviewList";
		response.sendRedirect(location);
	}

}
