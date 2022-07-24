package com.springbook.warehousing.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springbook.etc.vo.*;
import com.springbook.member.vo.MemberVO;
import com.springbook.vo.JusoVO;

public interface WarehousingService {


	List<MemberVO> getMakerList();

	Map<String, Object> getCalCtnInfo(Map<String, Object> map);

	Map<String, Object> getProductInfo(String productIdx, String thickness, String size);

	WarehouseVO getWarehouseInfo(String warehouseIdx);

	MemberVO getMemberInfo(String shipperId);

	int insertOrderCtn(Map<String, String> map);

	Map<String, Object> loadClientInfo(String id);

	List<Map<String, Object>> getProductCd();

	List<Map<String, Object>> getOrderList(int i, int j);

	int getOrderListCount();

	Map<String, Object> getOrderInfo(String idx);

	int updateOrderCtn(Map<String, String> map);

	Map<String, Object> getProductCdInfo(Map<String, Object> map);

	int cancelOrderCtn(String orderIdx);

	int insertOrderMultiCtn(List<Map<String, Object>> paramList);

	

}
