package com.springbook.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(63072000).preload(true).and().frameOptions().disable();
		http.cors().and().csrf().disable().exceptionHandling().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/**").permitAll()
				.antMatchers("/auth/**").permitAll().antMatchers("/**").permitAll().antMatchers("/ws/**").permitAll()
//				.antMatchers("/watcher/**").permitAll().antMatchers("/peon/downloadScript").permitAll()
//				.antMatchers("/peon/sendScreenshot").permitAll().antMatchers("/peon/sendLogFile").permitAll()
//				.antMatchers("/peon/sendFile").permitAll().antMatchers("/peon/portal2").permitAll()
//	.antMatchers("/peon/test").permitAll()
//	.antMatchers("/peon/retrieveScreenshot").permitAll()
				.anyRequest().authenticated();
	}
	
	@Bean
	  public HttpFirewall defaultHttpFirewall() {
	    return new DefaultHttpFirewall();
	  }
	
	  @Override
	  public void configure(WebSecurity web) throws Exception {
	      web.httpFirewall(defaultHttpFirewall());
	  }
	  
	  @Bean
	  public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	  }

}
