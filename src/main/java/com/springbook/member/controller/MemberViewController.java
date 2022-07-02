package com.springbook.member.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springbook.etc.service.EtcService;
import com.springbook.etc.vo.WarehouseVO;
import com.springbook.member.service.MemberService;
import com.springbook.member.vo.*;
import com.springbook.service.FileService;
import com.springbook.vo.FileVO;

@Controller
public class MemberViewController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private EtcService etcService;	
	@Autowired
	private FileService fileService;
	
	@Autowired(required = false)
	private PasswordEncoder encoder;
	
	@GetMapping("/login.do")
	public String login() {
		return "login";
	}
	
	@GetMapping(value= "/goMemberRegists.do")
	public String goMemberRegists(Model model, String id) {
		if(id != null){
			MemberVO result = memberService.getMemberInfo(id);
			FileVO file = fileService.getFileData(result.getFileId());
			model.addAttribute("info",result);
			model.addAttribute("fileInfo", file);
		}
		return "member_manage/member_regists";
	}
	
	@GetMapping(value= "/goMemberRegistsShipper.do")
	public String goMemberRegistsShipper(Model model, String id) {
		if(id != null){
			MemberVO result = memberService.getMemberInfo(id);
			FileVO file = fileService.getFileData(result.getFileId());
			model.addAttribute("info",result);
			model.addAttribute("fileInfo", file);
		}
		return "member_manage/member_regists_shipper";
	}
	
	@GetMapping(value= "/goMemberRegistsAdd.do")
	public String goMemberRegistsAdd(Model model, String id) {
		if(id != null){
			MemberVO result = memberService.getMemberInfo(id);
			FileVO file = fileService.getFileData(result.getFileId());
			model.addAttribute("info",result);
			model.addAttribute("fileInfo", file);
		}
		
		return "member_manage/member_regists_add";
	}
	
	@GetMapping(value= "/goMemberRegistsEdit.do")
	public String goMemberRegistsEdit(Model model, String roletype, String id) {
		if(id != null){
			MemberVO result = memberService.getMemberInfo(id);
			FileVO file = fileService.getFileData(result.getFileId());
			model.addAttribute("info",result);
			model.addAttribute("fileInfo", file);
		}
		model.addAttribute("roletype",roletype);
		return "member_manage/member_regists_edit";
	}
	
	@GetMapping(value= "/goMemberRegistsWarehouse.do")
	public String goMemberRegistsWarehouse(Model model, String id) {
		if(id != null){
			MemberVO result = memberService.getMemberInfo(id);
			FileVO file = fileService.getFileData(result.getFileId());
			model.addAttribute("info",result);
			model.addAttribute("fileInfo", file);
		}
		
		List<WarehouseVO> warehouse = etcService.getWarehouseList(0, 10000);
		model.addAttribute("warehouse", warehouse);
		
		return "member_manage/member_regists_warehouse";
	}
	
	@GetMapping(value= "/goMemberInfoSetting.do")
	public String goMemberInfoSetting(String id) {
		return "member_manage/member_info_setting";
	}

	@GetMapping(value= "/goPasswordReset.do")
	public String goPasswordReset() {
		return "/password_reset";
	}

	
}
