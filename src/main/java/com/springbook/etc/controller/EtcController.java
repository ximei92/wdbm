package com.springbook.etc.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
public class EtcController {
	@Autowired
	private EtcService etcService;
	@Autowired
	private FileService fileService;
	
	@Autowired(required = false)
	private PasswordEncoder encoder;
	
	//문의하기 등록
	@PostMapping("/tryAsk.do")
	@ResponseBody
	public int tryAsk(JoinAskVO vo) {
		int result = etcService.insertAsk(vo);
		return result;
	}

	@GetMapping("/inquiryList.do")    
	public String inquiryList(Model model,String pagenum, String contentnum, String keyword) {
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

		pagemaker.setTotalcount(etcService.inquiryListCount(keyword)); // mapper 전체 게시글 개수를 지정한다
        pagemaker.setPagenum(cpagenum-1);   // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서 사용할수 있다
        pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
        pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
        pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다.

        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
        pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지 블록번호로 정한다.
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
        if(ccontentnum == 10){//선택 게시글 수
        	List<JoinAskVO> list = etcService.getInquiryList(pagemaker.getPagenum()*10,pagemaker.getContentnum(),keyword);
        	 System.out.println(list);
            model.addAttribute("list", list);
        }else if(ccontentnum == 20){
        	List<JoinAskVO> list = etcService.getInquiryList(pagemaker.getPagenum()*20,pagemaker.getContentnum(), keyword);
        	 model.addAttribute("list", list);
        }else if(ccontentnum ==30){
        	List<JoinAskVO> list = etcService.getInquiryList(pagemaker.getPagenum()*30, pagemaker.getContentnum(), keyword);
        	 model.addAttribute("list", list);
        }
        
         model.addAttribute("keyword", keyword);
         model.addAttribute("page",pagemaker);
         
		return "etc_manage/new_sub_inquiry";
	}
	
	@GetMapping("/inquiryDetail.do")    
	public String getInquiryDetail(Model model, String idx) {
		
		etcService.checkNew(idx);
		List<JoinAskVO> list = etcService.getInquiryDetail(idx);
        model.addAttribute("list", list);
        
        if(list.get(0).getFileId().length() >0){
	        FileVO file = fileService.getFileData(list.get(0).getFileId());
			model.addAttribute("fileInfo", file);
        }

		return "etc_manage/new_sub_inquiry_view";
	}	
		
	@PostMapping("/updateInquiry.do")
	@ResponseBody
	public int updateInquiry(String info, String idx) {
		System.out.println(info);
		System.out.println(idx);
		int result = etcService.updateInquiry(info, idx);

		System.out.println(result);
		return result;
	}
	
	//제품명 조회
	@GetMapping("/productRegists.do")    
	public String productRegists(Model model,String pagenum, String contentnum, String type, String keyword) {
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

		pagemaker.setTotalcount(etcService.productListCount(type,keyword)); // mapper 전체 게시글 개수를 지정한다
        pagemaker.setPagenum(cpagenum-1);   // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서 사용할수 있다
        pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
        pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
        pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다.

        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
        pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지 블록번호로 정한다.
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
        if(ccontentnum == 10){//선택 게시글 수
        	List<ProductVO> list = etcService.getProductList(pagemaker.getPagenum()*10,pagemaker.getContentnum(),type, keyword);
        	 System.out.println(list);
            model.addAttribute("list", list);
        }else if(ccontentnum == 20){
        	List<ProductVO> list = etcService.getProductList(pagemaker.getPagenum()*20,pagemaker.getContentnum(),type, keyword);
        	 model.addAttribute("list", list);
        }else if(ccontentnum ==30){
        	List<ProductVO> list = etcService.getProductList(pagemaker.getPagenum()*30, pagemaker.getContentnum(),type, keyword);
        	 model.addAttribute("list", list);
        }
        
         model.addAttribute("keyword", keyword);
         model.addAttribute("page", pagemaker);
         model.addAttribute("type",type);
         
		return "etc_manage/product_regist";
	}
	
	//제품명 등록
	@PostMapping("/addProductType.do")
	@ResponseBody
	public int addProductType(ProductVO vo) {
		System.out.println(vo);
		int result = etcService.addProductType(vo);
		return result;
	}
	
	//제품명 업데이트
	@PostMapping("/updateProductType.do")
	@ResponseBody
	public int updateProductType(ProductVO vo) {
		System.out.println(vo);
		int result = etcService.updateProductType(vo);
		return result;
	}	
	
	//제품명 삭제
	@PostMapping("/typeDelete.do")
	@ResponseBody
	public int typeDelete(HttpServletRequest request) {
		String[] idxList = request.getParameterValues("idxList");
		System.out.println(idxList.toString());
		int result = 0;
		for(int i=0; i<idxList.length; i++){
			System.out.println("idx"+idxList[i]);
			result += etcService.typeDelete(idxList[i]);
		}
		
		int returnVal = 0;
		if(result != idxList.length){
			returnVal = 1;
		}
		
		return returnVal;
	}	

	//두께등록관리 페이지
	@GetMapping("/productThickness.do")    
	public String thicknessList(Model model,String pagenum, String contentnum, String type, String keyword) {
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

		pagemaker.setTotalcount(etcService.thicknessListCount(type,keyword)); // mapper 전체 게시글 개수를 지정한다
        pagemaker.setPagenum(cpagenum-1);   // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서 사용할수 있다
        pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
        pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
        pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다.

        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
        pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지 블록번호로 정한다.
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
        if(ccontentnum == 10){//선택 게시글 수
        	List<ProductThicknessVO> list = etcService.getThicknessList(pagemaker.getPagenum()*10,pagemaker.getContentnum(),type, keyword);
        	 System.out.println(list);
            model.addAttribute("list", list);
        }else if(ccontentnum == 20){
        	List<ProductThicknessVO> list = etcService.getThicknessList(pagemaker.getPagenum()*20,pagemaker.getContentnum(),type, keyword);
        	 model.addAttribute("list", list);
        }else if(ccontentnum ==30){
        	List<ProductThicknessVO> list = etcService.getThicknessList(pagemaker.getPagenum()*30, pagemaker.getContentnum(),type, keyword);
        	 model.addAttribute("list", list);
        }
        
         model.addAttribute("keyword", keyword);
         model.addAttribute("page", pagemaker);
         model.addAttribute("type",type);
         
		return "etc_manage/product_thickness";
	}

	//두께 등록
	@PostMapping("/addProductThickness.do")
	@ResponseBody
	public int addProductThickness(ProductThicknessVO vo) {
		int result = etcService.addProductThickness(vo);
		return result;
	}
	
	//두께 업데이트
	@PostMapping("/updateProductThickness.do")
	@ResponseBody
	public int updateProductThickness(ProductThicknessVO vo) {
		int result = etcService.updateProductThickness(vo);
		return result;
	}
	
	//두께등록페이지 이동
	@GetMapping(value= "/goThicknessRegist.do")
	public String goThicknessRegist(Model model, String idx, String fileId) {
		List<ProductVO> productNameList = etcService.getProductNameList();		
		model.addAttribute("list", productNameList);
		
		if(idx != null){
			FileVO file = fileService.getFileData(fileId);
			model.addAttribute("fileInfo", file);
			List<ProductThicknessVO> info = etcService.getThicknessInfo(idx);
			model.addAttribute("info", info);
		}
		
		return "/etc_manage/thickness_regist";
	}
	
	//두께 삭제
	@PostMapping("/thicknessDelete.do")
	@ResponseBody
	public int thicknessDelete(HttpServletRequest request) {
		String[] idxList = request.getParameterValues("idxList");
		int result = 0;
		for(int i=0; i<idxList.length; i++){
			System.out.println("idx"+idxList[i]);
			result += etcService.thicknessDelete(idxList[i]);
		}
		
		int returnVal = 0;
		if(result != idxList.length){
			returnVal = 1;
		}
		
		return returnVal;
	}
	
	//사이즈관리 페이지
	@GetMapping("/productSize.do")    
	public String productSize(Model model,String pagenum, String contentnum, String type, String keyword) {
		System.out.println("사아아아아아아이이이이이즈즈즈즈");
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

		pagemaker.setTotalcount(etcService.sizeListCount(type,keyword)); // mapper 전체 게시글 개수를 지정한다
        pagemaker.setPagenum(cpagenum-1);   // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서 사용할수 있다
        pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
        pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
        pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다.

        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
        pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지 블록번호로 정한다.
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
        if(ccontentnum == 10){//선택 게시글 수
        	List<String> list = etcService.getSizeList(pagemaker.getPagenum()*10,pagemaker.getContentnum(),type, keyword);
            model.addAttribute("list", list);
        }else if(ccontentnum == 20){
        	List<String> list = etcService.getSizeList(pagemaker.getPagenum()*20,pagemaker.getContentnum(),type, keyword);
        	 model.addAttribute("list", list);
        }else if(ccontentnum ==30){
        	List<String> list = etcService.getSizeList(pagemaker.getPagenum()*30, pagemaker.getContentnum(),type, keyword);
        	 model.addAttribute("list", list);
        }
        
         model.addAttribute("keyword", keyword);
         model.addAttribute("page", pagemaker);
         model.addAttribute("type",type);
         
		return "etc_manage/product_size";
	}
	
	//사이즈등록페이지 이동
	@GetMapping(value= "/goSizeRegist.do")
	public String goSizeRegist(Model model) {
		List<ProductVO> productNameList = etcService.getProductNameList();		
		model.addAttribute("list", productNameList);
		return "/etc_manage/size_regist";
	}
	
	//사이즈 등록
	@PostMapping("/addProductSize.do")
	@ResponseBody
	public int addProductSize(ProductSizeVO vo) {
		int result = etcService.addProductSize(vo);
		return result;
	}
	
	//사이즈 삭제
	@PostMapping("/sizeDelete.do")
	@ResponseBody
	public int sizeDelete(HttpServletRequest request) {
		String[] idxList = request.getParameterValues("idxList");
		int result = 0;
		for(int i=0; i<idxList.length; i++){
			result += etcService.sizeDelete(idxList[i]);
		}
		
		int returnVal = 0;
		if(result != idxList.length){
			returnVal = 1;
		}
		
		return returnVal;
	}
	
	//사이즈관리 페이지
	@GetMapping("/productDetail.do")    
	public String productDetail(Model model,String pagenum, String contentnum, String type, String keyword) {
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

		pagemaker.setTotalcount(etcService.detailListCount(type,keyword)); // mapper 전체 게시글 개수를 지정한다
        pagemaker.setPagenum(cpagenum-1);   // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서 사용할수 있다
        pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
        pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
        pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다.

        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
        pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지 블록번호로 정한다.
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
        if(ccontentnum == 10){//선택 게시글 수
        	List<String> list = etcService.getDetailList(pagemaker.getPagenum()*10,pagemaker.getContentnum(),type, keyword);
        	 System.out.println(list);
            model.addAttribute("list", list);
        }else if(ccontentnum == 20){
        	List<String> list = etcService.getDetailList(pagemaker.getPagenum()*20,pagemaker.getContentnum(),type, keyword);
        	 model.addAttribute("list", list);
        }else if(ccontentnum ==30){
        	List<String> list = etcService.getDetailList(pagemaker.getPagenum()*30, pagemaker.getContentnum(),type, keyword);
        	 model.addAttribute("list", list);
        }
        
         model.addAttribute("keyword", keyword);
         model.addAttribute("page", pagemaker);
         model.addAttribute("type",type);
         
		return "etc_manage/product_detail";
	}
	
	//상세설정 등록페이지 이동
	@GetMapping(value= "/goDetailRegist.do")
	public String goDetailRegist(Model model, String idx) {
		List<ProductVO> productNameList = etcService.getProductNameList();		
		model.addAttribute("productList", productNameList);
		
		if(idx != null){
			List<com.springbook.etc.vo.ProductDetailVO> info = etcService.getDetailInfo(idx);
			model.addAttribute("info", info);
		}
		
		return "/etc_manage/detail_regist";
	}
	
	//상세설정 두꼐,사이즈 조회
	@PostMapping("/selectDetailThickness.do")
	@ResponseBody
	public Map<String, Object> selectDetailThickness(HttpServletRequest request) {
		String name = request.getParameter("name");
		
		List<Map> thicklist = etcService.selectDetailThickness(name);
		List<Map> sizelist = etcService.selectDetailSize(name);
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("thickness", thicklist);
		map.put("size", sizelist);
		
		return map;
	}
	
	//상세설정 등록
	@PostMapping("/addProductDetail.do")
	@ResponseBody
	public int addProductDetail(com.springbook.etc.vo.ProductDetailVO vo) {
		int result = etcService.addProductDetail(vo);
		return result;
	}

	//상세설정  수정
	@PostMapping("/updateProductDetail.do")
	@ResponseBody
	public int updateProductDetail(com.springbook.etc.vo.ProductDetailVO vo) {
		int result = etcService.updateProductDetail(vo);
		return result;
	}
	
	//상세설정 삭제
	@PostMapping("/detailDelete.do")
	@ResponseBody
	public int detailDelete(HttpServletRequest request) {
		String[] idxList = request.getParameterValues("idxList");
		int result = 0;
		for(int i=0; i<idxList.length; i++){
			result += etcService.detailDelete(idxList[i]);
		}
		
		int returnVal = 0;
		if(result != idxList.length){
			returnVal = 1;
		}
		
		return returnVal;
	}
	
	//제품명 조회
	@GetMapping("/warehouseManage.do")    
	public String warehouseManage(Model model,String pagenum, String contentnum) {
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

		pagemaker.setTotalcount(etcService.warehouseListCount()); // mapper 전체 게시글 개수를 지정한다
        pagemaker.setPagenum(cpagenum-1);   // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서 사용할수 있다
        pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
        pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
        pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다.

        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
        pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지 블록번호로 정한다.
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
        if(ccontentnum == 10){//선택 게시글 수
        	List<WarehouseVO> list = etcService.getWarehouseList(pagemaker.getPagenum()*10,pagemaker.getContentnum());
            model.addAttribute("list", list);
        }else if(ccontentnum == 20){
        	List<WarehouseVO> list = etcService.getWarehouseList(pagemaker.getPagenum()*20,pagemaker.getContentnum());
        	 model.addAttribute("list", list);
        }else if(ccontentnum ==30){
        	List<WarehouseVO> list = etcService.getWarehouseList(pagemaker.getPagenum()*30, pagemaker.getContentnum());
        	 model.addAttribute("list", list);
        }

         model.addAttribute("page", pagemaker);

		return "etc_manage/warehouse_manage";
	}
	
	//창고별 재고관리 이동
	@GetMapping("/addInventoryStock.do")    
	public String addInventoryStock(Model model) {
		List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);
		List<ProductVO> productNameList = etcService.getProductNameList();
		
		model.addAttribute("warehouse", warehouse);
		model.addAttribute("productList", productNameList);
		
		return "etc_manage/inventory_manage_view";
	}
	
	//창고별 재고관리 등록
	@PostMapping("/updateInventoryStock.do")
	@ResponseBody
	public int updateInventoryStock(WarehouseInventoryVO vo, HttpServletRequest request) {
		List<WarehouseStockVO> check = etcService.inventoryStockInfo(vo);
		int result;
		String str2 = request.getParameter("productNm");
		vo.setProductNm(str2);

		//재고변경 목록에 넣어줌
		etcService.insertInventoryInfo(vo);
		
		//기존에 stock에 있으면 update 없으면 insert
		if(check.size() == 0){
			result = etcService.insertInventoryStock(vo);
		} else {
			vo.setStock(check.get(0).getStock()+ vo.getStock());
			result = etcService.updateInventoryStock(vo);
		}
	
		return result;
	}	

	@GetMapping("/safetyManage.do")    
	public String safetyManage(Model model,String pagenum, String contentnum, String keyword) {
		Page pagemaker = new Page();
		int cpagenum;
		int ccontentnum;
		String key = "";		
		List<ProductVO> productNameList = etcService.getProductNameList();		
		model.addAttribute("list", productNameList);
		
		if(keyword == null || keyword == "" || keyword.length() == 0){
			key = productNameList.get(0).getProductNm();
		}		
		
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

		pagemaker.setTotalcount(etcService.safetyListCount(key)); // mapper 전체 게시글 개수를 지정한다
        pagemaker.setPagenum(cpagenum-1);   // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서 사용할수 있다
        pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
        pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
        pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다.

        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
        pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지 블록번호로 정한다.
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
        if(ccontentnum == 10){//선택 게시글 수
        	List<ProductDetailVO> list = etcService.getSafetyList(pagemaker.getPagenum()*10,pagemaker.getContentnum(),key);
            model.addAttribute("list", list);
            System.out.println(list);
        }else if(ccontentnum == 20){
        	List<ProductDetailVO> list = etcService.getSafetyList(pagemaker.getPagenum()*20,pagemaker.getContentnum(),key);
        	 model.addAttribute("list", list);
        	 System.out.println(list);
        }else if(ccontentnum ==30){
        	List<ProductDetailVO> list = etcService.getSafetyList(pagemaker.getPagenum()*30, pagemaker.getContentnum(),key);
        	model.addAttribute("list", list);
        }

         model.addAttribute("page", pagemaker);

		return "etc_manage/increase_safety";
	}
	
	@GetMapping("/creditList.do")    
	public String creditList(Model model,String pagenum, String contentnum, String keyword, String startDate, String endDate) {

		Page pagemaker = new Page();
		int cpagenum;
		int ccontentnum;
		Date startDt = null;
		Date endDt = null;
		System.out.println(startDate);
		System.out.println(endDate);
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
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
    		if(startDate != null){
    			startDt = formatter.parse(startDate);	
    		}
    		if(endDate != null){
        		endDt = formatter.parse(endDate);	
    		}
    		
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
		pagemaker.setTotalcount(etcService.creditListCount(keyword,startDt,endDt)); // mapper 전체 게시글 개수를 지정한다
        pagemaker.setPagenum(cpagenum-1);   // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서 사용할수 있다
        pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
        pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
        pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다.

        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
        pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지 블록번호로 정한다.
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
        if(ccontentnum == 10){//선택 게시글 수
        	List<Map<String, Object>>  list = etcService.getCreditList(pagemaker.getPagenum()*10,pagemaker.getContentnum(),keyword,startDt,endDt);
        	System.out.println(list);
            model.addAttribute("list", list);
        }else if(ccontentnum == 20){
        	List<Map<String, Object>>  list = etcService.getCreditList(pagemaker.getPagenum()*20,pagemaker.getContentnum(), keyword,startDt,endDt);
        	model.addAttribute("list", list);
        }else if(ccontentnum ==30){
        	List<Map<String, Object>>  list = etcService.getCreditList(pagemaker.getPagenum()*30, pagemaker.getContentnum(), keyword,startDt,endDt);
        	model.addAttribute("list", list);
        }
        
         model.addAttribute("keyword", keyword);
         model.addAttribute("page",pagemaker);
         
		return "etc_manage/credit_manage";
	}
	
	//창고별 재고관리 이동
	@GetMapping("/depositRegist.do")    
	public String depositRegist(Model model) {
		List<MemberVO> list = etcService.memberList();
		
		model.addAttribute("company", list);
		
		return "etc_manage/deposit_regist";
	}


	@GetMapping(value= "/goInvetoryManage.do")
	public String goInvetoryManage(Model model,String pagenum, String contentnum) {
		List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);
		List<ProductVO> productNameList = etcService.getProductNameList();
		
		model.addAttribute("warehouse", warehouse);
		model.addAttribute("productList", productNameList);
		
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

		pagemaker.setTotalcount(etcService.inventoryHistoryCount()); // mapper 전체 게시글 개수를 지정한다
        pagemaker.setPagenum(cpagenum-1);   // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서 사용할수 있다
        pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
        pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
        pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다.

        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
        pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지 블록번호로 정한다.
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
        if(ccontentnum == 10){//선택 게시글 수
        	List<WarehouseInventoryVO> list = etcService.getInventoryHistory(pagemaker.getPagenum()*10,pagemaker.getContentnum());
        	 System.out.println(list);
            model.addAttribute("list", list);
        }else if(ccontentnum == 20){
        	List<WarehouseInventoryVO> list = etcService.getInventoryHistory(pagemaker.getPagenum()*20,pagemaker.getContentnum());
        	 model.addAttribute("list", list);
        }else if(ccontentnum ==30){
        	List<WarehouseInventoryVO> list = etcService.getInventoryHistory(pagemaker.getPagenum()*30, pagemaker.getContentnum());
        	 model.addAttribute("list", list);
        }
        
//         model.addAttribute("keyword", keyword);
         model.addAttribute("page",pagemaker);

		return "/etc_manage/inventory_manage";
	}
	
	@GetMapping(value= "/goInventoryStatus.do")
	public String inventoryStatus(Model model,String pagenum, String contentnum) {
		List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);
		List<ProductVO> productNameList = etcService.getProductNameList();
		
		model.addAttribute("warehouse", warehouse);
		model.addAttribute("productList", productNameList);
		
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

		pagemaker.setTotalcount(etcService.inventoryHistoryCount()); // mapper 전체 게시글 개수를 지정한다
        pagemaker.setPagenum(cpagenum-1);   // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서 사용할수 있다
        pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
        pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
        pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다.

        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
        pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지 블록번호로 정한다.
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
        if(ccontentnum == 10){//선택 게시글 수
        	List<WarehouseInventoryVO> list = etcService.getInventoryHistory(pagemaker.getPagenum()*10,pagemaker.getContentnum());
        	 System.out.println(list);
            model.addAttribute("list", list);
        }else if(ccontentnum == 20){
        	List<WarehouseInventoryVO> list = etcService.getInventoryHistory(pagemaker.getPagenum()*20,pagemaker.getContentnum());
        	 model.addAttribute("list", list);
        }else if(ccontentnum ==30){
        	List<WarehouseInventoryVO> list = etcService.getInventoryHistory(pagemaker.getPagenum()*30, pagemaker.getContentnum());
        	 model.addAttribute("list", list);
        }
        
//         model.addAttribute("keyword", keyword);
         model.addAttribute("page",pagemaker);

		return "/etc_manage/inventory_status";
	}
	
	@PostMapping(value= "/selectCompany.do")
	@ResponseBody
	public Map<String,Object> selectCompany(String pagenum, String keyword) {

		if(keyword == null){
			keyword = "";
		}
		String contentnum = "5";
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

		System.out.println(pagemaker.getEndPage()+"endPa");
		System.out.println(pagemaker.getStartPage()+"endPa");
		return map;
	}	
	
	@PostMapping(value= "/searchCreditInfo.do")
	@ResponseBody
	public Map<String,Object>  searchCreditInfo(String keyword) {
		System.out.println(keyword);
		Map<String,Object> map = etcService.searchCreditInfo(keyword);
		System.out.println(map);
		return map;
	}
	
	//창고별 재고관리 이동
	@PostMapping("/addDepositHistory.do")
	@ResponseBody
	public boolean addDepositHistory(String depAmount, String totalDeposit,String companyNm,String totalOrder,String depDate,String credit) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("depAmount", depAmount.replace(",", ""));
		map.put("totalDeposit", Integer.parseInt(depAmount.replace(",", ""))+Integer.parseInt(totalDeposit.replace(",", "")));
		map.put("companyNm", companyNm);
		map.put("totalOrder", totalOrder.replace(",", ""));
		map.put("depDate", depDate);
		map.put("credit", credit.replace(",", ""));
		//총 입금액에 추가된 입금액 계산
		map.put("updateDepositInfo", Integer.parseInt(depAmount.replace(",", ""))+Integer.parseInt(totalDeposit.replace(",", "")));
		
		int updateTotalDep = etcService.updateTotalDeposit(map);
		int insertDepositHistory = etcService.addDepositHistory(map);
		boolean result = updateTotalDep == insertDepositHistory;
		
		return result;
	}
	
	//제품명 삭제
	@PostMapping("/historyDelete.do")
	@ResponseBody
	public int historyDelete(HttpServletRequest request) {
		String[] idxList = request.getParameterValues("idxList");
		int result = 0;
		for(int i=0; i<idxList.length; i++){
			System.out.println("idx"+idxList[i]);
			result += etcService.historyDelete(idxList[i]);
		}
		
		int returnVal = 0;
		if(result != idxList.length){
			returnVal = 1;
		}
		
		return returnVal;
	}
	
	//여신관리 수정화면 정보
	@GetMapping(value= "/searchDepositInfo.do")
	public String searchDepositInfo(Model model, String idx) {
		System.out.println(idx);
		Map<String,String> map= etcService.searchDepositInfo(idx);
		System.out.println(map);
        model.addAttribute("list",map);

		return "/etc_manage/deposit_regist";
	}
	
	//여신관리 수정화면 정보
	@PostMapping("/updateDepositHistory.do")
	@ResponseBody
	public boolean updateDepositHistory(Model model, int totalDeposit,String depDate,int depAmount, String idx, String companyNm) {
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
        try {
    		Date date = formatter.parse(depDate);
    		map.put("depDate", date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
		map.put("depAmount", depAmount);
		map.put("totalDeposit", totalDeposit);
		map.put("companyNm", companyNm);
		map.put("idx", idx);
		//총 입금액에 추가된 입금액 계산
		map.put("updateDepositInfo", totalDeposit);

		int updateTotalDep = etcService.updateTotalDeposit(map);
		int updateDepositHistory = etcService.updateDepositHistory(map);
		boolean result = updateTotalDep == updateDepositHistory;
		
		return result;
	}
}
