package com.springbook.etc.vo;

import java.util.Date;
import lombok.Data;

@Data
public class ProductThicknessVO {
	private int fileId;
	private int thicknessIdx;
	private Date regDate;
	private int productIdx;
	private String thickness;
	private String testInstitute;
	private String testNum;
	private String customOrder;
}
