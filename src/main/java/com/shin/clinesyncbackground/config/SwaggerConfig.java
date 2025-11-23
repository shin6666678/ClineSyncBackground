package com.shin.clinesyncbackground.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CineSync 电影票务系统 API")
                        .version("1.0")
                        .description("CineSync 电影票务系统的 RESTful API 接口文档")
                        .contact(new Contact()
                                .name("技术支持")
                                .email("support@clinesync.com")));
    }
}