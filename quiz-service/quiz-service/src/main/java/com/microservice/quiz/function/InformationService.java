package com.microservice.quiz.function;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class InformationService {

    @Bean
    public Supplier<String> getInformation()
    {
        return ()->"Hello";
    }
}
