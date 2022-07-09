package com.springbook.email.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailVO {
	
	private String fromAddress; // 받는사람 주소
	private String subject; // 제목
	private String content; // 내용
	private boolean isUseHtmlYn; // 메일 형싱 html인지 
	

}
