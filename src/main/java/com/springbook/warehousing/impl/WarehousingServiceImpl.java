package com.springbook.warehousing.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.etc.vo.ProductVO;
import com.springbook.etc.vo.WarehouseVO;
import com.springbook.member.vo.MemberVO;
import com.springbook.vo.JusoVO;
import com.springbook.warehousing.mapper.WarehousingMapper;
import com.springbook.warehousing.service.WarehousingService;

@Service
public class WarehousingServiceImpl implements WarehousingService {
	@Autowired

	private WarehousingMapper warehousingMapper;

	@Override
	public List<MemberVO> getMakerList() {
		// TODO Auto-generated method stub
		return warehousingMapper.getMakerList();
	}

	@Override
	public Map<String, Object> getCalCtnInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return warehousingMapper.getCalCtnInfo(map);
	}

	@Override
	public Map<String, Object> getProductInfo(String productIdx, String thickness, String size) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("productIdx", productIdx);
		map.put("thickness", thickness);
		map.put("size", size);
		
		return warehousingMapper.getProductInfo(map);
	}

	@Override
	public WarehouseVO getWarehouseInfo(String warehouseIdx) {
		// TODO Auto-generated method stub
		return warehousingMapper.getWarehouseInfo(warehouseIdx);
	}

	@Override
	public MemberVO getMemberInfo(String shipperId) {
		// TODO Auto-generated method stub
		return warehousingMapper.getMemberInfo(shipperId);
	}

	@Override
	public int insertOrderCtn(Map<String, String> map) {
		// TODO Auto-generated method stub
		return warehousingMapper.insertOrderCtn(map);
	}

	@Override
	public Map<String, Object> loadClientInfo(String id) {
		// TODO Auto-generated method stub
		return warehousingMapper.loadClientInfo(id);
	}

	@Override
	public List<Map<String, Object>> getProductCd() {
		// TODO Auto-generated method stub
		return warehousingMapper.getProductCd();
	}

	@Override
	public List<Map<String, Object>> getOrderList(int i, int contentnum) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		return warehousingMapper.getOrderList(map);
	}

	@Override
	public int getOrderListCount() {
		// TODO Auto-generated method stub
		return warehousingMapper.getOrderListCount();
	}

	@Override
	public Map<String, Object> getOrderInfo(String idx) {
		// TODO Auto-generated method stub
		return warehousingMapper.getOrderInfo(idx);
	}

	@Override
	public int updateOrderCtn(Map<String, String> map) {
		// TODO Auto-generated method stub
		return warehousingMapper.updateOrderCtn(map);
	}

	@Override
	public Map<String, Object> getProductCdInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return warehousingMapper.getProductCdInfo(map);
	}

	@Override
	public int cancelOrderCtn(String orderIdx) {
		// TODO Auto-generated method stub
		return warehousingMapper.cancelOrderCtn(orderIdx);
	}

	@Override
	public int insertOrderMultiCtn(List<Map<String, Object>> paramList) {
		// TODO Auto-generated method stub
		return warehousingMapper.insertOrderMultiCtn(paramList);
	}


	
}
