package com.kh.semi.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class CommunityBoard implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String writer;
	private String title;
	private String content;
	private Date regDate;
	private int readCount;
	private int boardCommentCount;
	
	private Attachment attach;
	
	public CommunityBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public CommunityBoard(int no, String writer, String title, String content, Date regDate, int readCount,
			int boardCommentCount, Attachment attach) {
		super();
		this.no = no;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.readCount = readCount;
		this.boardCommentCount = boardCommentCount;
		this.attach = attach;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getBoardCommentCount() {
		return boardCommentCount;
	}

	public void setBoardCommentCount(int boardCommentCount) {
		this.boardCommentCount = boardCommentCount;
	}

	public Attachment getAttach() {
		return attach;
	}

	public void setAttach(Attachment attach) {
		this.attach = attach;
	}

	@Override
	public String toString() {
		return "CommunityBoard [no=" + no + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", regDate=" + regDate + ", readCount=" + readCount + ", boardCommentCount=" + boardCommentCount
				+ ", attach=" + attach + "]";
	}

	
	
	
	
}
