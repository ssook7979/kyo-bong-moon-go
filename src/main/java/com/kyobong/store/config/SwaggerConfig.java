package com.kyobong.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
        		.info(new Info()
        				.title("교봉문고 도서관리 API")
        				.version("v1")
        				.description("교봉문고 도서관리 API 입니다."))
                .addSecurityItem(new SecurityRequirement().addList("jwt token"))
                .components(new Components().addSecuritySchemes("token",
                        new SecurityScheme()
                        	.name("token")
                        	.type(SecurityScheme.Type.HTTP)
                        	.scheme("bearer")
                        	.bearerFormat("JWT")
                ));
    }
    
}
