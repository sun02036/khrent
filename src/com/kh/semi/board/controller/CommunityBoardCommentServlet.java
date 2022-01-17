package com.kh.semi.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.service.CommunityBoardService;
import com.kh.semi.board.model.vo.CommunityBoardComment;

/**
 * Servlet implementation class CommunityBoardCommentServlet
 */
@WebServlet("/board/communityBoardCommentEnroll")
public class CommunityBoardCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommunityBoardService boardService = new CommunityBoardService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 입력값 가져오기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String writer = request.getParameter("writer");
		int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
		int commentRef = Integer.parseInt(request.getParameter("commentRef"));
		String content = request.getParameter("content");
		
		
		CommunityBoardComment communityBoardComment = new CommunityBoardComment(0,commentLevel,writer,content,boardNo,commentRef,null);		
		System.out.println("communityBoardComment = " +communityBoardComment);
		//업무로직
		int result = boardService.insertCommunityComment(communityBoardComment);
		String msg = "";
		
		if(result>0)
			msg = "댓글이 등록되었습니다.";
		else
			msg = "댓글이 등록되지 않았습니다.";
		
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath()+"/board/boardView?no="+boardNo;
		response.sendRedirect(location);
	}

}
