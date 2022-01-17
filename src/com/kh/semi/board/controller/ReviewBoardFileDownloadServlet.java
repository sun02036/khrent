package com.kh.semi.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.service.ReviewBoardService;
import com.kh.semi.board.model.vo.Attachment;

/**
 * Servlet implementation class ReviewBoardFileDownloadServlet
 */
@WebServlet("/reviewboard/fileDownload")
public class ReviewBoardFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ReviewBoardService reviewBoardService = new ReviewBoardService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 2. 업무로직
		Attachment attach = reviewBoardService.selectOneReviewBoardAttachment(no);   
		System.out.println("attach@ReviewBoardFileDownloadServlet" + attach);
		
		// 3. view단 처리
		// 서버컴퓨터의 파일을 읽기 -> Http응답메시지에 쓰기
		
		// 저장된 경로의 파일 입력스트림
		String saveDirectory = getServletContext().getRealPath("/upload/board");
		String filename = attach.getRenamedFilename();
		File downFile = new File(saveDirectory, filename);
		System.out.println("downFile@BoardFileDownloadServlet" + downFile);
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
		
		// 응답메시지 출력스트림
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		// 응답메시지 헤더작성(브라우저에게 그냥 파일을 주는거니까 다운받으라고 알리는 메시지(?))
		response.setContentType("application/octet-stream; charset=utf-8");   // 이진데이터를 쓸 때 사용하는 메시지라고 한다. 뭔소리여...
		// 헤더부 한글 깨짐 방지
		String responseFilename = new String(attach.getOriginalFilename().getBytes("utf-8"), "iso-8859-1");
		response.setHeader("Content-Disposition", "attachment;filename=" + responseFilename);
		
		// 입력 & 출력
		int read = -1;
		while((read = bis.read()) != -1) {   // 파일내용을 다 읽으면 -1이 리턴된다.
			bos.write(read);
		}
		
		// 자원반납
		bos.close();
		bis.close();
	}


}
