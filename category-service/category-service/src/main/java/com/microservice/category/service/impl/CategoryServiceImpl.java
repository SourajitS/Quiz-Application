package com.microservice.category.service.impl;

import com.microservice.category.dto.CategoryDto;
import com.microservice.category.entity.Category;
import com.microservice.category.repository.CategoryRepository;
import com.microservice.category.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = modelMapper.map(categoryDto, Category.class);
        category.setId(UUID.randomUUID().toString());
        Category saved = this.categoryRepository.save(category);
        return modelMapper.map(saved, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto) {


        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category Not Found"));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setActive(categoryDto.isActive());

        Category saved = categoryRepository.save(category);
        return modelMapper.map(saved,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category Not Found"));

        return modelMapper.map(category,CategoryDto.class);

    }

    @Override
    public void deleteCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category Not Found"));
       categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(category -> modelMapper.map(category,CategoryDto.class)).toList();
    }
}
