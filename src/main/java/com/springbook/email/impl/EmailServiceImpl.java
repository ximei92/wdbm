package com.springbook.email.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.springbook.email.service.EmailService;
import com.springbook.email.vo.EmailVO;
import com.springbook.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender javaMailSender;
	private final MemberService memberService;

	@Async
	@Override
	public void send(SimpleMailMessage email) {
		javaMailSender.send(email);

	}

	@Override
	public void createToken(String userId) {

		
	}

	@Override
	public String check(String userId) {
		if (isEmail(userId)) {
			return memberService.checkEmail(userId);
		} else {
			return memberService.findEmailBy(userId);
			
		} 
	}


	private boolean isEmail(String userId) {
		if (userId.contains("@"))
			return true;
		else
			return false;
	}

}
