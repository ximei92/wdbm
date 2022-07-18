package com.springbook.email.service;

import org.springframework.mail.SimpleMailMessage;

import com.springbook.email.vo.EmailVO;
import com.springbook.member.vo.MemberVO;

/**
 * <h2>이메일 인증 서비스</h2>
 *
 */
public interface EmailService {
	
	SimpleMailMessage createMail(MemberVO member);

	void sendMail(MemberVO member);

	void resetPassword(MemberVO member) throws Exception;


		

		
	

}
