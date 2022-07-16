package com.springbook.warehousing.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.warehousing.mapper.WarehousingMapper;
import com.springbook.warehousing.service.WarehousingService;

@Service
public class WarehousingServiceImpl implements WarehousingService {
	@Autowired
	private WarehousingMapper WarehousingMapper;


}
