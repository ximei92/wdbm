package com.springbook.utill.session;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class SessionManager {
	
	Map<String,Object> store = new HashMap<>();
	
	public void createSession(String value, HttpServletResponse response) {
		String token = UUID.randomUUID().toString();
		store.put(token, value);
		Cookie cookie = new Cookie(SessionConst.SESSION_ID,token);
		response.addCookie(cookie);
		
	}

	public String getSession(HttpServletRequest request) {
		Cookie sessionCookie = findCookie(request);
		if(sessionCookie == null){
			return null ; 
		}
		return (String)store.get(sessionCookie.getValue());
	}

	private Cookie findCookie(HttpServletRequest request) {
		if(request.getCookies() == null){
			return null;
		}
		return Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals(SessionConst.SESSION_ID))
				.findFirst().orElse(null);
	}

	public void exprieSession(HttpServletResponse response) {
		 Cookie cookie = new Cookie(SessionConst.SESSION_ID, null);
		 cookie.setMaxAge(0);
		 response.addCookie(cookie);
		
	}

}
