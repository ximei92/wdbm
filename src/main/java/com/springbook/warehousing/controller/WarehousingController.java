/*package com.springbook.warehousing.controller;
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
public class WarehousingController {
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
		System.out.println(cpagenum+"///"+ccontentnum);
		System.out.println("cunrtPa");
		System.out.println(pagemaker.getCurrentblock());
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
	

}
*/