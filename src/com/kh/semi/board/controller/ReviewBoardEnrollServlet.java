package com.kh.semi.board.controller;

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
import com.kh.semi.board.model.vo.ReviewBoard;
import com.kh.semi.common.MvcFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class ReviewBoardEnrollServlet
 */
@WebServlet("/board/reviewBoardEnroll")
public class ReviewBoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ReviewBoardService reviewBoardService = new ReviewBoardService();
	/**
	 * 1. 파일업로드(사용자파일을 서버컴퓨터에 저장) : MultipartRequest객체 생성
	 * 	a. HttpServletRequest
	 * 	b. saveDirectory 파일이 저장될 directory
	 *  c. maxPostSize 업로드 최대용량(10mb)
	 *  d. encoding
	 *  e. FileRenamePolicy객체 : DefaultFileRenamePolicy객체 -> MvcFileRenamePolicy객체
	 * 	
	 * 
	 * 2. 저장된 파일정보를 db에 저장
	 * 
	 * 
	 * *MultipartRequest객체를 생성하면 기존의 HttpServletRequest객체로부터 사용자입력값을 가져올수 없다.
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 파일 업로드
		// b. 파일 저장경로
		ServletContext application = getServletContext(); 
		String saveDirectory = application.getRealPath("/upload/board");
		System.out.println("saveDirectory = " + saveDirectory);
		
		// c.최대파일용량 10mb = 1kb * 1000 * 10
		int maxPostSize = 1024 * 1024 * 10;
		
		// d.인코딩
		String encoding = "utf-8";
		// e.파일명 재지정 정책 객체
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		
		MultipartRequest multipartRequest = 
				new MultipartRequest(
						request, 
						saveDirectory, 
						maxPostSize, 
						encoding, 
						policy
					);
		// 파일정보 가져오기
		String originalFilename = multipartRequest.getOriginalFileName("upFile");
		String renamedFilename = multipartRequest.getFilesystemName("upFile");
		
		// 1. 사용자 입력값 처리
		String reserNo = multipartRequest.getParameter("reserNo");
		System.out.println("reserNo = " + reserNo);
		String title = multipartRequest.getParameter("title");
		String writer = multipartRequest.getParameter("writer");
		String content = multipartRequest.getParameter("content");
		String carName = multipartRequest.getParameter("carName");
		Double score = Double.parseDouble(multipartRequest.getParameter("score"));
		ReviewBoard reviewBoard = new ReviewBoard(0,reserNo, writer, title, content, carName, null, 0, score, 0, null);
		
		if(multipartRequest.getFile("upFile") != null) {
			Attachment attach = new Attachment();
			attach.setOriginalFilename(originalFilename);
			attach.setRenamedFilename(renamedFilename);
			System.out.println("ReviewBoardEnrollServlet@attach = " + attach);
			reviewBoard.setAttach(attach);
		}
		
		
		// 2. 업무로직
		int result = reviewBoardService.insertReviewBoard(reviewBoard);
		
		// 이용후기 작성될 때 마다 car_info테이블의 car_name에 해당하는 assess_cnt가 +1되도록
		int result2 = reviewBoardService.plusOneAssessCnt(carName);
		
		// 이용후기 작성될 때 마다 member테이블의 해당 아이디에 해당하는 mileage에 + 500 되도록
		int result3 = reviewBoardService.plusPoint(writer);
		
		// 이용후기 작성될 때 마다 car_info 테이블의 car_name에 해당하는 avg_score에 score가 입력되고 나누기 assess_cnt 해서 평균점수 구하기
		int result4 = reviewBoardService.insertAvgScore(score ,carName);
		
		
		
		System.out.println("ReviewBoardEnrollServlet@servlet = " + reviewBoard);
		
		// 3. 응답처리 : redirect
		HttpSession session = request.getSession();
		String location = request.getContextPath() + "/board/reviewBoardView?no=" + reviewBoard.getReviewNo();
		response.sendRedirect(location);
	}

}
