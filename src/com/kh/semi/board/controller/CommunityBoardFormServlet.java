package com.kh.semi.board.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.board.model.service.QuestionBoardService;
import com.kh.semi.board.model.vo.Attachment;
import com.kh.semi.board.model.vo.QuestionBoard;
import com.kh.semi.common.MvcFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class CommunityBoardEnrollServlet
 */
@WebServlet("/board/commentBoardForm")
public class CommunityBoardFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QuestionBoardService questionBoardService = new QuestionBoardService();
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
		.getRequestDispatcher("/WEB-INF/views/board/communityBoardForm.jsp")
		.forward(request, response);
	}
	

}


