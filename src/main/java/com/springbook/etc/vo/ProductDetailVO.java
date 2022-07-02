package com.springbook.etc.vo;

import java.util.Date;
import lombok.Data;

@Data
public class ProductDetailVO {
	private int productIdx;
	private Date regDate;
	private String productNm;
	private String thickness;
	private String size;
	private String height;
	private String width;
	private String safety;
	private String pallet;
	private String slice;
	private int stock;
}
