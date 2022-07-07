package com.springbook.member.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	
	
	@Test
	void findEmailByMemeberId(){
		
		//given		
		
		
		//when
		String email = memberService.findEmailBy("user4");
		
		
		//then\
		log.info("email = {}", email );
		

		
		
		
		
		
	}

}
