package com.springbook.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.mapper.CommonMapper;
import com.springbook.service.CommonService;
import com.springbook.vo.DateVO;
import com.springbook.vo.JusoVO;
import com.springbook.vo.ProductVO;
import com.springbook.warehousing.mapper.WarehousingMapper;
import com.springbook.warehousing.service.WarehousingService;

@Service("commonService")
public class CommonServiceImpl implements CommonService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceImpl.class);
		
		@Autowired
		private CommonMapper commonMapper;

	/**
	 * 날짜가져오기
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@Override
	public DateVO getDate() throws Exception{
		return commonMapper.getDate();
	}
	
	/**
	 * 제품명 가져오기
	 * @param ProductVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ProductVO> getProductNm() throws Exception{
		return commonMapper.getProductNm();
	}
	
	/**
	 * 제품사이즈 가져오기
	 * @param ProductVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ProductVO> getProductSize(ProductVO vo) throws Exception{
		return commonMapper.getProductSize(vo);
	}
	
	/**
	 * 제품두께 가져오기
	 * @param ProductVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ProductVO> getProductThickness(ProductVO vo) throws Exception{
		return commonMapper.getProductThickness(vo);
	}
	
	/**
	 * 시도 가져오기
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<JusoVO> getSido() throws Exception{
		System.out.println("dddddgetSIDOD");
		return commonMapper.getSido();
	}
	
	/**
	 * 시군구 가져오기
	 * @param JusoVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<JusoVO> getSigungu(JusoVO vo) throws Exception{
		return commonMapper.getSigungu(vo);
	}
	
	/**
	 * 읍면동 가져오기
	 * @param JusoVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<JusoVO> getEubmyeondong(JusoVO vo) throws Exception{
		return commonMapper.getEubmyeondong(vo);
	}

	
} 

