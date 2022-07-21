package com.springbook.etc.creditmng.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CreditMngMapper {
	
	int creditListCount(Map<String, Object> map);
	
	List<Map<String, Object>> getCreditList(Map<String, Object> map);



}
