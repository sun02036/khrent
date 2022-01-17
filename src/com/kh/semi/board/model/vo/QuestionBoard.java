package com.kh.semi.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class QuestionBoard implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String writer;
	private String qna_title;
	private String qna_content;
	private Date regDate;
	private String answer_status;
	private int readCount;
	private int boardCommentCount;
	
	private Attachment attach;

	public QuestionBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionBoard(int no, String writer, String qna_title, String qna_content, Date regDate,
			String answer_status, int readCount, int boardCommentCount, Attachment attach) {
		super();
		this.no = no;
		this.writer = writer;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.regDate = regDate;
		this.answer_status = answer_status;
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

	public String getQna_title() {
		return qna_title;
	}

	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}

	public String getQna_content() {
		return qna_content;
	}

	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getAnswer_status() {
		return answer_status;
	}

	public void setAnswer_status(String answer_status) {
		this.answer_status = answer_status;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "QuestionBoard [no=" + no + ", writer=" + writer + ", qna_title=" + qna_title + ", qna_content="
				+ qna_content + ", regDate=" + regDate + ", answer_status=" + answer_status + ", readCount=" + readCount
				+ ", boardCommentCount=" + boardCommentCount + ", attach=" + attach + "]";
	}

	
	
	
	
	
}
