package com.springbook.etc.vo;

import java.util.Date;

import lombok.Data;

@Data
public class WarehouseInventoryVO {
	private String warehouseIdx;
	private String productIdx;
	private String size;
	private String thickness;
	private int stock;
	private String reason;
	private Date regDate;

}
