package com.github.paulosalonso.zup.api.openapi;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class OpenApiConfiguration implements WebMvcConfigurer {

    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(ServletWebRequest.class)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.and(
                        Predicates.not(PathSelectors.ant("/error")),
                        PathSelectors.any()))
                .build()
                .apiInfo(buildApiInfo());

        addTags(docket);

        return docket;
    }

    private void addTags(Docket docket) {
        docket
                .tags(new Tag("Cities", "Cities Operations"));
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("Zup CRUD API")
                .description("Simple customers CRUD API built with SpringBoot")
                .version("0.0.1-SNAPSHOT")
                .contact(new Contact("Paulo Alonso",
                        "https://www.linkedin.com/in/paulo-alonso-67b082149/", "paulo_alonso_@hotmail.com"))
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
