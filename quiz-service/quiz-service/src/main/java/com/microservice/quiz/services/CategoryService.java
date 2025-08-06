package com.microservice.quiz.services;

import com.microservice.quiz.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

     CategoryDto findById(String catgoryId);

     List<CategoryDto> findAll();

     CategoryDto create(CategoryDto categoryDto);

     CategoryDto update (String categoryId,CategoryDto categoryDto);

     void delete(String categoryId);




}
