package com.microservice.quiz.services;

import com.microservice.quiz.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="category-service")

public interface CategoryServiceFeignClient {


    //get all categories
    @GetMapping("api/v1/categories")
    List<CategoryDto> findAll();

    @GetMapping("api/v1/categories/{categoryId}")
    CategoryDto findById(@PathVariable String categoryId);


    //Create new category
    @PostMapping("api/v1/categories")
    CategoryDto create( @RequestBody CategoryDto categoryDto);

    @PutMapping("api/v1/categories/{categoryId}")
    CategoryDto update(@PathVariable String categoryId,@RequestBody CategoryDto categoryDto);

    @DeleteMapping ("api/v1/categories/{categoryId}")
    void delete(@PathVariable String categoryId);

}

