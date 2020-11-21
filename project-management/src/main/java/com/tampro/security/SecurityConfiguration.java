package com.tampro.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	//xác thực
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		 //auth.inMemoryAuthentication() // xac thuc bộ nhớ
//		 auth.jdbcAuthentication().dataSource(dataSource)
//		 .withDefaultSchema() // tự tạo ra data , tự tạo table trong dataSource
//		 					//với  1 bảng user , 1 bảng authorities,
//		 					// 2 bảng được add dữ liệu từ WithUser ..
//		 .withUser("myUser")
//		 	.password("pass")
//		 	.roles("USER")
//		 .and()
//		 .withUser("taz")
//		 	.password("pass2")
//		 	.roles("USER")
//		 .and()
//		 .withUser("managerUser")
//			 .password("pass3")
//			 .roles("ADMIN");
		 auth.jdbcAuthentication()
		 	.usersByUsernameQuery("Select username , password ,enabled from user_accounts where username = ? ")
		 	.authoritiesByUsernameQuery("Select username , role from user_accounts where username = ?")
		 	.dataSource(dataSource)
		 	.passwordEncoder(bCryptPasswordEncoder);
		 
		 
	}
	 
	//Phân quyền
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
			.antMatchers("/projects/new").hasRole("ADMIN") // ROLE_ADMIN
			//.antMatchers("/employees/new").hasAuthority("ADMIN") // ADMIN
			.antMatchers("/employees/new").hasRole("ADMIN")
			.antMatchers("/projects/save").hasRole("ADMIN")
			.antMatchers("/employees/save").hasRole("ADMIN")
			//.antMatchers("/").authenticated() // mọi url đều phải xác thực
			.antMatchers("/","/**").permitAll()
			.and()
			.formLogin();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}
}
