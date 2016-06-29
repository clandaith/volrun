package com.clandaith.volrun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:META-INF/spring/applicationContext.xml")
public class SpringBootWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}

	// @Bean
	// public EmbeddedServletContainerCustomizer containerCustomizer() {
	// return new EmbeddedServletContainerCustomizer() {
	// @Override
	// public void customize(ConfigurableEmbeddedServletContainer container) {
	//
	// ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/errors/401.html");
	// ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/errors/404.html");
	// ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errors/500.html");
	//
	// container.addErrorPages(error401Page, error404Page, error500Page);
	// }
	// };
	// }
}
