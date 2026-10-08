package com.ecommerce.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.ecommerce")          // Finds @Service, @Controller, @Component
@EnableJpaRepositories(basePackages = "com.ecommerce")  // Finds Spring Data JPA Repositories
@EntityScan(basePackages = "com.ecommerce")             // Finds @Entity classes
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}