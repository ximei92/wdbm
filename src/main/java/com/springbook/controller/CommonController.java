package com.springbook.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.mapper.FileMapper;
import com.springbook.service.CommonService;
import com.springbook.service.FileService;

import lombok.extern.slf4j.Slf4j;
import com.springbook.vo.JusoVO;
import com.springbook.vo.ProductVO;

/* 파일 controller */
@Controller
@Slf4j
public class CommonController {
	
	@Autowired
	private CommonService commonService;
	/**
	 * 시군구가져오기
	 * @param Map<String, Object> commandMap
	 * @return ModelAndView
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getSigunguAjax.do")
	public List<JusoVO> getSigunguAjax(@RequestParam Map<String, Object> commandMap) throws Exception {

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");

		String sidoCd = (String) commandMap.get("sidoCd");
		
		JusoVO jusoVO = new JusoVO();
		jusoVO.setSidoNm(sidoCd);
		
		List<JusoVO> sigunguList = commonService.getSigungu(jusoVO);
		
		modelAndView.addObject("sigunguList", sigunguList);

		return sigunguList;
	}
	
	/**
	 * 읍면동가져오기
	 * @param Map<String, Object> commandMap
	 * @return ModelAndView
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getEubmyeondongAjax.do")
	public List<JusoVO> getEubmyeondongAjax(@RequestParam Map<String, Object> commandMap) throws Exception {

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
    	
    	String sidoCd = (String) commandMap.get("sidoCd");
		String sigunguCd = (String) commandMap.get("sigunguCd");
		
		JusoVO jusoVO = new JusoVO();
		jusoVO.setSidoNm(sidoCd);
		jusoVO.setSigunguNm(sigunguCd);
		
		List<JusoVO> eubmyeondongList = commonService.getEubmyeondong(jusoVO);
		 
		modelAndView.addObject("eubmyeondongList", eubmyeondongList);

		return eubmyeondongList;
	}
	
	/**
	 * 제품두께 가져오기
	 * @param Map<String, Object> commandMap
	 * @return ModelAndView
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getProductThicknessAjax.do")
	public ModelAndView getProductThicknessAjax(@RequestParam Map<String, Object> commandMap) throws Exception {

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");

		String productIdx = (String) commandMap.get("productIdx");
		
		ProductVO productVO = new ProductVO();
		productVO.setPRODUCT_IDX(Integer.parseInt(productIdx));
		
		List<ProductVO> ptList = commonService.getProductThickness(productVO);
		
		modelAndView.addObject("ptList", ptList);

		return modelAndView;
	}
	
	/**
	 * 제품사이즈 가져오기
	 * @param Map<String, Object> commandMap
	 * @return ModelAndView
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getProductSizeAjax.do")
	public ModelAndView getProductSizeAjax(@RequestParam Map<String, Object> commandMap) throws Exception {

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");

		String productIdx = (String) commandMap.get("productIdx");
		
		ProductVO productVO = new ProductVO();
		productVO.setPRODUCT_IDX(Integer.parseInt(productIdx));
		
		List<ProductVO> psList = commonService.getProductSize(productVO);
		
		modelAndView.addObject("psList", psList);

		return modelAndView;
	}
	

		

}