package com.springbook.email.vo;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class EmailToken{
	private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L;    // 이메일 토큰 만료 시간
	
	private Long Idx;
	
	private LocalDateTime expriationDate;
	
	private boolean expired; 
	
	private String memberId;
	
	public static EmailToken create(Long Idx){
		EmailToken emailToken = new EmailToken();
		emailToken.expriationDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE); // 5분 후 만료 
		emailToken.Idx = Idx;
		emailToken.expired = false;
		return emailToken;
	}
	
	/**
	 * 토큰 만료 
	 */
	public void setTokenUsed(){
		this.expired = true; 
	}
	

}
