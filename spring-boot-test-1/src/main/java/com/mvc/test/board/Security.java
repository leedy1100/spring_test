package com.mvc.test.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AuthenticationProvider authenticationProvider;
    
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		//web.ignoring().antMatchers("/favicon.ico", "/css/**", "/image/**", "/js/**","/resource/**","/insert","/insertres");
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//http.csrf().disable();
    	http.exceptionHandling().accessDeniedPage("/accessDenied");
        http.authorizeRequests()
        .antMatchers("/","/insert","/insertres").permitAll()
        			.antMatchers("/user/**").hasAnyRole("USER","ADMIN")
                	.antMatchers("/admin/**").hasRole("ADMIN")
                	.anyRequest().authenticated()
                	.and()
                .formLogin()
                	.loginPage("/loginform")
                	.loginProcessingUrl("/login")
                	.usernameParameter("id")
                	.passwordParameter("password")
                	.defaultSuccessUrl("/list")
                	.permitAll()
                	.and()
                .logout()
                	.logoutUrl("/logout")
                	.logoutSuccessUrl("/")
                	.invalidateHttpSession(true)
                	.and()
                .csrf()
                	.and()
                .authenticationProvider(authenticationProvider);
                
                
    }
}


