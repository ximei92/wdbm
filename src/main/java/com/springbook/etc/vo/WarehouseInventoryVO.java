package com.springbook.etc.vo;

import java.util.Date;

import lombok.Data;

@Data
public class WarehouseInventoryVO {
	private String warehouse;
	private String productCd;
	private String productNm;
	private String size;
	private String height;
	private String width;
	private String thickness;
	private int stock;
	private String reason;
	private Date regDate;

}
