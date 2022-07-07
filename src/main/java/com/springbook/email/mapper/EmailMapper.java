package com.springbook.email.mapper;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.springbook.email.vo.EmailToken;

@Mapper
public interface EmailMapper {
	
	Optional<EmailToken> findToken(String emailTokenId, LocalDateTime now, boolean expired);
	
	public int save(EmailToken token);
		

}
