package com.springbook.warehousing.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.member.vo.MemberVO;
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


}
