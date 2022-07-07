package com.springbook.etc.vo;

import java.util.Date;

import lombok.Data;

@Data
public class WarehouseVO {
	private int fileId;
	private String warehouse;
	private Date regDate;
	private int warehouseIdx;
}
