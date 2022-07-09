package com.springbook.email.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbook.email.service.EmailService;
import com.springbook.member.service.MemberService;
import com.springbook.member.vo.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/email")
@RestController
@RequiredArgsConstructor
public class EmailController {
	
	private final EmailService emailService;
	private final MemberService memberService;
	/**
	 * 이메일 전송  
	 * 
	 * @param userId
	 * @return
	 */
	@PostMapping("/send/")
	public void sendMail(@RequestBody MemberVO member){
		log.info("sendMail, member={}", member);
		emailService.resetPassword(member);
		emailService.sendMail(member);
	}
	

}
