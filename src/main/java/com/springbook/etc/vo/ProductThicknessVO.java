package com.springbook.etc.vo;

import java.util.Date;
import lombok.Data;

@Data
public class ProductThicknessVO {
	private int fileId;
	private int productIdx;
	private Date regDate;
	private String productNm;
	private String thickness;
	private String testInstitute;
	private String testNum;
	private String customOrder;
	private String fileNm;
}
