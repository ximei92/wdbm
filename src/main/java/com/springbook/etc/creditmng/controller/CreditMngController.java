package com.springbook.etc.creditmng.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.springbook.etc.creditmng.dto.CreditExcelDTO;
import com.springbook.etc.creditmng.service.CreditMngService;
import com.springbook.utill.excel.ExcelXlsxView;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/credit")
@AllArgsConstructor
public class CreditMngController {
	
	private final CreditMngService creditMngService;
	
	@PostMapping("/excel/down")
	@ResponseBody
	public ModelAndView downlaodDepositExcel(@RequestBody String jsonData) throws JsonMappingException, JsonProcessingException{
		List<CreditExcelDTO> creditList = creditMngService.parseJson(jsonData);
		Map<String,Object> excelData = creditMngService.getCreditExcelData(creditList);
		return new ModelAndView(new ExcelXlsxView(),excelData);
	}

}
