package com.springbook.email.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbook.email.service.EmailService;
import com.springbook.email.vo.EmailVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/email")
@RestController
@RequiredArgsConstructor
public class EmailController {
	
	private final EmailService emailService;
	
	@PostMapping("/check")
	public EmailVO checkUser(@RequestParam String userId){
		log.info("userId={}", userId);
		return new EmailVO(emailService.check(userId)); 
		
	} 
	
	/**
	 * 이메일 전송  
	 * 
	 * @param userId
	 * @return
	 */
	@PostMapping("/send")
	public EmailVO sendMail(@RequestBody EmailVO email){
		log.info("sendMail email={}", email);
		//emailService.createToken(userId);
		return new EmailVO();
	}
	

}
