package com.quoctoanphamtoan.person_post.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/api/**") // configuration only accepted with url having prefix /api/
//			.allowedOrigins("*") // access domain
//			.allowedMethods("GET", "POST", "PUT", "DELETE")
//			.allowCredentials(false) // not use cookie
//			.maxAge(4800); // timeout alive in cache 4800s
//	}
	
}