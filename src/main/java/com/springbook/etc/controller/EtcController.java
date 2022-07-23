package com.springbook.etc.controller;
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

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.configuration.Page;
import com.springbook.etc.service.EtcService;
import com.springbook.etc.vo.JoinAskVO;
import com.springbook.etc.vo.ProductDetailVO;
import com.springbook.etc.vo.ProductSizeVO;
import com.springbook.etc.vo.ProductThicknessVO;
import com.springbook.etc.vo.ProductVO;
import com.springbook.etc.vo.WarehouseInventoryVO;
import com.springbook.etc.vo.WarehouseVO;
import com.springbook.member.vo.MemberVO;
import com.springbook.service.FileService;
import com.springbook.utill.excel.ExcelXlsxView;
import com.springbook.vo.FileVO;

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
        	List<Map<String , Object>> list = etcService.getThicknessList(pagemaker.getPagenum()*10,pagemaker.getContentnum(),type, keyword);
            model.addAttribute("list", list);
        }else if(ccontentnum == 20){
        	List<Map<String , Object>> list = etcService.getThicknessList(pagemaker.getPagenum()*20,pagemaker.getContentnum(),type, keyword);
        	 model.addAttribute("list", list);
        }else if(ccontentnum ==30){
        	List<Map<String , Object>> list = etcService.getThicknessList(pagemaker.getPagenum()*30, pagemaker.getContentnum(),type, keyword);
        	 model.addAttribute("list", list);
        }
        
         model.addAttribute("keyword", keyword);
         model.addAttribute("page", pagemaker);
         model.addAttribute("type",type);
         
		return "etc_manage/product_thickness";
	}

	//두께 중복확인
	@PostMapping("/checkProductThickness.do")
	@ResponseBody
	public int checkProductThickness(ProductThicknessVO vo) {

		int result = etcService.checkProductThickness(vo);
		return result;
	}
	
	//두께 등록
	@PostMapping("/addProductThickness.do")
	@ResponseBody
	public int addProductThickness(ProductThicknessVO vo) {
		System.out.println(vo);
		int result = etcService.addProductThickness(vo);
		return result;
	}
	
	//두께 업데이트
	@PostMapping("/updateProductThickness.do")
	@ResponseBody
	public int updateProductThickness(ProductThicknessVO vo) {
		int result = etcService.updateProductThickness(vo);
		return 0;
	}
	
	//두께등록페이지 이동
	@GetMapping(value= "/goThicknessRegist.do")
	public String goThicknessRegist(Model model, String idx, String fileId) {
		List<ProductVO> productNameList = etcService.getProductNameList();		
		model.addAttribute("productList", productNameList);
		
		if(idx != null){
			FileVO file = fileService.getFileData(fileId);
			model.addAttribute("fileInfo", file);
			List<Map<String,Object>> list = etcService.getThicknessInfo(idx);
			model.addAttribute("list", list);
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
	public String goSizeRegist(Model model,String idx) {
		List<ProductVO> productNameList = etcService.getProductNameList();		
		model.addAttribute("productList", productNameList);
		
		if(idx != null){
			List<Map<String,Object>> list = etcService.getSizeInfo(idx);
			model.addAttribute("list", list);
		}

		return "/etc_manage/size_regist";
	}
	
	//사이즈 중복확인
	@PostMapping("/checkProductSize.do")
	@ResponseBody
	public int checkProductSize(ProductSizeVO vo) {
System.out.println("SSSSIIIIIIZZZZZEEEEE");
System.out.println(vo);
		int result = etcService.checkProductSize(vo);
		return result;
	}
	
	//사이즈 등록
	@PostMapping("/addProductSize.do")
	@ResponseBody
	public int addProductSize(ProductSizeVO vo) {
		int result = etcService.addProductSize(vo);
		return result;
	}
	
	//사이즈 수정
	@PostMapping("/updateProductSize.do")
	@ResponseBody
	public int updateProductSize(ProductSizeVO vo) {
		int result = etcService.updateProductSize(vo);
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
			List<Map<String,Object>> list = etcService.getDetailInfo(idx);
			model.addAttribute("list", list);
			String productIdx = list.get(0).get("PRODUCT_IDX").toString();

			List<Map> thicklist = etcService.selectDetailThickness(productIdx);
			List<Map> sizelist = etcService.selectDetailSize(productIdx);

			Map<String,Object> map = new HashMap<String,Object>();
			
			map.put("thickness", thicklist);
			map.put("size", sizelist);
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
		System.out.println(thicklist);
		System.out.println(sizelist);
		map.put("thickness", thicklist);
		map.put("size", sizelist);
		
		return map;
	}
	
	//상세설정 등록
	@PostMapping("/addProductDetail.do")
	@ResponseBody
	public int addProductDetail(com.springbook.etc.vo.ProductDetailVO vo) {
		List<ProductDetailVO> cnt = etcService.findProductDetail(vo);
		int result = 0;
		System.out.println(cnt.size());
		System.out.println(vo);
		if(cnt.size() != 0 ){
			vo.setDetailIdx(cnt.get(0).getDetailIdx());
			result = etcService.updateProductDetail(vo);
		} else {
			result = etcService.addProductDetail(vo);
		}
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
	
	//창고관리 조회
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
	
	//창고 수정페이지, 생성페이지
	@GetMapping(value= "/warehouseCreate.do")
	public String warehouseCreate(Model model, String warehouseIdx, String fileId) {
		
		if(warehouseIdx != null){
			FileVO file = fileService.getFileData(fileId);
			model.addAttribute("fileInfo", file);
			WarehouseVO vo = etcService.getWarehoseInfo(warehouseIdx);
			model.addAttribute("list", vo);
		}
		return "/etc_manage/warehouse_create";
	}	
	
	//창고생성
	@PostMapping("/addWarehouse.do")
	@ResponseBody
	public int addWarehouse(HttpServletRequest request, String fileId ,String fileNm, String warehouse ) {
		FileVO file = fileService.getFileData(fileId);
		String filePath = file.getLogiPath()+file.getLogiNm();
		int result = 0;
		int addWarehouseResult = etcService.addWarehouse(fileId,warehouse);
		WarehouseVO vo = etcService.selectWarehouse(fileId,warehouse);

		//csv 파일 읽어서 db에 저장
        List<List<String>> csvList = new ArrayList<List<String>>();
        List<Map<String,Object>> insertFeeList = new ArrayList<Map<String,Object>>();
        File csv = new File(filePath);
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(csv));
            while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
                aLine = Arrays.asList(lineArr);
                csvList.add(aLine);
            }

            for(int i = 1; i<csvList.size(); i++){            	
            	for(int j = 3; j<csvList.get(1).size(); j++){
            	Map<String,Object> tempMap = new HashMap<String,Object>();
	            	tempMap.put("warehouseIdx", vo.getWarehouseIdx());
	            	tempMap.put("SIDO_NM", csvList.get(i).get(0));
	            	tempMap.put("SIGUNGU_NM", csvList.get(i).get(1));
	            	tempMap.put("EUBMYEONDONG_NM", csvList.get(i).get(2));
            		tempMap.put("ton", csvList.get(0).get(j));
            		tempMap.put("fee", csvList.get(i).get(j));

            		insertFeeList.add(tempMap);
            	}

            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) { 
                    br.close(); // 사용 후 BufferedReader를 닫아준다.
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(insertFeeList);

        int addFeeResult = etcService.addWarehouseFee(insertFeeList);
        
        if(addFeeResult < 1){
        	result = 3;
        } else if(addWarehouseResult != 1){
        	result = 2;
        }
        
		return result;
	}

	
	//창고수정
	@PostMapping("/updateWarehouse.do")
	@ResponseBody
	public int updateWarehouse(HttpServletRequest request, String fileId ,String fileNm, String warehouse ,String fileChange, String warehouseIdx) {
		int result = 0;
		int addFeeResult = 2;
		int updateWarehouseResult = etcService.updateWarehouse(fileId,warehouse,warehouseIdx);
		//csv 파일 변경되었으면 기존 데이터 삭제후 재 업로드
		System.out.println(fileChange);
		System.out.println(warehouseIdx);
		System.out.println(fileId);
		if(fileChange.equals("Y")){

		int	deleteWarehouseResult = etcService.deleteWarehouseFee(warehouseIdx);	
		System.out.println(deleteWarehouseResult);
		FileVO file = fileService.getFileData(fileId);
		String filePath = file.getLogiPath()+file.getLogiNm();

		//csv 파일 읽어서 db에 저장
        List<List<String>> csvList = new ArrayList<List<String>>();
        List<Map<String,Object>> insertFeeList = new ArrayList<Map<String,Object>>();
        File csv = new File(filePath);
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(csv));
            while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
                aLine = Arrays.asList(lineArr);
                csvList.add(aLine);
            }

            for(int i = 1; i<csvList.size(); i++){            	
            	for(int j = 3; j<csvList.get(1).size(); j++){
            	Map<String,Object> tempMap = new HashMap<String,Object>();
	            	tempMap.put("warehouseIdx", warehouseIdx);
	            	tempMap.put("SIDO_NM", csvList.get(i).get(0));
	            	tempMap.put("SIGUNGU_NM", csvList.get(i).get(1));
	            	tempMap.put("EUBMYEONDONG_NM", csvList.get(i).get(2));
            		tempMap.put("ton", csvList.get(0).get(j).replace("톤",""));
            		tempMap.put("fee", csvList.get(i).get(j));
System.out.println(tempMap);
            		insertFeeList.add(tempMap);
            	}

            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) { 
                    br.close(); // 사용 후 BufferedReader를 닫아준다.
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        addFeeResult = etcService.addWarehouseFee(insertFeeList);
        
		}
        
        if(addFeeResult < 2){
        	result = 3;
        } else if(updateWarehouseResult != 1){
        	result = 2;
        }
        
		return result;
	}
	
	//제품명 삭제
	@PostMapping("/deleteWarehouse.do")
	@ResponseBody
	public int deleteWarehouse(HttpServletRequest request) {
		String[] idxList = request.getParameterValues("idxList");
		int result = 0;
		for(int i=0; i<idxList.length; i++){
			result += etcService.warehouseDelete(idxList[i]);
			etcService.deleteWarehouseFee(idxList[i]);	
		}
		
		int returnVal = 0;
		if(result != idxList.length){
			returnVal = 1;
		}
		
		return returnVal;
	}
	
	//창고별 재고관리 이동
	@GetMapping("/addInventoryStock.do")    
	public String addInventoryStock(Model model) {
		List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);
		List<ProductVO> productNameList = etcService.getProductNameListGroup();
		
		model.addAttribute("warehouse", warehouse);
		model.addAttribute("productList", productNameList);
		
		return "etc_manage/inventory_manage_view";
	}
	
	//창고별 재고관리 등록
	@PostMapping("/updateInventoryStock.do")
	@ResponseBody
	public int updateInventoryStock(WarehouseInventoryVO vo, HttpServletRequest request) {
		int result;

		result = etcService.insertInventoryInfo(vo);

		return result;
	}	

	@GetMapping("/safetyManage.do")    
	public String safetyManage(Model model,String pagenum, String contentnum, String keyword) {
		Page pagemaker = new Page();
		int cpagenum;
		int ccontentnum;
		String key = "";		
		List<ProductVO> productNameList = etcService.getProductNameListGroup();		
		model.addAttribute("productNameList", productNameList);
		
		if(keyword == null || keyword == "" || keyword.length() == 0){
			key = Integer.toString(productNameList.get(0).getProductIdx());
		}	else{
			key = keyword;
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
        	List<Map<String,Object>> list = etcService.getSafetyList(pagemaker.getPagenum()*10,pagemaker.getContentnum(),key);
            model.addAttribute("list", list);
            System.out.println(list);
        }else if(ccontentnum == 20){
        	List<Map<String,Object>> list = etcService.getSafetyList(pagemaker.getPagenum()*20,pagemaker.getContentnum(),key);
        	 model.addAttribute("list", list);
        	 System.out.println(list);
        }else if(ccontentnum ==30){
        	List<Map<String,Object>> list = etcService.getSafetyList(pagemaker.getPagenum()*30, pagemaker.getContentnum(),key);
        	model.addAttribute("list", list);
        }

         model.addAttribute("page", pagemaker);
         model.addAttribute("productKey",key);
         
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
	public String goInvetoryManage(Model model,String pagenum, String contentnum, String productIdx, String warehouseIdx, String thickness, String size) {
		List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);
		List<ProductVO> productNameList = etcService.getProductNameListGroup();
		model.addAttribute("warehouse", warehouse);
		model.addAttribute("productList", productNameList);
		Page pagemaker = new Page();
		int cpagenum;
		int ccontentnum;
		boolean check = false;
		if(productIdx == null && warehouseIdx == null && thickness == null && size == null){
			check = true;
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

		if(check){
			pagemaker.setTotalcount(etcService.inventoryHistoryAllCount()); 
			System.out.println(etcService.inventoryHistoryAllCount());
		} else{
			pagemaker.setTotalcount(etcService.inventoryHistoryCount(productIdx,warehouseIdx,thickness,size)); 
			System.out.println(etcService.inventoryHistoryCount(productIdx,warehouseIdx,thickness,size));
		}
		// mapper 전체 게시글 개수를 지정한다
        pagemaker.setPagenum(cpagenum-1);   // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서 사용할수 있다
        pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
        pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
        pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다.

        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
        pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지 블록번호로 정한다.
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
        if(ccontentnum == 10){//선택 게시글 수
        	if(check){
            	List<Map<String,Object>> list = etcService.getInventoryHistoryAll(pagemaker.getPagenum()*10,pagemaker.getContentnum(),productIdx,warehouseIdx,thickness,size);
                model.addAttribute("list", list);       		
        	} else{
        	List<Map<String,Object>> list = etcService.getInventoryHistory(pagemaker.getPagenum()*10,pagemaker.getContentnum(),productIdx,warehouseIdx,thickness,size);
        	System.out.println(list);
            model.addAttribute("list", list);
        	}
        }else if(ccontentnum == 20){
        	if(check){
            	List<Map<String,Object>> list = etcService.getInventoryHistoryAll(pagemaker.getPagenum()*20,pagemaker.getContentnum(),productIdx,warehouseIdx,thickness,size);
                model.addAttribute("list", list);       		
        	} else{
        		List<Map<String,Object>> list = etcService.getInventoryHistory(pagemaker.getPagenum()*20,pagemaker.getContentnum(),productIdx,warehouseIdx,thickness,size);
        		model.addAttribute("list", list);
        	}
        }else if(ccontentnum ==30){
        	if(check){
            	List<Map<String,Object>> list = etcService.getInventoryHistoryAll(pagemaker.getPagenum()*30,pagemaker.getContentnum(),productIdx,warehouseIdx,thickness,size);
                model.addAttribute("list", list);       		
        	} else{
        		List<Map<String,Object>> list = etcService.getInventoryHistory(pagemaker.getPagenum()*30,pagemaker.getContentnum(),productIdx,warehouseIdx,thickness,size);
        		model.addAttribute("list", list);
        	}
        }

         model.addAttribute("page",pagemaker);
         model.addAttribute("productIdxKey",productIdx);
         model.addAttribute("warehouseIdxKey",warehouseIdx);
         model.addAttribute("thicknessKey",thickness);
         model.addAttribute("sizeKey",size);

		return "/etc_manage/inventory_manage";
	}
	
	@GetMapping(value= "/goInventoryStatus.do")
	public String inventoryStatus(Model model,String pagenum, String contentnum, String productIdx, String warehouseIdx) {
		List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);
		List<ProductVO> productNameList = etcService.getProductNameListGroup();
	
		model.addAttribute("warehouse", warehouse);
		model.addAttribute("productNameList", productNameList);
		
		Page pagemaker = new Page();
		int cpagenum;
		int ccontentnum;
		String productKey;
		String warehouseKey;
		
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
		
		if(productIdx == "" || productIdx == null){
			productKey = productNameList.get(0).getProductNm();
		} else { 
			productKey = productIdx;
		}

		if(warehouseIdx == "" || warehouseIdx == null){
			warehouseKey = Integer.toString(warehouse.get(0).getWarehouseIdx());
		} else { 
			warehouseKey = warehouseIdx;
		}

		pagemaker.setTotalcount(etcService.inventoryStatusCount(productKey , warehouseKey)); // mapper 전체 게시글 개수를 지정한다
        pagemaker.setPagenum(cpagenum-1);   // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서 사용할수 있다
        pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
        pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
        pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다.

        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
        pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지 블록번호로 정한다.
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
        if(ccontentnum == 10){//선택 게시글 수
        	List<Map<String,Object>> list = etcService.getInventoryStatus(pagemaker.getPagenum()*10,pagemaker.getContentnum(),productKey ,warehouseKey);
        	 System.out.println(list);
            model.addAttribute("list", list);
        }else if(ccontentnum == 20){
        	List<Map<String,Object>> list = etcService.getInventoryStatus(pagemaker.getPagenum()*10,pagemaker.getContentnum(),productKey ,warehouseKey);
        	 model.addAttribute("list", list);
        }else if(ccontentnum ==30){
        	List<Map<String,Object>> list = etcService.getInventoryStatus(pagemaker.getPagenum()*10,pagemaker.getContentnum(),productKey ,warehouseKey);
        	 model.addAttribute("list", list);
        }
         model.addAttribute("page",pagemaker);
         model.addAttribute("productKey",productKey);
         model.addAttribute("warehouseKey",warehouseKey);
         System.out.println(warehouse);
         System.out.println(productNameList);

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
		System.out.println(map == null);
		if(map == null){
			map = etcService.memberInfoDeposit(keyword);
			map.put("TOTAL_ORDER", 0);
			map.put("TOTAL_DEP", 0);
			map.put("id",map.get("ID"));
			System.out.println(map);
			
		}
		
		return map;
	}
	
	//창고별 재고관리 이동
	@PostMapping("/addDepositHistory.do")
	@ResponseBody
	public boolean addDepositHistory(String depAmount, String totalDeposit,String companyNm,String totalOrder,String depDate,String credit) {
		System.out.println("id====="+companyNm);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("depAmount", depAmount.replace(",", ""));
		map.put("totalDeposit", Integer.parseInt(depAmount.replace(",", ""))+Integer.parseInt(totalDeposit.replace(",", "")));
		map.put("companyNm", companyNm);
		map.put("totalOrder", totalOrder.replace(",", ""));
		map.put("depDate", depDate);
		map.put("credit", credit.replace(",", ""));
		//총 입금액에 추가된 입금액 계산
		map.put("updateDepositInfo", Integer.parseInt(depAmount.replace(",", ""))+Integer.parseInt(totalDeposit.replace(",", "")));
		
//		int updateTotalDep = etcService.updateTotalDeposit(map);
		int insertDepositHistory = etcService.addDepositHistory(map);
		boolean result = true;
		
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
	public String searchDepositInfo(Model model, String idx, String dep, String order) {
		System.out.println(idx);
		Map<String,String> map= etcService.searchDepositInfo(idx);
		map.put("TOTAL_ORDER",order);
		map.put("TOTAL_DEP", dep);
		System.out.println(map);
		System.out.println("mdmamdmamddmadm");
        model.addAttribute("list",map);

		return "/etc_manage/deposit_regist";
	}
	
	//여신관리 수정화면 정보
	@PostMapping("/updateDepositHistory.do")
	@ResponseBody
	public boolean updateDepositHistory(Model model, int totalDeposit,String depDate,int depAmount, String idx, String companyNm) {
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("memberid ===="+companyNm);
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

//		int updateTotalDep = etcService.updateTotalDeposit(map);
		int updateDepositHistory = etcService.updateDepositHistory(map);
		boolean result= true;
		
		return result;
	}
	
	@PostMapping("/deposit-excel/down")
	@ResponseBody
	public ModelAndView downlaodDepositExcel(Map<String, ?> excelData){ 
		
		return new ModelAndView(new ExcelXlsxView(),excelData);
		
		
	}
}
