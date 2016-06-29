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
		// registry.addViewController("/hello").setViewName("hello");
		// registry.addViewController("/login").setViewName("login");
		// registry.addViewController("/about").setViewName("about");
		// registry.addViewController("/createuser").setViewName("createuser");
		// registry.addViewController("/verify_logout").setViewName("verify_logout");

		// registry.addViewController("/error").setViewName("/errors/general_error");
		// registry.addViewController("/Access_Denied").setViewName("/errors/access_denied");
		// registry.addViewController("/users/user").setViewName("/user/user");
		// registry.addViewController("/companies/company").setViewName("/company/company");
	}

	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}
}
