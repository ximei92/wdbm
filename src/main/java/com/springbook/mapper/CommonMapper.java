package com.springbook.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.springbook.vo.JusoVO;
import com.springbook.vo.ProductVO;
import com.springbook.vo.DateVO;

@Mapper
/* common dao */
public interface CommonMapper {

	DateVO getDate();

	List<ProductVO> getProductNm();

	List<ProductVO> getProductSize(ProductVO vo);

	List<ProductVO> getProductThickness(ProductVO vo);

	List<JusoVO> getSido();

	List<JusoVO> getSigungu(JusoVO vo);

	List<JusoVO> getEubmyeondong(JusoVO vo);
	

}