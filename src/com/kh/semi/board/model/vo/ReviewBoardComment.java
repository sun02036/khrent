package com.kh.semi.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ReviewBoardComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private int review_no;
	private String writer;
	private String content;
	private Date regDate;
	private int commentLevel; // 댓글 1, 대댓글2
	private int commentRef;
	public ReviewBoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewBoardComment(int no, int review_no, String writer, String content, Date regDate, int commentLevel,
			int commentRef) {
		super();
		this.no = no;
		this.review_no = review_no;
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
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ReviewBoardComment [no=" + no + ", review_no=" + review_no + ", writer=" + writer + ", content="
				+ content + ", regDate=" + regDate + ", commentLevel=" + commentLevel + ", commentRef=" + commentRef
				+ "]";
	}
	
	

}
