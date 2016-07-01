package com.clandaith.volrun.helpers;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
						.antMatchers("/", "/home", "/forgotpassword", "/resources/**", "/fonts/**", "/about801dev", "/requestmembership").permitAll()
						.antMatchers("/manage/**").access("hasRole('ADMIN')")
						.antMatchers("/users/**").access("hasRole('USER') or hasRole('ADMIN')")
						.antMatchers("/admin/**").access("hasRole('ADMIN')")
						.anyRequest().authenticated()
						.and()
							.formLogin()
								.loginPage("/login")
									.usernameParameter("multipassName")
									.passwordParameter("multipassIdent")
									.permitAll()
						.and().csrf()
						.and().exceptionHandling().accessDeniedPage("/Access_Denied")
						.and().logout().logoutSuccessUrl("/?logout").permitAll();
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
						.dataSource(dataSource)
						.usersByUsernameQuery(
										"select username, password, enabled from users where username = ?")
						.authoritiesByUsernameQuery("select username, role from user_roles where username = ?")
						.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {		
		return new BCryptPasswordEncoder();
	}
}