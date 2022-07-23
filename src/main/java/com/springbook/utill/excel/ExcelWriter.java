package com.springbook.utill.excel;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.springbook.etc.creditmng.dto.CreditExcelDTO;
import com.springbook.utill.excel.annotation.ExcelColumn;
import com.springbook.utill.excel.annotation.ExcelFileName;

/**
 * 엑셀 다운로드  라이브러리
 * @author chan
 *
 */
public class ExcelWriter {

	private final Workbook workbook;
	private final Map<String, Object> data;
	private final HttpServletResponse response;

	public ExcelWriter(Workbook workbook, Map<String, Object> data, HttpServletResponse response) {
		this.workbook = workbook;
		this.data = data;
		this.response = response;
	}

	/**
	 * 엑셀파일 생성  
	 */
	public void create() {

		Sheet sheet = workbook.createSheet();
		CellStyle cellStyle = workbook.createCellStyle();
		setCellStyle(cellStyle);
		setColumnWidth(sheet, mapToColumnWidthList());
		createHead(sheet, cellStyle, mapToHeaderList());
		createBody(sheet, cellStyle, mapToBodyList());
		
		String encodedFileName = encodeFileName(mapToFileName());
		setFileNameWithHeader(encodedFileName,response);
	}

	/**
	 * 파일명을 UTF8로  인코딩 
	 * 
	 * @param fileName
	 * @return
	 */
	private String encodeFileName(String fileName) {
		try {
			return URLEncoder.encode(fileName+".xlsx","UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("file name is not exist");
		}
	}

	/**
	 * 응답헤더 세팅 
	 * 
	 * @param fileName
	 * @param response
	 */
	private void setFileNameWithHeader(String fileName, HttpServletResponse response) {
		  response.reset();
		  response.setHeader("Set-Cookie", "fileDownload=true; path=/");
		  response.setContentType("application/download;charset=UTF-8");
		  response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		  response.setHeader("Content-Transfer-Encoding", "binary");
	}


	/**
	 * 파일 명을 가져온다. 
	 * @return
	 */
	private String mapToFileName() {
		return (String)data.get("fileName");
	}

	/**
	 * @param sheet
	 * @param cellStyle
	 * @param mapToBodyList
	 */
	private void createBody(Sheet sheet, CellStyle style, List<List<String>> bodyList) {
		int rowSize = bodyList.size();
		for (int i = 0; i < rowSize; i++) {
			createRow(sheet,style,bodyList.get(i), i+1);
		}

	}

	@SuppressWarnings("unchecked")
	private List<List<String>> mapToBodyList() {
		return (List<List<String>>) data.get("body");
	}

	/**
	 * 엑셀 헤더 생성
	 * 
	 * @param sheet
	 * @param cellStyle
	 * @param mapToHeaderList
	 */
	private void createHead(Sheet sheet, CellStyle cellStyle, List<String> mapToHeaderList) {
		createRow(sheet, cellStyle, mapToHeaderList, 0);
	}

	/**
	 * 엑셀 row 생성
	 * 
	 * @param sheet
	 * @param cellStyle
	 * @param mapToHeaderList
	 * @param rownum
	 */
	private void createRow(Sheet sheet, CellStyle style, List<String> cells, int rowNum) {
		Row row = sheet.createRow(rowNum);

		for (int i = 0; i < cells.size(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(cells.get(i));
		}

	}

	@SuppressWarnings("unchecked")
	private List<String> mapToHeaderList() {
		return (List<String>) data.get("header");
	}

	/**
	 * 컬럼 간격을 지정한다.
	 * 
	 * @param sheet
	 * @param columnWidths
	 */
	private void setColumnWidth(Sheet sheet, List<Integer> columnWidths) {
		for (int i = 0; i < columnWidths.size(); i++) {
			sheet.setColumnWidth(i, columnWidths.get(i));
		}
	}

	@SuppressWarnings("unchecked")
	private List<Integer> mapToColumnWidthList() {
		return (List<Integer>) data.get("columnWidth");
	}

	private void setCellStyle(CellStyle cellStyle) {
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
	}

	/**
	 * 엑셀 데이터 맵 생성
	 * 
	 * @param data
	 * @param target
	 * @return
	 */
	public static Map<String, Object> createExcelData(List<? extends ExcelDTO> data, Class<CreditExcelDTO> target) {
		Map<String, Object> excelData = new HashMap<>();
		excelData.put("fileName", createFileName(target));
		excelData.put("header", createHeaderData(target));
		excelData.put("body", createBodyData(data));
		excelData.put("columnWidth", createColumnWidth(target));
		return excelData;
	}

	/**
	 * 컬럼 넓이 생성
	 * 
	 * @param target
	 * @return
	 */
	private static List<Integer> createColumnWidth(Class<?> target) {
		List<Integer> columnWidths = new ArrayList<>();
		for (Field field : target.getDeclaredFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(ExcelColumn.class)) {
				int columnWidth = field.getAnnotation(ExcelColumn.class).columnWidth();
				columnWidths.add(columnWidth);
			}
		}
		return columnWidths;
	}

	/**
	 * 엑셀 바디 데이터 생성
	 * 
	 * @param list
	 * @return List<List<String>> bodyData
	 */
	private static List<List<String>> createBodyData(List<? extends ExcelDTO> list) {
		List<List<String>> bodyData = new ArrayList<>();
		for (ExcelDTO dto : list) {
			bodyData.add(dto.mapToList());
		}
		return bodyData;
	}

	/**
	 * 엑셀 헤더 생성
	 * 
	 * @param header
	 * @return
	 */
	private static List<String> createHeaderData(Class<?> header) {
		List<String> headData = new ArrayList<>();
		for (Field field : header.getDeclaredFields()) {
			field.setAccessible(true);

			// 존재하는 어노테이션이 있는지 검사한다.
			if (field.isAnnotationPresent(ExcelColumn.class)) {

				// 필드에 어노테이션으로 지정된 헤더네임을 가져온다.
				String headerName = field.getAnnotation(ExcelColumn.class).headerName();

				// headerName이 지정되어 있지 않으면 필드 이름을 넣는다.
				if (headerName.equals("")) {
					headData.add(field.getName());
				} else {
					headData.add(headerName);
				}
			}

		}
		return headData;
	}

	/**
	 * 엑셀 파일명 생성
	 * 
	 * @param clazz
	 * @return
	 */
	private static String createFileName(Class<?> clazz) {
		if (clazz.isAnnotationPresent(ExcelFileName.class)) {
			String fileName = clazz.getAnnotation(ExcelFileName.class).fileName();
			// 파일명이 지정되어있지 않을 경우 클래스명을 파일 명으로 리턴한다.
			return fileName.equals("") ? clazz.getSimpleName() : fileName;

		} else {
			throw new RuntimeException("excel fileName not exist");
		}

	}

}
