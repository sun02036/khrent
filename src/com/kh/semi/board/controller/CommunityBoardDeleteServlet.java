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

import com.kh.semi.board.model.service.CommunityBoardService;
import com.kh.semi.board.model.service.QuestionBoardService;
import com.kh.semi.board.model.vo.Attachment;

/**
 * Servlet implementation class QnaBoardDeleteServlet
 */
@WebServlet("/board/communityBoardDelete")
public class CommunityBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CommunityBoardService boardService = new CommunityBoardService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
				int no = Integer.parseInt(request.getParameter("no"));
				
				
		// 2.업무로직
		Attachment attach = boardService.selectOneCommunityAttachment(no);
		
		// 글에 딸려있는 댓글과 첨부파일은 db에서 자동으로 삭제되지만(fk를 게시글번호 on delete cascade 했으므로) 컴퓨터에 저장되어있는 첨부파일은 따로 삭제해야한다.
		if(attach != null) {
			ServletContext application = getServletContext(); 
			String saveDirectory = application.getRealPath("/upload/board");
			String filename = attach.getRenamedFilename();
			File delFile = new File(saveDirectory, filename);
			boolean result = delFile.delete();
			System.out.printf("첨부파일[%s] 삭제여부 : %b%n", filename, result);
		}
		
		int result = boardService.deleteCommunityBoard(no);
		String msg = result > 0 ? "게시물 삭제 성공!" : "게시물 삭제 실패!";
			
		// 3. view단 처리. BoardListServlet으로 리다이렉트한다.
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		String location = request.getContextPath() + "/board/communityList";
		response.sendRedirect(location);
	}

}
