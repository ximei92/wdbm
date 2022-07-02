package com.springbook.vo;

import java.util.Date;

import lombok.Data;

//@Getter
//@Setter
@Data
public class FileVO {
	private int fileId;
	private String origNm;
	private String logiNm;
	private String logiPath;
	private String ext;
	private int size;
	private Date regDate;
}
