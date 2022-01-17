package com.kh.semi.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class MemberDel extends Member implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date delDate;
	
	public MemberDel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberDel(String memberId, String password, String memberRole, String memberName, String phone, int mileage,
			Date regDate, String issue_date, String license_type, String license_no, Date delDate) {
		super(memberId, password, memberRole, memberName, phone, mileage, regDate, issue_date, license_type, license_no);
		this.delDate = delDate;
		// TODO Auto-generated constructor stub
	}
	
	public Date getDelDate() {
		return delDate;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}
	
	@Override
	public String toString() {
		return "delMember [delDate=" + delDate + ", toString()=" + super.toString() + "]";
	}

}
