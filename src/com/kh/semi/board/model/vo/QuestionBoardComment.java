package com.kh.semi.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class QuestionBoardComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int no;
	private int boardNo;
	private String writer;
	private String content;
	private Date regDate;
	private int commentLevel;	// 댓글 1, 대댓글 2
	private int commentRef;
	public QuestionBoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuestionBoardComment(int no, int boardNo, String writer, String content, Date regDate, int commentLevel,
			int commentRef) {
		super();
		this.no = no;
		this.boardNo = boardNo;
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
		this.commentLevel = commentLevel;
		this.commentRef = commentRef;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getCommentLevel() {
		return commentLevel;
	}
	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
	}
	public int getCommentRef() {
		return commentRef;
	}
	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
	}
	@Override
	public String toString() {
		return "QuestionBoardComment [no=" + no + ", boardNo=" + boardNo + ", writer=" + writer + ", content=" + content
				+ ", regDate=" + regDate + ", commentLevel=" + commentLevel + ", commentRef=" + commentRef + "]";
	}	
	
	
	
}
