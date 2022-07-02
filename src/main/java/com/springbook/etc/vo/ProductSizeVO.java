package com.springbook.etc.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ProductSizeVO {
	private int fileId;
	private int productIdx;
	private Date regDate;
	private String productNm;
	private String size;
	private String height;
	private String width;
	private String customOrder;
}
