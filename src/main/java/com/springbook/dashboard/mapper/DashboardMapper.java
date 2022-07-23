package com.springbook.dashboard.mapper;

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
public interface DashboardMapper {

	List<Map<String, Object>> getLeakSafety();




}
