package com.mvc.test.board.biz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
	public CustomerDto login(String id, String pw) {
		return dao.login(id, pw);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		CustomerDto dto = dao.findById2(username);
		
		UserDetails userDetails = new UserDetails() {

			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isAccountNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public String getUsername() {
				// TODO Auto-generated method stub
				return dto.getId();
			}

			@Override
			public String getPassword() {
				// TODO Auto-generated method stub
				return dto.getPassword();
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				
				CustomerDto string_authorities = dao.findById(username);
				List<GrantedAuthority> authorities = new ArrayList<>();
				
				authorities.add(new SimpleGrantedAuthority(string_authorities.getAuthority()));
				
				return authorities;
			}
		};

		return userDetails;
	}

}
