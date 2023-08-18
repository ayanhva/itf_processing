package com.example.itf_processing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ItfProcessingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItfProcessingApplication.class, args);
    }
}
