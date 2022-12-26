package com.example.test123;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
        (exclude = { SecurityAutoConfiguration.class })
public class Test123Application {

    public static void main(String[] args) {
        SpringApplication.run(Test123Application.class, args);
    }

}
