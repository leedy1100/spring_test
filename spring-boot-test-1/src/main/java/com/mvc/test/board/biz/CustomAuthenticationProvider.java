package com.mvc.test.board.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mvc.test.board.dto.CustomerDto;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsService userDeSer;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String id = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		System.out.println("id : " + id + "\t pw : " + password);
		
		CustomerDto dto = null;
		System.out.println("USERDESER = " + userDeSer);
		dto = (CustomerDto)userDeSer.loadUserByUsername(id);
		if(!passwordEncoder.matches(password, dto.getPassword())) {
			throw new BadCredentialsException("암호가 일치하지 않습니다.");
		}
		
		if(!dto.isEnabled()) {
            throw new BadCredentialsException("인증되지 않은 회원입니다.");
        }

		return new UsernamePasswordAuthenticationToken(id,password,dto.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
