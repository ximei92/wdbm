
package com.springbook.warehousing.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;


import com.springbook.configuration.Page;
import com.springbook.etc.service.EtcService;
import com.springbook.etc.vo.CreditVO;
import com.springbook.etc.vo.JoinAskVO;
import com.springbook.etc.vo.ProductSizeVO;
import com.springbook.etc.vo.ProductThicknessVO;
import com.springbook.etc.vo.ProductVO;
import com.springbook.etc.vo.WarehouseInventoryVO;
import com.springbook.etc.vo.WarehouseVO;
import com.springbook.etc.vo.WarehouseStockVO;
import com.springbook.etc.vo.ProductDetailVO;
import com.springbook.member.vo.MemberVO;
import com.springbook.service.FileService;
import com.springbook.vo.FileVO;

import com.springbook.warehousing.service.WarehousingService;

import ch.qos.logback.core.net.SyslogOutputStream;


import ch.qos.logback.core.net.SyslogOutputStream;


@Controller
public class WarehousingController {
	@Autowired
	private EtcService etcService;
	@Autowired
	private FileService fileService;

	@Autowired
	private WarehousingService warehousingService;
	
	@Autowired(required = false)
	private PasswordEncoder encoder;


	@GetMapping("/manageOrder.do")    
	public String inquiryList(Model model,String pagenum, String contentnum, String keyword) {
		List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);
		List<ProductVO> productNameList = etcService.getProductNameListGroup();
		List<MemberVO> makerList = warehousingService.getMakerList();
		
		model.addAttribute("warehouse", warehouse);
		model.addAttribute("productList", productNameList);
		model.addAttribute("makerList", makerList);
		
		return "warehousing_manage/warehousing_order";
	}
	
	@ResponseBody
	@RequestMapping(value = "calCtn.do")
	public Map<String, Object> getCalCtnInfo(@RequestParam Map<String, Object> productMap) throws Exception {

		String productIdx = (String) productMap.get("productIdx");
		String size = (String) productMap.get("size");
		String thickness = (String) productMap.get("thickness");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("productIdx", productIdx);
		map.put("size", size);
		map.put("thickness", thickness);
		Map<String,Object> infoMap = warehousingService.getCalCtnInfo(map);
		System.out.println(map);
		System.out.println("---=====");
		return infoMap;

	}
	

}

