package com.kh.semi.board.model.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class NoticeBoard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private Date regDate;
	private int readCount;
	
	
	@Override
	public String toString() {
		return "NoticeBoardService [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent="
				+ noticeContent + ", regDate=" + regDate + ", readCount=" + readCount + "]";
	}


	public NoticeBoard(int noticeNo, String noticeTitle, String noticeContent, Date regDate, int readCount) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.regDate = regDate;
		this.readCount = readCount;
	}


	public int getNoticeNo() {
		return noticeNo;
	}


	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}


	public String getNoticeTitle() {
		return noticeTitle;
	}


	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}


	public String getNoticeContent() {
		return noticeContent;
	}


	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public NoticeBoard() {
		super();
		// TODO Auto-generated constructor stub
	}


	
}
