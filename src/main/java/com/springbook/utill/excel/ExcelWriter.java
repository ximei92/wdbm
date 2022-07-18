package com.springbook.utill.excel;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 엑셀 쓰기 라이브러리
 *  
 * 
 * @author chan
 *
 */
public class ExcelWriter {
	
	private final Workbook workbook;
	private final Map<String, Object> data;
	private final HttpServletResponse response;
	
	public ExcelWriter(Workbook workbook, Map<String,Object> data, HttpServletResponse response) {
		this.workbook = workbook;
		this.data = data;
		this.response = response;
	}

	public void create() {
		
	}
	
	

}
