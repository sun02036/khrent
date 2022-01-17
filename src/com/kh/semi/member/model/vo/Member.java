package com.kh.semi.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String memberId;
	private String password;
	private String memberRole;
	private String memberName;
	private String phone;
	private int mileage;
	private Date regDate;
	private String issue_date;
	private String license_type;
	private String license_no;
	
	public Member() {
		super();
	}

	public Member(String memberId, String password, String memberRole, String memberName, String phone, int mileage,
			Date regDate, String issue_date, String license_type, String license_no) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.memberRole = memberRole;
		this.memberName = memberName;
		this.phone = phone;
		this.mileage = mileage;
		this.regDate = regDate;
		this.issue_date = issue_date;
		this.license_type = license_type;
		this.license_no = license_no;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}

	public String getLicense_type() {
		return license_type;
	}

	public void setLicense_type(String license_type) {
		this.license_type = license_type;
	}

	public String getLicense_no() {
		return license_no;
	}

	public void setLicense_no(String license_no) {
		this.license_no = license_no;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", memberRole=" + memberRole
				+ ", memberName=" + memberName + ", phone=" + phone + ", mileage=" + mileage + ", regDate=" + regDate
				+ ", issue_date=" + issue_date + ", license_type=" + license_type + ", license_no=" + license_no + "]";
	}
	
}
