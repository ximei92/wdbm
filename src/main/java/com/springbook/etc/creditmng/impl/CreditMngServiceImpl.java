package com.springbook.etc.creditmng.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbook.etc.creditmng.dto.CreditExcelDTO;
import com.springbook.etc.creditmng.mapper.CreditMngMapper;
import com.springbook.etc.creditmng.service.CreditMngService;
import com.springbook.utill.excel.ExcelWriter;

import lombok.AllArgsConstructor;

/**
 * <h3>여신관리 서비스 구현체</h3>
 * 
 * @author chan
 *
 */
@Service
@AllArgsConstructor
public class CreditMngServiceImpl implements CreditMngService {

	private final CreditMngMapper creditMngMapper;

	@Override
	public List<CreditExcelDTO> parseJson(String jsonData) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<CreditExcelDTO> resultList = mapper.readValue(
				jsonData, new TypeReference<ArrayList<CreditExcelDTO>>(){});
		return resultList;
	}

	@Override
	public Map<String, Object> getCreditExcelData(List<CreditExcelDTO> creditList) {
		return ExcelWriter.createExcelData(creditList,CreditExcelDTO.class);
	}

}
