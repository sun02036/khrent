package com.kh.semi.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.service.CommunityBoardService;
import com.kh.semi.board.model.service.QuestionBoardService;
import com.kh.semi.board.model.vo.Attachment;
import com.kh.semi.board.model.vo.CommunityBoard;
import com.kh.semi.board.model.vo.QuestionBoard;
import com.kh.semi.common.MvcFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class QnaBoardUpdateServlet
 */
@WebServlet("/board/communityBoardUpdate")
public class CommunityBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CommunityBoardService boardService = new CommunityBoardService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 2. 업무로직
		CommunityBoard communityBoard = boardService.selectOneBoard(no);
		System.out.println("communityBoard 글 불러온 상태@CommunityBoardUpdateServlet = " + communityBoard);		

		// 3. view단으로 포워드
		request.setAttribute("communityBoard", communityBoard);
		request
			.getRequestDispatcher("/WEB-INF/views/board/communityBoardUpdate.jsp")
			.forward(request, response);
		
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. MultipartRequest 객체 생성
		String saveDirectory = getServletContext().getRealPath("/upload/board");
		
		// 최대저장용량 10mb
		int maxPostSize = 1024 * 1024 * 10;
		
		// 인코딩
		String encoding = "utf-8";
		
		// 파일명 재지정 정책 객체
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		
		MultipartRequest multipartRequest = 
				new MultipartRequest(
						request, 
						saveDirectory, 
						maxPostSize, 
						encoding, 
						policy
									);
		
		// 1. 사용자입력값 처리
		int no = Integer.parseInt(multipartRequest.getParameter("no"));
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");

//		CommunityBoard communityBoard = new CommunityBoard(no, null, title, content, null, null, 0, 0, null);
		CommunityBoard communityBoard = new CommunityBoard(no, null, title, content, null, 0, 0, null);
		// 첨부파일 
		if(multipartRequest.getFile("upFile") != null) {
			Attachment attach = new Attachment();
			attach.setBoardNo(no);
			attach.setOriginalFilename(multipartRequest.getOriginalFileName("upFile"));
			attach.setRenamedFilename(multipartRequest.getFilesystemName("upFile"));
			
			System.out.println("attach@CommunityBoardUpdateServlet = " + attach);
			communityBoard.setAttach(attach);
		}
		
		System.out.println("Community 수정한 첨부파일 붙인 상태@CommunityBoardUpdateServlet = " + communityBoard);
		
		// 2. 업무로직
		int result = 0;
		
		// 첨부파일이 변경되었거나 삭제 체크박스가 체크된 경우, 기존 첨부파일 삭제
		String delFile = multipartRequest.getParameter("delFile");    // 기존 첨부파일의 pk 번호. 이걸로 db에서 찾아서 삭제할 수 있다.
		if(delFile != null) {
			int attachNo = Integer.parseInt(delFile);
			Attachment attach = boardService.selectOneCommunityAttachment(attachNo);
			
			// 서버컴퓨터에서 파일 삭제
			File targetFile = new File(saveDirectory, attach.getRenamedFilename());
			targetFile.delete();
			
			// db 레코드 삭제
			result = boardService.deleteCommunityAttachment(attachNo);
			System.out.println(result > 0 ? "첨부파일 삭제 성공" : "첨부파일 삭제 실패");
			
		}
		
		// 게시물 수정 + 새 첨부파일 등록
		result = boardService.updateCommunityBoard(communityBoard);
		String msg = result > 0 ? "게시글이 수정되었습니다." : "게시글 수정에 실패했습니다.";
		
		// 3. 응답처리 (DML 하면 리다이렉트)
		request.getSession().setAttribute("msg", msg);
		
		String location = request.getContextPath() + "/board/boardView?no=" + no;
		response.sendRedirect(location);
		
	}

}



















