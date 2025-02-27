package com.kh.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String phone;
	private String email;
	private String address;
	private String interest;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	
	public Member(String userId, String userPwd, String userName, String phone, String email, String address,
			String interest) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.interest = interest;
	}

	public Member(String userId, String phone, String email, String address, String interest) {
		super();
		this.userId = userId;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.interest = interest;
	}

	public Member() {
		super();
	}
	
	
	
}