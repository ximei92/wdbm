package com.springbook.email.service;

import org.springframework.mail.SimpleMailMessage;

/**
 * <h2>이메일 인증 서비스</h2>
 *
 */
public interface EmailService {
	
	/**
	 * <h3>인증메일 전송</h3>
	 * @param email
	 */

	public void createToken(String email);

	void send(SimpleMailMessage email);

	/**
	 * <h3>메일 /Id 체크</h3>
	 * Email또는 memberId로 등록된 email주소가 있는지 확인하고, 있으면 이메일 주소를 반환한다.  
	 * @param userId
	 * @return
	 */
	public String check(String userId);
		

		
	

}
