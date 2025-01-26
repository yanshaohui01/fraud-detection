package com.hsbc.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 〈功能概述〉<br>
 *
 * @className: SwaggerConfig
 * @package: com.hsbc.springboot.config
 * @author: bruce
 * @date: 2025/1/25 23:55
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hsbc.springboot.controller"))
                .paths(PathSelectors.regex("^(?!.*swagger-ui).*$"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot 2.2.5 集成 Swagger 示例")
                .description("实时欺诈检测系统，用于生成 API 文档。")
                .version("1.0.0")
                .build();
    }
}
