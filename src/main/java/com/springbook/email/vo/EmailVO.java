package com.springbook.email.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailVO {
	private String email;
	
	public EmailVO(String email){
		this.email = email;
	}

}
