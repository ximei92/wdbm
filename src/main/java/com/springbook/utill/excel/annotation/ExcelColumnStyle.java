package com.springbook.utill.excel.annotation;

import com.springbook.utill.excel.style.ExcelCellStyle;

/**
 * 
 * 
 * @author chan
 *
 */
public @interface ExcelColumnStyle {

	Class<? extends ExcelCellStyle> excelCellStyleClass();

	/**
	 * enum 이름
	 * 
	 * @return
	 */
	String enumName() default "";

}
