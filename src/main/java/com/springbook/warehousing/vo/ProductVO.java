package com.springbook.warehousing.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ProductVO {
	private int fileId;
	private int productIdx;
	private int thicknessIdx;
	private Date regDate;
	private String productNm;
	private String productCd;
	private String type;
	private String fileNm;
}
