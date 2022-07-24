package com.springbook.client.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springbook.client.vo.ClientVO;
import com.springbook.etc.vo.*;
import com.springbook.member.vo.MemberVO;
import com.springbook.vo.JusoVO;

public interface ClientService {

	List<ClientVO> getSearchResult(ClientVO clientVO);

	int getFeeCnt(ClientVO clientVO);

	String getFee(ClientVO clientVO);

	List<ClientVO> getWarehouse();

	ClientVO getTonInfo(ClientVO clientVO);

	List<ClientVO> getPrice(ClientVO clientVO);

	String getTotalPrice(ClientVO clientVO);

	String getWarehouseNm(String warehouseIdx);

	

}
