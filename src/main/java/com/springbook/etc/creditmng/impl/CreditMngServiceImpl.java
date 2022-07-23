package com.springbook.etc.creditmng.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
		return mapper.readValue(jsonData, new TypeReference<ArrayList<CreditExcelDTO>>(){});
	}

	@Override
	public Map<String, Object> getCreditExcelData(List<CreditExcelDTO> creditList) {
		return ExcelWriter.createExcelData(creditList,CreditExcelDTO.class);
	}
	
	@Override
	public int creditListCount(String keyword, Date startDt, Date endDt) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();

		map.put("keyword", keyword);
		map.put("startDt", startDt);
		map.put("endDt", endDt);
		
		return creditMngMapper.creditListCount(map);
	}
	
	@Override
	public List<Map<String, Object>> getCreditList(int i, int contentnum, String keyword, Date startDt, Date endDt) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		map.put("keyword", keyword);
		map.put("startDt", startDt);
		map.put("endDt", endDt);
		System.out.println("ddddd");
		System.out.println(map);
		return creditMngMapper.getCreditList(map);
	}

}
