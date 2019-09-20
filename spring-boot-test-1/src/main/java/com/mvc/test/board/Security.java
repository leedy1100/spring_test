package com.mvc.test.board;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import com.mvc.test.board.biz.CustomAuthenticationProvider;

@Component 
class Security extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception
	{
		//web.ignoring().antMatchers("/favicon.ico", "/css/**", "/image/**", "/js/**","/resource/**","/insert","/insertres","/loginajax");
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                	.antMatchers("/loginform","/resource/**","/insert","/insertres").permitAll()
                	.anyRequest().authenticated()
                	.and()
                .formLogin()
                	.loginPage("/loginform")
                	.loginProcessingUrl("/login")
                	.usernameParameter("id")
                	.passwordParameter("password")
                	.permitAll()
                	.defaultSuccessUrl("/")
                	.and()
                .logout()
                	.logoutUrl("/logout")
                	.logoutSuccessUrl("/")
                	.invalidateHttpSession(true)
                	.permitAll();
        http.authenticationProvider(new CustomAuthenticationProvider());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}


