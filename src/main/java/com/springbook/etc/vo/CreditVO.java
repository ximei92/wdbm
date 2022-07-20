package com.springbook.etc.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CreditVO {
	
	
	private int fileId;
	private int productIdx;
	private Date depDate;
	private String companyNm;
	private String depAmount;
	private String creditAmount;
	private String orderAmount;
	private String totalDep;
	private String orderAbleAmount;
	
}
