package com.springbook.email.impl;

import java.security.SecureRandom;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.springbook.email.service.EmailService;
import com.springbook.member.service.MemberService;
import com.springbook.member.vo.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender javaMailSender;
	private final MemberService memberService;

	@Override
	public SimpleMailMessage createMail(MemberVO member) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("gusal8490@naver.com");
		mailMessage.setTo(member.getEmail1()+'@'+member.getEmail2());
		mailMessage.setSubject("[WDBM]임시 비밀번호 발급 메일입니다.");
		mailMessage.setText("안녕하세요. 임시 비밀번호 발급 안내 메일입니다. 회원님의 임시 비밀번호는  "+ member.getPassword() + "입니다." );
		return mailMessage;
	}

	@Override
	public void sendMail(MemberVO member) {
		SimpleMailMessage mailMessage = createMail(member);
		javaMailSender.send(mailMessage);
	}
	@Override
	public void resetPassword(MemberVO member) throws Exception {
		member.setPassword(getRandomPassword(10)); 
		memberService.modifyPassword(member);
	}

	public String getRandomPassword(int size) {
		char[] charSet = new char[] {                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&' };
		
		StringBuffer sb = new StringBuffer();
		SecureRandom sr = new SecureRandom();
		int idx = 0; 
		for(int i = 0; i<size ; i++){
			idx = sr.nextInt(charSet.length);
			sb.append(charSet[idx]);
		}
		return sb.toString(); 
	}



}
