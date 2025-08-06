package com.microservice.quiz.repositories;

import com.microservice.quiz.collections.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuizRepository extends MongoRepository<Quiz,String> {

    List<Quiz> findByTitle();
    List<Quiz> findByCategoryId(String categoryId);
}
