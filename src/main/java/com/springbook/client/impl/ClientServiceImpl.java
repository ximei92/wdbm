package com.springbook.client.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.client.mapper.ClientMapper;
import com.springbook.client.service.ClientService;
import com.springbook.client.vo.ClientVO;
import com.springbook.etc.vo.ProductVO;
import com.springbook.etc.vo.WarehouseVO;
import com.springbook.member.vo.MemberVO;
import com.springbook.vo.JusoVO;
import com.springbook.warehousing.mapper.WarehousingMapper;
import com.springbook.warehousing.service.WarehousingService;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired

	private ClientMapper clientMapper;

	@Override
	public List<ClientVO> getSearchResult(ClientVO clientVO) {
		// TODO Auto-generated method stub
		return clientMapper.getSearchResult(clientVO);
	}

	@Override
	public int getFeeCnt(ClientVO clientVO) {
		// TODO Auto-generated method stub
		return clientMapper.getFeeCnt(clientVO);
	}

	@Override
	public String getFee(ClientVO clientVO) {
		// TODO Auto-generated method stub
		return clientMapper.getFee(clientVO);
	}

	@Override
	public List<ClientVO> getWarehouse() {
		// TODO Auto-generated method stub
		return clientMapper.getWarehouse();
	}

	@Override
	public ClientVO getTonInfo(ClientVO clientVO) {
		// TODO Auto-generated method stub
		return clientMapper.getTonInfo(clientVO);
	}

	@Override
	public List<ClientVO> getPrice(ClientVO clientVO) {
		// TODO Auto-generated method stub
		return clientMapper.getPrice(clientVO);
	}

	@Override
	public String getTotalPrice(ClientVO clientVO) {
		// TODO Auto-generated method stub
		return clientMapper.getTotalPrice(clientVO);
	}

	@Override
	public String getWarehouseNm(String warehouseIdx) {
		// TODO Auto-generated method stub
		return clientMapper.getWarehouseNm(warehouseIdx);
	}

	
}
