package com.springbook.etc.creditmng.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.springbook.configuration.Page;
import com.springbook.etc.creditmng.dto.CreditExcelDTO;
import com.springbook.etc.creditmng.service.CreditMngService;
import com.springbook.utill.excel.ExcelXlsxView;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
//@RequestMapping("/credit")
@AllArgsConstructor
public class CreditMngController {
	private final CreditMngService creditMngService;
	
	
	@GetMapping("/creditList.do")    
	public ModelAndView creditList(ModelAndView mv,String pagenum, String contentnum, String keyword, String startDate, String endDate) {

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
        
		pagemaker.setTotalcount(creditMngService.creditListCount(keyword,startDt,endDt)); // mapper 전체 게시글 개수를 지정한다
        pagemaker.setPagenum(cpagenum-1);   // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서 사용할수 있다
        pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
        pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
        pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다.

        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
        pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지 블록번호로 정한다.
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
        if(ccontentnum == 10){//선택 게시글 수
        	List<Map<String, Object>>  list = creditMngService.getCreditList(pagemaker.getPagenum()*10,pagemaker.getContentnum(),keyword,startDt,endDt);
        	System.out.println(list);
            mv.addObject("list", list);
        }else if(ccontentnum == 20){
        	List<Map<String, Object>>  list = creditMngService.getCreditList(pagemaker.getPagenum()*20,pagemaker.getContentnum(), keyword,startDt,endDt);
        	mv.addObject("list", list);
        }else if(ccontentnum ==30){
        	List<Map<String, Object>>  list = creditMngService.getCreditList(pagemaker.getPagenum()*30, pagemaker.getContentnum(), keyword,startDt,endDt);
        	mv.addObject("list", list);
        }
        
         mv.addObject("keyword", keyword);
         mv.addObject("page",pagemaker);
         mv.setViewName("/etc_manage/credit_manage");
         
		return mv;
	}
	
	@PostMapping(value = "/credit/excel", produces ="application/json; charset=UTF-8"  )
	public ModelAndView downlaodDepositExcel(@RequestParam String jsonData) throws JsonMappingException, JsonProcessingException{
		log.info("jsonData = {}",jsonData);
		List<CreditExcelDTO> creditList = creditMngService.parseJson(jsonData);
		Map<String,Object> excelData = creditMngService.getCreditExcelData(creditList);
		return new ModelAndView(new ExcelXlsxView(),excelData);
		//return null; 
	}

}
