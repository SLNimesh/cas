package com.nelanga.cas.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(swaggerInfo());
    }

    private Info swaggerInfo() {
        return new Info()
                .title("CAS API")
                .description("Generated API documentation for CAS using springdoc-openapi and OpenAPI 3.")
                .version("v1.0");
    }

    public static OrRequestMatcher swaggerPaths() {
        return new OrRequestMatcher(
                new AntPathRequestMatcher("/api/v1/docs/**"),
                new AntPathRequestMatcher("/h2-console/**") // TODO: Remove when H2 is not needed
        );
    }
}
