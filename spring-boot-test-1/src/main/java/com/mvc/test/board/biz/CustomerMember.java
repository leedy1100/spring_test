package com.mvc.test.board.biz;


import org.springframework.security.core.userdetails.User;

import com.mvc.test.board.dto.CustomerDto;

@SuppressWarnings("serial")
public class CustomerMember extends User{

	public CustomerMember(CustomerDto dto) {
		super(dto.getId(), dto.getPassword(), dto.getAuthorities());
	}
	
	
}
