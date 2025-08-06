package com.microservice.quiz.dto;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {

    private String id;
    private String title;
    private String description;
    private Integer maxMarks;
    private Integer timeLimit;
    private String createdBy;
    private Integer noOfQuestions;
    private String imageUrl;
    private Boolean live;
    private Integer passingMarks;

    private String categoryId;

    private CategoryDto categoryDto;
}
