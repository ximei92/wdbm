package com.springbook.etc.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ProductSizeVO {
	private int sizeIdx;
	private Date regDate;
	private String productCd;
	private String size;
	private String height;
	private String width;
	private String customOrder;
}
