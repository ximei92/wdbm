
package com.springbook.warehousing.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
import org.springframework.http.HttpRequest;
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
import org.yaml.snakeyaml.events.MappingEndEvent;

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
import com.springbook.service.CommonService;
import com.springbook.service.FileService;
import com.springbook.vo.FileVO;

import com.springbook.warehousing.service.WarehousingService;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.springbook.vo.JusoVO;
import ch.qos.logback.core.net.SyslogOutputStream;


@Controller
public class ShipmentController {
	@Autowired
	private EtcService etcService;
	@Autowired
	private FileService fileService;

	@Autowired
	private WarehousingService warehousingService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired(required = false)
	private PasswordEncoder encoder;

	//선적현황 이동
	@GetMapping("/goShipmentStatus.do")    
	public String goShipmentStatus(Model model) {

		
		return "warehousing_manage/shipment_status";
	}
	
	//입고현황이동
	@GetMapping("/goCustomsStatus.do")    
	public String goCustomStatus(Model model) {

		
		return "warehousing_manage/customs_status";
	}	
}

