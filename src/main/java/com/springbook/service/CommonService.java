package com.springbook.service;

import java.util.List;

import com.springbook.vo.JusoVO;
import com.springbook.vo.ProductVO;
import com.springbook.vo.DateVO;

public interface CommonService {
	
	/**
	 * 날짜가져오기
	 * @param ProductVO
	 * @return
	 * @throws Exception
	 */
	public DateVO getDate() throws Exception;
	
	/**
	 * 제품명 가져오기
	 * @param ProductVO
	 * @return
	 * @throws Exception
	 */
	public List<ProductVO> getProductNm() throws Exception;
	
	/**
	 * 제품사이즈 가져오기
	 * @param ProductVO
	 * @return
	 * @throws Exception
	 */
	public List<ProductVO> getProductSize(ProductVO vo) throws Exception;
	
	/**
	 * 제품두께 가져오기
	 * @param ProductVO
	 * @return
	 * @throws Exception
	 */
	public List<ProductVO> getProductThickness(ProductVO vo) throws Exception;
	
	/**
	 * 시도 가져오기
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<JusoVO> getSido() throws Exception;
	
	/**
	 * 시군구 가져오기
	 * @param JusoVO
	 * @return
	 * @throws Exception
	 */
	public List<JusoVO> getSigungu(JusoVO vo) throws Exception;
	
	/**
	 * 읍면동 가져오기
	 * @param JusoVO
	 * @return
	 * @throws Exception
	 */
	public List<JusoVO> getEubmyeondong(JusoVO vo) throws Exception;
	
}
