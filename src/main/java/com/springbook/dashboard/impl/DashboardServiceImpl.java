package com.springbook.dashboard.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.dashboard.mapper.DashboardMapper;
import com.springbook.dashboard.service.DashboardService;
import com.springbook.etc.vo.ProductVO;
import com.springbook.etc.vo.WarehouseVO;
import com.springbook.member.vo.MemberVO;
import com.springbook.vo.JusoVO;
import com.springbook.warehousing.mapper.WarehousingMapper;
import com.springbook.warehousing.service.WarehousingService;

@Service
public class DashboardServiceImpl implements DashboardService {
	@Autowired
	private DashboardMapper dashboardMapper;

	@Override
	public List<Map<String, Object>> getLeakSafety() {
		// TODO Auto-generated method stub
		return dashboardMapper.getLeakSafety();
	}

	
}
