package com.springbook.etc.vo;

import java.util.Date;

import lombok.Data;

@Data
public class WarehouseVO {
	private int fildId;
	private String warehouse;
	private Date regDate;
	private int idx;
}
