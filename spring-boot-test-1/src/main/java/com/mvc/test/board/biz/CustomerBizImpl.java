package com.mvc.test.board.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mvc.test.board.dao.CustomerDaoImpl;
import com.mvc.test.board.dto.CustomerDto;

@Service
public class CustomerBizImpl implements CustomerBiz, UserDetailsService {

	@Autowired
	private CustomerDaoImpl dao;
	@Autowired 
	PasswordEncoder passwordEncoder;

	@Override
	public List<CustomerDto> selectList() {

		return dao.selectList();
	}

	@Override
	public CustomerDto selectOne(String id) {
		return dao.selectOne(id);
	}

	@Override
	public int insert(CustomerDto dto) {

		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		dto.setAccouontNonExpired(true);
		dto.setAccountNonLocked(true);
		dto.setCredentialsNonExpired(true);
		dto.setEnabled(true);
		dto.setAuthority(dto.getAuthority());

		return dao.insert(dto);
	}

	@Override
	public int update(CustomerDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(String id) {
		return dao.delete(id);
	}

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		CustomerDto dto = dao.selectOne(id);
		System.out.println("dto는 : " + dto);
		if(dto == null) {
			System.out.println("loadUserByUsername : not existed user");
			throw new UsernameNotFoundException("아이디가 존재하지 않습니다.");
		}
		
		return dto;
	}


}
