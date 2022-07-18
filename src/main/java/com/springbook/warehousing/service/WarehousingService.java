package com.springbook.warehousing.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springbook.etc.vo.*;
import com.springbook.member.vo.MemberVO;

public interface WarehousingService {

	List<MemberVO> getMakerList();

	Map<String, Object> getCalCtnInfo(Map<String, Object> map);

	

}
