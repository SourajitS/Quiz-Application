package com.microservice.quiz.services.impl;

import com.microservice.quiz.dto.CategoryDto;
import com.microservice.quiz.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service

public class CategoryServiceImplWebClient implements CategoryService {

    private final RestTemplate restTemplate;

    private final WebClient webClient;
    private final WebClient.Builder webClientBuilder;


    private  final ModelMapper modelMapper;

    private final Logger logger =org.slf4j.LoggerFactory.getLogger(CategoryServiceImplWebClient.class);

    public CategoryServiceImplWebClient(RestTemplate restTemplate,WebClient.Builder webClientBuilder , ModelMapper modelMapper) {
        this.restTemplate = restTemplate;

       this.webClientBuilder =webClientBuilder;
        this.modelMapper = modelMapper;
       this.webClient = webClientBuilder.baseUrl("lb://CATEGORY-SERVICE").build();


    }

    @Override
    public CategoryDto findById(String categoryId) {

        //logger.info("Error http://127.0.0.1:9092/api/v1/categories/{categoryId}");
        try {
            CategoryDto category= this.webClient
                    .get()
                    .uri("/api/v1/categories/{categoryId}", categoryId)
                    .retrieve()
                    .bodyToMono(CategoryDto.class)
                    .block();

            return category;
        }
        catch (WebClientResponseException ex)
        {
            if(ex.getStatusCode().equals(HttpStatus.NOT_FOUND))
            {
                logger.error("Category Not Found");
            } else if (ex.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                logger.info("Internal Server Error");
            }
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CategoryDto> findAll() {

       return this.webClient
                .get()
                .uri("/api/v1/categories")
                .retrieve()
                .bodyToFlux(CategoryDto.class)
                .collectList()
                .block();

    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {

       return this.webClient.post()
                .uri("/api/v1/categories")
                .bodyValue(categoryDto)
                .retrieve()
                .bodyToMono(CategoryDto.class)
                .block();

    }

    @Override
    public CategoryDto update(String categoryId, CategoryDto categoryDto) {
        return this.webClient.put()
                .uri("/api/v1/categories/{categoryId}",categoryId)
                .bodyValue(categoryDto)
                .retrieve()
                .bodyToMono(CategoryDto.class)
                .block();

    }

    @Override
    public void delete(String categoryId) {

        webClient.delete()
                .uri("/api/v1/categories/{categoryId}",categoryId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
