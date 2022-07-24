package com.springbook.warehousing.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springbook.etc.vo.CreditVO;
import com.springbook.etc.vo.JoinAskVO;
import com.springbook.etc.vo.ProductSizeVO;
import com.springbook.etc.vo.ProductThicknessVO;
import com.springbook.etc.vo.ProductVO;
import com.springbook.etc.vo.WarehouseInventoryVO;
import com.springbook.etc.vo.WarehouseStockVO;
import com.springbook.etc.vo.WarehouseVO;
import com.springbook.etc.vo.ProductDetailVO;
import com.springbook.member.vo.MemberVO;

@Mapper
public interface WarehousingMapper {


	List<MemberVO> getMakerList();

	Map<String, Object> getCalCtnInfo(Map<String, Object> map);

	Map<String, Object> getProductInfo(Map<String, Object> map);

	WarehouseVO getWarehouseInfo(String warehouseIdx);

	MemberVO getMemberInfo(String shipperId);

	int insertOrderCtn(Map<String, String> map);

	Map<String, Object> loadClientInfo(String id);

	List<Map<String, Object>> getProductCd();

	List<Map<String, Object>> getOrderList(Map<String, Object> map);

	int getOrderListCount();

	Map<String, Object> getOrderInfo(String idx);

	int updateOrderCtn(Map<String, String> map);

	int cancelOrderCtn(String orderIdx);

	Map<String, Object> getProductCdInfo(Map<String, Object> map);

	int insertOrderMultiCtn(List<Map<String, Object>> paramList);




}
