package com.clandaith.volrun.helpers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ConversionServiceConfig extends WebMvcConfigurerAdapter {
	@Bean
	public DateFormatter calendarFormatter() {
		return new DateFormatter();
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(calendarFormatter());
	}
}
