package com.microservice.quiz.services;

import com.microservice.quiz.dto.QuizDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {

    QuizDto create(QuizDto quizDto);
    QuizDto update(String quizId, QuizDto quizDto);

    void delete(String quizId);
    QuizDto findById(String quizId);
    List<QuizDto> findAll();

    List<QuizDto> findByCategory(String categoryId);

}
