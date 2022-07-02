package com.springbook.etc.vo;

import java.util.Date;

import lombok.Data;

@Data
public class JoinAskVO {
	private String companyNm;
	private String ceoNm;
	private String businessNum;
	private String companyPhoneNum;
	private String addr1;
	private String addr2;
	private String managerNm;
	private String fileId;
	private int idx;
	private Date regDate;
	private String info;
	private String businessType;
	private int checkNew;
}
