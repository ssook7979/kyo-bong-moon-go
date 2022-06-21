package com.kyobong.store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kyobong.store.enums.converter.StringToBookStatus;
import com.kyobong.store.enums.converter.StringToCategory;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToBookStatus());
		registry.addConverter(new StringToCategory());
	}

}
