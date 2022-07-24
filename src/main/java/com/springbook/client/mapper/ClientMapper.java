package com.springbook.client.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springbook.client.vo.ClientVO;
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
public interface ClientMapper {

	List<ClientVO> getSearchResult(ClientVO clientVO);

	int getFeeCnt(ClientVO clientVO);

	String getFee(ClientVO clientVO);

	List<ClientVO> getWarehouse();

	ClientVO getTonInfo(ClientVO clientVO);

	List<ClientVO> getPrice(ClientVO clientVO);

	String getTotalPrice(ClientVO clientVO);

	String getWarehouseNm(String warehouseIdx);





}
