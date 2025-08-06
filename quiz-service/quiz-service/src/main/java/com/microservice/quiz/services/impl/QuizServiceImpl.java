package com.microservice.quiz.services.impl;

import com.microservice.quiz.collections.Quiz;
import com.microservice.quiz.dto.CategoryDto;
import com.microservice.quiz.dto.QuizDto;
import com.microservice.quiz.repositories.QuizRepository;
import com.microservice.quiz.services.CategoryService;
import com.microservice.quiz.services.CategoryServiceImplFeignClient;
import com.microservice.quiz.services.QuizService;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final CategoryService categoryService;
    private final CategoryServiceImplFeignClient categoryServiceImplFeignClient;
    private static final Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);



    public QuizServiceImpl(QuizRepository quizRepository, ModelMapper modelMapper, RestTemplate restTemplate, CategoryService categoryService, CategoryServiceImplFeignClient categoryServiceImplFeignClient) {
        this.quizRepository = quizRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
        this.categoryService = categoryService;
        this.categoryServiceImplFeignClient = categoryServiceImplFeignClient;
    }

    @Override
    public QuizDto create(QuizDto quizDto) {

        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        quiz.setId(UUID.randomUUID().toString());
        //validate category
        String url="lb://CATEGORY-SERVICE/api/v1/categories/"+ quizDto.getCategoryId();
        logger.info(url);

        CategoryDto categoryDto=restTemplate.getForObject(url,CategoryDto.class);
        logger.info("category exists :"+categoryDto.getTitle());

        Quiz saved = quizRepository.save(quiz);
        QuizDto quizDto1=modelMapper.map(saved,QuizDto.class);
        quizDto1.setCategoryDto(categoryDto);
        return quizDto1;
    }

    @Override
    public QuizDto update(String quizId, QuizDto quizDto) {

       Quiz quiz = quizRepository.findById(quizId).orElseThrow(()->new RuntimeException("Quiz not found"));

       quiz.setTitle(quizDto.getTitle());
       quiz.setDescription(quizDto.getDescription());
       quiz.setMaxMarks(quizDto.getMaxMarks());
       quiz.setTimeLimit(quizDto.getTimeLimit());
       quiz.setCreatedBy(quizDto.getCreatedBy());
       quiz.setNoOfQuestions(quizDto.getNoOfQuestions());
       quiz.setImageUrl(quizDto.getImageUrl());
       quiz.setLive(quizDto.getLive());
       quiz.setPassingMarks(quizDto.getPassingMarks());
       quiz.setCategoryId(quizDto.getCategoryId());

       Quiz saved = quizRepository.save(quiz);

        return modelMapper.map(saved, QuizDto.class);
    }

    @Override
    public void delete(String quizId) {

        Quiz quiz = quizRepository.findById(quizId).orElseThrow(()->new RuntimeException("Quiz not found"));
        quizRepository.delete(quiz);

    }

    @Override
    public QuizDto findById(String quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(()->new RuntimeException("Quiz not found"));
        QuizDto quizDto=modelMapper.map(quiz,QuizDto.class);

        String categoryId = quiz.getCategoryId();
        System.out.println(categoryId);
        String url="lb://CATEGORY-SERVICE/api/v1/categories/"+categoryId;
        logger.info(url);
//calling category service
        CategoryDto categoryDto = restTemplate.getForObject(url, CategoryDto.class);
        quizDto.setCategoryDto(categoryDto);


        return quizDto;
    }

    @Override
    public List<QuizDto> findAll() {
        logger.debug("Fetching");
        List<Quiz> all = quizRepository.findAll();

        if (all == null || all.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if no quizzes are found
        }

        List<QuizDto> quizDtoList = all.stream().map(quiz -> {
            String categoryId = quiz.getCategoryId();
            QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);

            CategoryDto categoryDto = this.categoryService.findById(categoryId);
            quizDto.setCategoryDto(categoryDto);
            return quizDto;
        }).toList();

        return quizDtoList;
    }




    @Override
    public List<QuizDto> findByCategory(String categoryId) {

        List<Quiz> quizList = quizRepository.findByCategoryId(categoryId);
        return  quizList.stream().map(quiz -> {
            QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
            //calling category service to get category and put into category dto

            CategoryDto categoryDto=null;
try{
    categoryDto = categoryServiceImplFeignClient.findById(quizDto.getCategoryId());
}
catch (FeignException.NotFound ex)
{
    logger.error("category not found");
}


            quizDto.setCategoryDto(categoryDto);
            return quizDto;

        }).toList();
    }
}
