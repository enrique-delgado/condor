package com.condor.transactionsmanager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Condor Transactions Manager API")
                        .version("1.0.0")
                        .description("Microservicio para la gestión de cuentas y transacciones en Condor")
                        .contact(new Contact()
                                .name("Condor Team")
                                .email("support@condor.com")
                                .url("https://condor.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
