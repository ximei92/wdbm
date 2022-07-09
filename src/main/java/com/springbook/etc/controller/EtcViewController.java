package com.springbook.etc.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springbook.etc.service.EtcService;
import com.springbook.etc.vo.*;
import com.springbook.service.FileService;
import com.springbook.vo.FileVO;

@Controller
public class EtcViewController {
	@Autowired
	private EtcService etcService;
	@Autowired
	private FileService fileService;
	
	@Autowired(required = false)
	private PasswordEncoder encoder;
	
	@GetMapping(value= "/goJoinInquiry.do")
	 
	public String goJoinInquiry() {
		return "/join_inquiry";
	}

	@GetMapping(value= "/goTypeRegist.do")
	public String goTypeRegist(Model model, String idx, String fileId) {
		
		if(idx != null){
			FileVO file = fileService.getFileData(fileId);
			model.addAttribute("fileInfo", file);
			List<ProductVO> info = etcService.getProductInfo(idx);
			model.addAttribute("info", info);
		}
		
		return "/etc_manage/type_regist";
	}

}
