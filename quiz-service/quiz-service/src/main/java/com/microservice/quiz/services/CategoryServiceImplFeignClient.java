package com.microservice.quiz.services;

import com.microservice.quiz.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="category-service",url="http://localhost:9092/api/v1")

public interface CategoryServiceImplFeignClient {


    //get all categories
    @GetMapping("/categories")
    List<CategoryDto> findAll();

    @GetMapping("/categories/{categoryId}")
    CategoryDto findById(@PathVariable String categoryId);


    //Create new category
    @PostMapping("/categories")
    CategoryDto create( @RequestBody CategoryDto categoryDto);

    @PutMapping("/categories/{categoryId}")
    CategoryDto update(@PathVariable String categoryId,@RequestBody CategoryDto categoryDto);

    @DeleteMapping ("/categories/{categoryId}")
    void delete(@PathVariable String categoryId);

}

