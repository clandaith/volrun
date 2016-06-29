package com.clandaith.volrun.helpers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/about801dev").setViewName("about801dev");
		registry.addViewController("/requestmembership").setViewName("requestmembership");
		registry.addViewController("/verify_logout").setViewName("verify_logout");

		registry.addViewController("/admin/index").setViewName("admin/index");
		registry.addViewController("/users/index").setViewName("users/index");
		registry.addViewController("/users/viewfiles").setViewName("users/viewfiles");
		registry.addViewController("/users/localusers").setViewName("users/localusers");

		// registry.addViewController("/error").setViewName("/errors/general_error");
		// registry.addViewController("/Access_Denied").setViewName("/errors/access_denied");
	}

	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}
}
