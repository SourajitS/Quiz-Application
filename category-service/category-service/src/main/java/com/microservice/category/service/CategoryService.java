package com.microservice.category.service;

import com.microservice.category.dto.CategoryDto;

import java.util.*;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(String categoryId,CategoryDto categoryDto);

    CategoryDto getCategory(String categoryId);
     void deleteCategory (String  categoryId);

     List<CategoryDto> getAllCategory();
}
