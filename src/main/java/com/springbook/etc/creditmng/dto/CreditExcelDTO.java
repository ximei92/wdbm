package com.springbook.etc.creditmng.dto;

import java.util.Arrays;
import java.util.List;

import com.springbook.utill.excel.ExcelDTO;
import com.springbook.utill.excel.annotation.ExcelColumn;
import com.springbook.utill.excel.annotation.ExcelFileName;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h3>여신 관리 dto
 * <h3>
 *
 * @author chan
 *
 */
@Data
@NoArgsConstructor
@ExcelFileName(fileName = "여신관리현황")
public class CreditExcelDTO implements ExcelDTO {
	@ExcelColumn(headerName = "입금일", columnWidth = 2000)
	private String depDate; // 입금일
	
	@ExcelColumn(headerName = "회사명", columnWidth = 3000)
	private String companyNm; // 회사명
	
	@ExcelColumn(headerName = "입금액", columnWidth = 3000)
	private String depAmt; // 입금액
	
	@ExcelColumn(headerName = "여신설정 금액", columnWidth = 3000)
	private String creditSetAmt; // 여신설정 금액
	
	@ExcelColumn(headerName = "총 발주액", columnWidth = 3000)
	private String totalOrderAmt; // 총 발주액

	@ExcelColumn(headerName = "총 입금액", columnWidth = 3000)
	private String totalDepAmt; // 총 입금액
	
	@ExcelColumn(headerName = "주문 가능 금액", columnWidth = 3000)
	private String availableAmt; // 주문가능금액

	@Override
	public List<String> mapToList() {
		return Arrays.asList(depDate, companyNm, depAmt, creditSetAmt, totalOrderAmt, totalDepAmt, availableAmt);
	}

}
