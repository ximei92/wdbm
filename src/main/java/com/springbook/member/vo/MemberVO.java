package com.springbook.member.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	
	private String id;
	private int idx;
	private String password;
	private String companyNm;
	private String ceoNm;
	private String businessNum;
	private String companyPhoneNum;
	private String role;
	private String faxNum;
	private String addr1;
	private String addr2;
	private String managerNm;
	private String managerNum;
	private String email1;
	private String email2;
	private String email3;
	private String fileId;
	private String maker;
	private String specialMessage;
	private String incomeMessage;
	private String creditAmount;
	private String warehouse;
	private String delYn;
	private Date regDate;
}
