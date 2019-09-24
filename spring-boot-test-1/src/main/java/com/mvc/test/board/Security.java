package com.mvc.test.board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mvc.test.board.biz.CustomAuthenticationProvider;

@Configuration
class Security extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomAuthenticationProvider authenticationProvider;
	
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/favicon.ico", "/css/**", "/image/**", "/js/**","/resource/**","/insert","/insertres","/loginajax");
	}
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
    	
        http.authorizeRequests()
                	.antMatchers("/loginform","/resource/**","/insert","/insertres").permitAll()
                	.anyRequest().authenticated()
                	.and()
                .formLogin()
                	.loginPage("/loginform")
                	.loginProcessingUrl("/login")
                	.usernameParameter("id")
                	.passwordParameter("password")
                	.defaultSuccessUrl("/")
                	.permitAll()
                	.and()
                .logout()
                	.logoutUrl("/logout")
                	.logoutSuccessUrl("/")
                	.invalidateHttpSession(true)
                	.permitAll()
                	.and()
                	.authenticationProvider(authenticationProvider);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}


