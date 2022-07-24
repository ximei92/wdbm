
package com.springbook.client.controller;

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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;
import org.yaml.snakeyaml.events.MappingEndEvent;

import com.springbook.client.service.ClientService;
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
import com.springbook.client.vo.ClientVO;
//import worldBmOther.main.service.vo.LoginVO;
//import worldBmOther.main.service.vo.OrderVO;
import com.springbook.client.vo.OrderVO;
import com.springbook.vo.JusoVO;
import ch.qos.logback.core.net.SyslogOutputStream;


@Controller
public class ClientController {
	@Autowired
	private EtcService etcService;
	@Autowired
	private FileService fileService;

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired(required = false)
	private PasswordEncoder encoder;

	
	//재고조회 이동
	@GetMapping("/goOrderManage.do")    
	public String goOrderManage(Model model) {
		List<JusoVO> sidoList = null;
		try {
			sidoList = commonService.getSido();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ProductVO> productNameList = etcService.getProductNameListGroup();

		model.addAttribute("productList", productNameList);
		model.addAttribute("sidoList", sidoList);
		
		return "client/order_manage";
	}

	//주문현황 이동
	@GetMapping("/goOrderStatus.do")    
	public String goOrderStatus(Model model) {
		List<JusoVO> sidoList = null;
		try {
			sidoList = commonService.getSido();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ProductVO> productNameList = etcService.getProductNameListGroup();

		model.addAttribute("productList", productNameList);
		model.addAttribute("sidoList", sidoList);
		
		return "client/order_status";
	}
	
	//발주변경조회 이동
	@GetMapping("/goOrderStatusView.do")    
	public String goOrderStatusView(Model model) {
		List<JusoVO> sidoList = null;
		try {
			sidoList = commonService.getSido();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ProductVO> productNameList = etcService.getProductNameListGroup();

		model.addAttribute("productList", productNameList);
		model.addAttribute("sidoList", sidoList);
		
		return "client/order_status_view";
	}
	/**
	 * 창고데이터 가져오기
	 * @param Map<String, Object> commandMap
	 * @return ModelAndView
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getSearchResultAjax.do")
	public List<ClientVO> getSearchResultAjax(@RequestParam Map<String, Object> commandMap) throws Exception {
    	
    	String dueDt = (String) commandMap.get("dueDt");
    	String sido = (String) commandMap.get("sido");
    	String sigungu = (String) commandMap.get("sigungu");
    	String eubmyeondong = (String) commandMap.get("eubmyeondong");
    	String carYn = (String) commandMap.get("carYn");
    	String pl = (String) commandMap.get("pl");
    	
    	String[] productList = pl.split("\\@");
    	
    	System.out.println("dueDt : " + dueDt);
    	System.out.println("sido : " + sido);
    	System.out.println("sigungu : " + sigungu);
    	System.out.println("eubmyeondong : " + eubmyeondong);
    	System.out.println("carYn : " + carYn);
    	System.out.println("pl : " + pl);
    	
    	List<ClientVO> whList = clientService.getWarehouse();
    	System.out.println(whList);
    	System.out.println("촹고릐스트!");
    	String pk = "";
    	String ps = "";
    	String pt = "";
    	String pqt = "";
    	String productQuery = "";
    	int wCnt = 0;
    	
    	//창고별 검색 쿼리 생성
    	for(ClientVO vo : whList) {
    		for(int i=0;i<productList.length;i++) {
        		System.out.println(i + "=" + productList[i]);
        		
        		String[] product = productList[i].split("\\|");
        		System.out.println("pk : " + product[0]);
        		System.out.println("ps : " + product[1]);
        		System.out.println("pt : " + product[2]);
        		System.out.println("ptt : " + product[3]);
        		if(i == 0) {
        			productQuery += "SELECT '" + vo.getWarehouseIdx() + "' AS WAREHOUSE_IDX, '" + product[0] + "' AS PRODUCT_IDX, '" + product[2] + "' AS THICKNESS_IDX, '" + product[1] + "' AS SIZE_IDX, " + product[3] + " AS ORDER_QT ";
        		}else {
        			productQuery += "UNION SELECT '" + vo.getWarehouseIdx() + "' AS WAREHOUSE_IDX, '" + product[0] + "' AS PRODUCT_IDX, '" + product[2] + "' AS THICKNESS_IDX, '" + product[1] + "' AS SIZE_IDX, " + product[3] + " AS ORDER_QT ";
        		}
        		//System.out.println("SELECT '" + product[0] + "' AS PRODUCT_IDX, '" + product[2] + "' AS THICKNESS_IDX, '" + product[1] + "' AS SIZE_IDX, " + product[3] + " AS ORDER_QT");
        	}
    		
    		wCnt ++;
    		
    		if(whList.size() != wCnt) {
    			productQuery += "UNION ";
    		}
    		
    	}
    	
    	System.out.println("productQuery : " + productQuery);
    	
    	ClientVO clientVO = new ClientVO();
    	clientVO.setDueDt(dueDt);
    	clientVO.setProductQuery(productQuery);
    	clientVO.setSidoNm(sido);
    	clientVO.setSigunguNm(sigungu);
    	clientVO.setEubmyeondongNm(eubmyeondong);
    	//검색결과 가져오기
    	List<ClientVO> resultList = clientService.getSearchResult(clientVO);
    	
    	int costCnt = 0;
    	//운반비 계산 추가
    	for(ClientVO vo : resultList) {
    		clientVO.setWarehouseIdx(vo.getWarehouseIdx());
    		clientVO.setTon(vo.getTon2());
    		
    		int feeCnt = clientService.getFeeCnt(clientVO);
        	String fee = "";
        	if(feeCnt == 0) {
        		clientVO.setEubmyeondongNm("");
        		feeCnt = clientService.getFeeCnt(clientVO);
        		if(feeCnt == 0) {
        			clientVO.setSigunguNm("");
        			feeCnt = clientService.getFeeCnt(clientVO);
        			if(feeCnt == 0) {
        				fee = "문의하기";
        			}else {
        				fee = clientService.getFee(clientVO);
        			}
        		}else {
        			fee = clientService.getFee(clientVO);
        		}
        	}else {
        		fee = clientService.getFee(clientVO);
        	}
        	vo.setCost(fee);
        	resultList.set(costCnt, vo);
    		//System.out.println(vo.getWAREHOUSE_IDX());
    		//System.out.println(vo.getTON2());
    		costCnt ++;
    	}

		//modelAndView.addObject("stock", stock);
		//modelAndView.addObject("fee", fee);
		//modelAndView.addObject("hb", String.format("%.2f",productSizeH*Integer.parseInt(pqt)));
		
		return resultList;
	}
	
	@RequestMapping(value="/order.do")
	public String order(
			HttpServletRequest req, HttpServletResponse res, HttpSession session, ModelMap model, @RequestParam Map<String, Object> commandMap
			) throws Exception {
		
//		LoginVO loginVO = (LoginVO)req.getSession().getAttribute("loginVO");
		
		String warehouseIdx =	(String) commandMap.get("warehouseIdx");
		String dueDt =	(String) commandMap.get("dueDt");
		String arrivalTime = (String) commandMap.get("arrivalTime");
		String ampm = (String) commandMap.get("arrivalTime");
		String hour = (String) commandMap.get("hour");
		String minutes = (String) commandMap.get("minutes");
		String sido = (String) commandMap.get("sido");
    	String sigungu = (String) commandMap.get("sigungu");
    	String eubmyeondong = (String) commandMap.get("eubmyeondong");
    	String jusoDetail = (String) commandMap.get("jusoDetail");
    	String carYn = (String) commandMap.get("carYn");
		String pl =	(String) commandMap.get("pl");
		
		String arrivalTimeNm = "";
		if(arrivalTime != "9999") {
			String hh = arrivalTime.substring(0,2);
			String mm = arrivalTime.substring(2,4);
			if(Integer.parseInt(hh) > 12) {
				arrivalTimeNm += "오후 " + "0" + (Integer.parseInt(hh) - 12) + ":" + mm;
			}else {
				arrivalTimeNm += "오전 " + hh + ":" + mm;
			}
			
		}
		System.out.println("warehouseIdx : " + warehouseIdx);
		System.out.println("dueDt : " + dueDt);
		System.out.println("arrivalTime : " + arrivalTime);
    	System.out.println("sido : " + sido);
    	System.out.println("sigungu : " + sigungu);
    	System.out.println("eubmyeondong : " + eubmyeondong);
    	System.out.println("jusoDetail : " + jusoDetail);
    	System.out.println("carYn : " + carYn);
    	System.out.println("pl : " + pl);
    	
    	OrderVO orderVO = new OrderVO();
    	orderVO.setWarehouseIdx(warehouseIdx);
    	orderVO.setDueDt(dueDt);
    	orderVO.setDueArrivalTime(arrivalTime);
    	orderVO.setDueArrivalTimeNm(arrivalTimeNm);
    	orderVO.setDueAmpm(ampm);
    	orderVO.setDueHh(hour);
    	orderVO.setDueMm(minutes);
    	orderVO.setSido(sido);
    	orderVO.setSigungu(sigungu);
    	orderVO.setEubmyeondong(eubmyeondong);
    	orderVO.setAddressDetail(jusoDetail);
    	orderVO.setCarYn(carYn);
    	orderVO.setPl(pl);
    	
    	String[] productList = pl.split("\\@");
    	String pk = "";
    	String ps = "";
    	String pt = "";
    	String pqt = "";
    	String productQuery = "";
    	
		for(int i=0;i<productList.length;i++) {
    		System.out.println(i + "=" + productList[i]);
    		
    		String[] product = productList[i].split("\\|");
    		System.out.println("pk : " + product[0]);
    		System.out.println("ps : " + product[1]);
    		System.out.println("pt : " + product[2]);
    		System.out.println("ptt : " + product[3]);
    		if(i == 0) {
    			productQuery += "SELECT '" + warehouseIdx + "' AS WAREHOUSE_IDX, '" + product[0] + "' AS PRODUCT_IDX, '" + product[2] + "' AS THICKNESS_IDX, '" + product[1] + "' AS SIZE_IDX, " + product[3] + " AS ORDER_QT ";
    		}else {
    			productQuery += "UNION SELECT '" + warehouseIdx + "' AS WAREHOUSE_IDX, '" + product[0] + "' AS PRODUCT_IDX, '" + product[2] + "' AS THICKNESS_IDX, '" + product[1] + "' AS SIZE_IDX, " + product[3] + " AS ORDER_QT ";
    		}
    		//System.out.println("SELECT '" + product[0] + "' AS PRODUCT_IDX, '" + product[2] + "' AS THICKNESS_IDX, '" + product[1] + "' AS SIZE_IDX, " + product[3] + " AS ORDER_QT");
    	}	
    	
    	System.out.println("productQuery : " + productQuery);
    	
    	ClientVO clientVO = new ClientVO();
    	clientVO.setProductQuery(productQuery);
    	clientVO.setSidoNm(sido);
    	clientVO.setSigunguNm(sigungu);
    	clientVO.setEubmyeondongNm(eubmyeondong);
    	//톤정보 가져오기
    	ClientVO tonInfo = clientService.getTonInfo(clientVO);
    	
    	clientVO.setWarehouseIdx(warehouseIdx);
		clientVO.setTon(tonInfo.getTon2());
		
		int feeCnt = clientService.getFeeCnt(clientVO);
    	String fee = "";
    	if(feeCnt == 0) {
    		clientVO.setEubmyeondongNm("");
    		feeCnt = clientService.getFeeCnt(clientVO);
    		if(feeCnt == 0) {
    			clientVO.setSigunguNm("");
    			feeCnt = clientService.getFeeCnt(clientVO);
    			if(feeCnt == 0) {
    				fee = "문의하기";
    			}else {
    				fee = clientService.getFee(clientVO);
    			}
    		}else {
    			fee = clientService.getFee(clientVO);
    		}
    	}else {
    		fee = clientService.getFee(clientVO);
    	}
    	
    	//제품가격 가져오기
    	List<ClientVO> pPrice = clientService.getPrice(clientVO);
    	
    	//공급가액 가져오기
    	String totalPrice = clientService.getTotalPrice(clientVO);
    	
    	//창고명 가져오기
    	String warehouseNm = clientService.getWarehouseNm(warehouseIdx);
    	
    	//시도가져오기
    	List<JusoVO> sidoList = commonService.getSido();
    	
    	JusoVO jusoVO = new JusoVO();
    	jusoVO.setSidoNm(sido);
    	jusoVO.setSigunguNm(sigungu);
    	
    	//시군구가져오기
    	List<JusoVO> sigunguList = commonService.getSigungu(jusoVO);
    	//읍면동 가져오기
    	List<JusoVO> eubmyeondongList = commonService.getEubmyeondong(jusoVO);
    	
    	model.addAttribute("orderVO", orderVO);
    	model.addAttribute("pPrice", pPrice);
    	model.addAttribute("totalPrice", totalPrice);
    	model.addAttribute("fee", fee);
    	model.addAttribute("warehouseNm", warehouseNm);
    	model.addAttribute("sidoList", sidoList);
    	model.addAttribute("sigunguList", sigunguList);
    	model.addAttribute("eubmyeondongList", eubmyeondongList);
		model.addAttribute("menuPath", "/client/101_order");
		
		return "client/order_manage_view";
	}	

}

