package com.springbook.utill.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;

public class NoExcelCellStyle implements ExcelCellStyle{

	@Override
	public void apply(CellStyle cellStyle) {
		// 아무것도 안함 
		
	}

}
