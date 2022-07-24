
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class WarehousingController {
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
	
	@GetMapping("/orderlist.do")    
	public String orderList(Model model,String pagenum, String contentnum, String startDt, String endDt, String productCd, String port, String maker, String warehouseIdx) {
		List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);
		List<Map<String,Object>> productList = warehousingService.getProductCd();
		List<MemberVO> makerList = warehousingService.getMakerList();
		List<String> productCdList = new ArrayList<String>();
//		List<Map<String,Object>> orderList = warehousingService.getOrderList();
//		
		for(int i=0; i<productList.size(); i++){
			String cd = productList.get(i).get("THICKNESS_NM").toString()+productList.get(i).get("SIZE_NM").toString()+"-"+productList.get(i).get("PRODUCT_CD").toString();
			productCdList.add(cd);
		}
		
		//페이징
		Page pagemaker = new Page();
		int cpagenum;
		int ccontentnum;
		
		if(pagenum == null || pagenum.length() == 0){
			cpagenum = 1;
		} else {
	        cpagenum = Integer.parseInt(pagenum);		
		}

		if(contentnum == null || contentnum.length() == 0){
			ccontentnum = 10;
		} else {
	        ccontentnum = Integer.parseInt(contentnum);	
		}

		pagemaker.setTotalcount(warehousingService.getOrderListCount()); // mapper 전체 게시글 개수를 지정한다
        pagemaker.setPagenum(cpagenum-1);   // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서 사용할수 있다
        pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
        pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
        pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다.

        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
        pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지 블록번호로 정한다.
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
        if(ccontentnum == 10){//선택 게시글 수
        	List<Map<String,Object>> orderList = warehousingService.getOrderList(pagemaker.getPagenum()*10,pagemaker.getContentnum());
            model.addAttribute("orderList", orderList);
        }else if(ccontentnum == 20){
        	List<Map<String,Object>> orderList = warehousingService.getOrderList(pagemaker.getPagenum()*20,pagemaker.getContentnum());
        	 model.addAttribute("orderList", orderList);
        }else if(ccontentnum ==30){
        	List<Map<String,Object>> orderList = warehousingService.getOrderList(pagemaker.getPagenum()*30, pagemaker.getContentnum());
        	 model.addAttribute("orderList", orderList);
        }
        

        model.addAttribute("page",pagemaker);	
		model.addAttribute("warehouse", warehouse);
		model.addAttribute("productCdList", productCdList);
		model.addAttribute("makerList", makerList);
		
		return "warehousing_manage/order_list";
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
		return infoMap;

	}
	
	@ResponseBody
	@RequestMapping(value = "getProductCd.do")
	public Map<String, Object> getProductCd(@RequestParam Map<String, Object> productMap) throws Exception {
		String productIdx = (String) productMap.get("productIdx");
		String size = (String) productMap.get("size");
		String thickness = (String) productMap.get("thickness");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("productIdx", productIdx);
		map.put("size", size);
		map.put("thickness", thickness);
		Map<String,Object> infoMap = warehousingService.getProductCdInfo(map);
		return infoMap;

	}
	
	//오더 발주 취소
	@ResponseBody
	@RequestMapping(value = "cancelOrderCtn.do")
	public int cancelOrderCtn(@RequestParam String orderIdx) throws Exception {

		int result = warehousingService.cancelOrderCtn(orderIdx);
		return result;

	}
	
	@PostMapping(value= "/selectDirectOrderCompany.do")
	@ResponseBody
	public Map<String,Object> selectDirectOrderCompany(String pagenum, String keyword) {

		if(keyword == null){
			keyword = "";
		}
		String contentnum = "10";
		Page pagemaker = new Page();
		int cpagenum;
		int ccontentnum;
		
		if(pagenum == null || pagenum.length() == 0){
			cpagenum = 1;
		} else {
	        cpagenum = Integer.parseInt(pagenum);		
		}

		if(contentnum == null || contentnum.length() == 0){
			ccontentnum = 5;
		} else {
	        ccontentnum = Integer.parseInt(contentnum);	
		}
		
		pagemaker.setTotalcount(etcService.selectCompanyCount(keyword)); // mapper 전체 게시글 개수를 지정한다
        pagemaker.setPagenum(cpagenum-1);   // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서 사용할수 있다
        pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
        pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
        pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다.

        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
        pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지 블록번호로 정한다.
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        

        //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
        List<MemberVO> list = etcService.getSelectCompany(pagemaker.getPagenum()*5,pagemaker.getContentnum(),keyword);

		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("page", pagemaker);
		map.put("list", list);

		return map;
	}
	
	@GetMapping("/orderListDetailDirect.do")    
	public String orderListDetailDirect(Model model,String productIdx, String thickness, String size, String maker, String warehouseIdx, String stock, String etd,String shipperId, String port,String ctn) {
		//시도가져오기
		List<JusoVO> sidoList = null;
		try {
			sidoList = commonService.getSido();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("sidoList", sidoList);
		
		if(productIdx.contains("//")){
			//ctn 2개
			String productIdx1 = productIdx.split("//")[0];
			String productIdx2 = productIdx.split("//")[1];
			
			String thickness1 = thickness.split("//")[0];
			String thickness2 = thickness.split("//")[1];
			
			String size1 = size.split("//")[0];
			String size2 = size.split("//")[1];
			
			Map<String,Object> productInfo = warehousingService.getProductInfo(productIdx1,thickness1,size1);
			Map<String,Object> productInfo1 = warehousingService.getProductInfo(productIdx2,thickness2,size2);
			WarehouseVO warehouseInfo = warehousingService.getWarehouseInfo(warehouseIdx);
			MemberVO memberInfo = warehousingService.getMemberInfo(shipperId);
			List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);

			model.addAttribute("productInfo", productInfo);
			model.addAttribute("productInfo1", productInfo);
			model.addAttribute("stock", stock.split("//")[0]);
			model.addAttribute("stock1", stock.split("//")[1]);
			model.addAttribute("ctn", ctn.split("//")[0]);
			model.addAttribute("ctn1", ctn.split("//")[1]);
			model.addAttribute("warehouse", warehouse);
			model.addAttribute("warehouseInfo", warehouseInfo);
			model.addAttribute("memberInfo", memberInfo);
			model.addAttribute("etd", etd);
			model.addAttribute("maker", maker);
			model.addAttribute("port", port);
		} else {
			//ctn1개
			Map<String,Object> productInfo = warehousingService.getProductInfo(productIdx,thickness,size);
			WarehouseVO warehouseInfo = warehousingService.getWarehouseInfo(warehouseIdx);
			MemberVO memberInfo = warehousingService.getMemberInfo(shipperId);
			List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);
			
			model.addAttribute("productInfo", productInfo);
			model.addAttribute("warehouse", warehouse);
			model.addAttribute("warehouseInfo", warehouseInfo);
			model.addAttribute("memberInfo", memberInfo);
			model.addAttribute("stock", stock);
			model.addAttribute("etd", etd);
			model.addAttribute("maker", maker);
			model.addAttribute("port", port);
			model.addAttribute("ctn", ctn);

		}
		
		return "warehousing_manage/order_list_detail_direct";
	}

	@GetMapping("/orderListDetail.do")    
	public String orderListDetail(Model model,String productIdx, String thickness, String size,
			String maker, String warehouseIdx, String stock, String etd,String shipperId, String port,String ctn) {

		if(productIdx.contains("//")){
			//ctn 2개
			String productIdx1 = productIdx.split("//")[0];
			String productIdx2 = productIdx.split("//")[1];
			
			String thickness1 = thickness.split("//")[0];
			String thickness2 = thickness.split("//")[1];
			
			String size1 = size.split("//")[0];
			String size2 = size.split("//")[1];
			
			Map<String,Object> productInfo = warehousingService.getProductInfo(productIdx1,thickness1,size1);
			Map<String,Object> productInfo1 = warehousingService.getProductInfo(productIdx2,thickness2,size2);
			WarehouseVO warehouseInfo = warehousingService.getWarehouseInfo(warehouseIdx);
			MemberVO memberInfo = warehousingService.getMemberInfo(shipperId);
			List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);

			model.addAttribute("productInfo", productInfo);
			model.addAttribute("productInfo1", productInfo);
			model.addAttribute("stock", stock.split("//")[0]);
			model.addAttribute("stock1", stock.split("//")[1]);
			model.addAttribute("ctn", ctn.split("//")[0]);
			model.addAttribute("ctn1", ctn.split("//")[1]);
			model.addAttribute("warehouse", warehouse);
			model.addAttribute("warehouseInfo", warehouseInfo);
			model.addAttribute("memberInfo", memberInfo);
			model.addAttribute("etd", etd);
			model.addAttribute("maker", maker);
			model.addAttribute("port", port);
		} else {
			//ctn1개
			Map<String,Object> productInfo = warehousingService.getProductInfo(productIdx,thickness,size);
			WarehouseVO warehouseInfo = warehousingService.getWarehouseInfo(warehouseIdx);
			MemberVO memberInfo = warehousingService.getMemberInfo(shipperId);
			List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);
			
			model.addAttribute("productInfo", productInfo);
			model.addAttribute("warehouse", warehouse);
			model.addAttribute("warehouseInfo", warehouseInfo);
			model.addAttribute("memberInfo", memberInfo);
			model.addAttribute("stock", stock);
			model.addAttribute("etd", etd);
			model.addAttribute("maker", maker);
			model.addAttribute("port", port);
			model.addAttribute("ctn", ctn);

		}
		
		return "warehousing_manage/order_list_detail";
	}

	//직송아닌경우 수정 페이지
	@GetMapping("/orderEditDetail.do")    
	public String orderListDetail(Model model,String idx, String warehouseIdx) {
		Map<String,Object> orderInfo = warehousingService.getOrderInfo(idx);
		model.addAttribute("orderInfo", orderInfo);
		
		if(orderInfo.get("PRODUCT_IDX").toString().contains("//")){
			//ctn 2개
			String productIdx1 = orderInfo.get("PRODUCT_IDX").toString().split("//")[0];
			String productIdx2 = orderInfo.get("PRODUCT_IDX").toString().split("//")[1];
			
			String thickness1 = orderInfo.get("THICKNESS_IDX").toString().split("//")[0];
			String thickness2 = orderInfo.get("THICKNESS_IDX").toString().split("//")[1];
			
			String size1 = orderInfo.get("SIZE_IDX").toString().split("//")[0];
			String size2 = orderInfo.get("SIZE_IDX").toString().split("//")[1];
			//날짜변환

			Map<String,Object> productInfo = warehousingService.getProductInfo(productIdx1,thickness1,size1);
			Map<String,Object> productInfo1 = warehousingService.getProductInfo(productIdx2,thickness2,size2);
			WarehouseVO warehouseInfo = null;
			if(warehouseIdx != null && warehouseIdx != ""){
				warehouseInfo = warehousingService.getWarehouseInfo(warehouseIdx);
			} else {
				warehouseInfo = warehousingService.getWarehouseInfo(orderInfo.get("WAREHOUSE_IDX").toString());
			}
			
			MemberVO memberInfo = warehousingService.getMemberInfo(orderInfo.get("MAKER_ID").toString());
			List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);

			model.addAttribute("productInfo", productInfo);
			model.addAttribute("productInfo1", productInfo);
			model.addAttribute("stock", orderInfo.get("STOCK").toString().split("//")[0]);
			model.addAttribute("stock1", orderInfo.get("STOCK").toString().split("//")[1]);
			model.addAttribute("ctn", 1);
			model.addAttribute("ctn1", 2);
			model.addAttribute("warehouse", warehouse);
			model.addAttribute("warehouseInfo", warehouseInfo);
			model.addAttribute("memberInfo", memberInfo);
			model.addAttribute("etdDateTime", orderInfo.get("ETD").toString());
			model.addAttribute("maker", orderInfo.get("MAKER").toString());
			model.addAttribute("port", orderInfo.get("DP").toString()+"//"+orderInfo.get("AP").toString());
		} else {
			//ctn1개
			String productIdx = orderInfo.get("PRODUCT_IDX").toString();
			String thickness = orderInfo.get("THICKNESS_IDX").toString();			
			String size = orderInfo.get("SIZE_IDX").toString();
			
			Map<String,Object> productInfo = warehousingService.getProductInfo(productIdx,thickness,size);
			WarehouseVO warehouseInfo = null;
			if(warehouseIdx != null && warehouseIdx != ""){
				warehouseInfo = warehousingService.getWarehouseInfo(warehouseIdx);
			} else {
				warehouseInfo = warehousingService.getWarehouseInfo(orderInfo.get("WAREHOUSE_IDX").toString());
			}
			MemberVO memberInfo = warehousingService.getMemberInfo(orderInfo.get("MAKER_ID").toString());
			List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);
			
			model.addAttribute("productInfo", productInfo);
			model.addAttribute("warehouse", warehouse);
			model.addAttribute("warehouseInfo", warehouseInfo);
			model.addAttribute("memberInfo", memberInfo);
			model.addAttribute("stock", orderInfo.get("STOCK").toString());
			model.addAttribute("etdDateTime", orderInfo.get("ETD").toString());
			model.addAttribute("maker", orderInfo.get("MAKER").toString());
			model.addAttribute("port", orderInfo.get("DP").toString()+"//"+orderInfo.get("AP").toString());
			model.addAttribute("ctn", 1);

		}

		return "warehousing_manage/order_list_detail";
	}
	
	//직송인경우 수정페이지
	@GetMapping("/orderEditDetailDirect.do")    
	public String orderEditDetailDirect(Model model,String idx) {
		Map<String,Object> orderInfo = warehousingService.getOrderInfo(idx);
		model.addAttribute("orderInfo", orderInfo);
		//시도가져오기
		List<JusoVO> sidoList = null;
		try {
			sidoList = commonService.getSido();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("sidoList", sidoList);
		
		if(orderInfo.get("PRODUCT_IDX").toString().contains("//")){
			//ctn 2개
			String productIdx1 = orderInfo.get("PRODUCT_IDX").toString().split("//")[0];
			String productIdx2 = orderInfo.get("PRODUCT_IDX").toString().split("//")[1];
			
			String thickness1 = orderInfo.get("THICKNESS_IDX").toString().split("//")[0];
			String thickness2 = orderInfo.get("THICKNESS_IDX").toString().split("//")[1];
			
			String size1 = orderInfo.get("SIZE_IDX").toString().split("//")[0];
			String size2 = orderInfo.get("SIZE_IDX").toString().split("//")[1];
			
			Map<String,Object> productInfo = warehousingService.getProductInfo(productIdx1,thickness1,size1);
			Map<String,Object> productInfo1 = warehousingService.getProductInfo(productIdx2,thickness2,size2);
			WarehouseVO warehouseInfo = warehousingService.getWarehouseInfo(orderInfo.get("WAREHOUSE_IDX").toString());
			MemberVO memberInfo = warehousingService.getMemberInfo(orderInfo.get("MAKER_ID").toString());
			List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);

			model.addAttribute("productInfo", productInfo);
			model.addAttribute("productInfo1", productInfo);
			model.addAttribute("stock", orderInfo.get("STOCK").toString().split("//")[0]);
			model.addAttribute("stock1", orderInfo.get("STOCK").toString().split("//")[1]);
			model.addAttribute("ctn", 1);
			model.addAttribute("ctn1", 2);
			model.addAttribute("warehouse", warehouse);
			model.addAttribute("warehouseInfo", warehouseInfo);
			model.addAttribute("memberInfo", memberInfo);
			model.addAttribute("maker", orderInfo.get("MAKER").toString());
			model.addAttribute("port", orderInfo.get("DP").toString()+"//"+orderInfo.get("AP").toString());
		} else {
			//ctn1개
			String productIdx = orderInfo.get("PRODUCT_IDX").toString();
			String thickness = orderInfo.get("THICKNESS_IDX").toString();			
			String size = orderInfo.get("SIZE_IDX").toString();
			
			Map<String,Object> productInfo = warehousingService.getProductInfo(productIdx,thickness,size);
			WarehouseVO warehouseInfo = warehousingService.getWarehouseInfo(orderInfo.get("WAREHOUSE_IDX").toString());
			MemberVO memberInfo = warehousingService.getMemberInfo(orderInfo.get("MAKER_ID").toString());
			List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);
			
			model.addAttribute("productInfo", productInfo);
			model.addAttribute("warehouse", warehouse);
			model.addAttribute("warehouseInfo", warehouseInfo);
			model.addAttribute("memberInfo", memberInfo);
			model.addAttribute("stock", orderInfo.get("STOCK").toString());
			model.addAttribute("maker", orderInfo.get("MAKER").toString());
			model.addAttribute("port", orderInfo.get("DP").toString()+"//"+orderInfo.get("AP").toString());
			model.addAttribute("ctn", 1);

		}
		
		return "warehousing_manage/order_list_detail_direct";
	}
	
	@PostMapping("/insertOrderCtn.do")    
	@ResponseBody
	public int insertOrderCtn(Model model, @RequestParam Map<String, String>  map) {

		int result = 0 ;
		if(map.get("update").equals("1")){
			result = warehousingService.updateOrderCtn(map);
		} else if(map.get("update").equals("0")){
			result = warehousingService.insertOrderCtn(map);
		}
		
		return result;
	}
	
	@PostMapping("/insertOrderMultiCtn.do")    
	@ResponseBody
	public int insertOrderMultiCtn(Model model, @RequestParam Map<String, Object>  parameters) {
	      String json = parameters.get("paramList").toString();
	      ObjectMapper mapper = new ObjectMapper();
	      List<Map<String, Object>> paramList = null;
		try {
			paramList = mapper.readValue(json, new TypeReference<ArrayList<Map<String, Object>>>(){});
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      
		System.out.println(paramList);

		int result = 0 ;
		result = warehousingService.insertOrderMultiCtn(paramList);
//		if(map.get("update").equals("1")){
//			result = warehousingService.updateOrderCtn(map);
//		} else if(map.get("update").equals("0")){
//			
//		}
		return result;
	}
	
	@PostMapping("/loadClientInfo.do")    
	@ResponseBody
	public MemberVO loadClientInfo(Model model, @RequestParam String id) {

		MemberVO result = warehousingService.getMemberInfo(id);

		return result;
	}
	
}

