package com.hfut.invigilate.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hfut.invigilate.controller"))//基础包名
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(LocalDate.class,String.class)
                .directModelSubstitute(LocalDateTime.class,String.class)
                .directModelSubstitute(LocalTime.class,String.class)
                .extensions(openApiExtensionResolver.buildExtensions("1.0"));
    }

}