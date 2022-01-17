package com.kh.semi.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ReviewBoard implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int reviewNo;
	private String reservNo;
	private String reviewWriter;
	private String reviewTitle;
	private String reviewContent;
	private String carName;
	private Date regDate;
	private int readCount;
	private Double score;
	private int boardCommentCount;
	
	private Attachment attach;

	public ReviewBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewBoard(int reviewNo, String reservNo, String reviewWriter, String reviewTitle, String reviewContent,
			String carName, Date regDate, int readCount, Double score, int boardCommentCount, Attachment attach) {
		super();
		this.reviewNo = reviewNo;
		this.reservNo = reservNo;
		this.reviewWriter = reviewWriter;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.carName = carName;
		this.regDate = regDate;
		this.readCount = readCount;
		this.score = score;
		this.boardCommentCount = boardCommentCount;
		this.attach = attach;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getReservNo() {
		return reservNo;
	}

	public void setReservNo(String reservNo) {
		this.reservNo = reservNo;
	}

	public String getReviewWriter() {
		return reviewWriter;
	}

	public void setReviewWriter(String reviewWriter) {
		this.reviewWriter = reviewWriter;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
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

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
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
		return "ReviewBoard [reviewNo=" + reviewNo + ", reservNo=" + reservNo + ", reviewWriter=" + reviewWriter
				+ ", reviewTitle=" + reviewTitle + ", reviewContent=" + reviewContent + ", carName=" + carName
				+ ", regDate=" + regDate + ", readCount=" + readCount + ", score=" + score + ", boardCommentCount="
				+ boardCommentCount + ", attach=" + attach + "]";
	}

	
	
	
	
	
	
}
