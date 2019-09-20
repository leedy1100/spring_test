package com.mvc.test.board.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mvc.test.board.dao.CustomerDaoImpl;
import com.mvc.test.board.dto.CustomerDto;

public class UserDeSer implements UserDetailsService {

	@Autowired
	private CustomerDaoImpl dao;
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		System.out.println("입력한 ID는 : "+ id);
		CustomerDto dto = dao.selectOne(id);
		
		if(dto == null) {
			System.out.println("dto: " +dto);
			System.out.println("loadUserByUsername : not existed user");
			throw new UsernameNotFoundException("login fail");
		}
		
		return dto;
	}

}
