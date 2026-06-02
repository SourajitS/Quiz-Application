package com.microservice.quiz;

import com.microservice.quiz.collections.Quiz;
import com.microservice.quiz.dto.CategoryDto;
import com.microservice.quiz.repositories.QuizRepository;
import com.microservice.quiz.services.CategoryServiceFeignClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient

public class QuizServiceApplication implements CommandLineRunner {

	@Autowired
	private QuizRepository quizRepository;



	public static void main(String[] args) {
//		WebClient webClient = WebClient.create("http://127.0.0.1:9092");
//		String categoryId = "67409a80-d978-4bb7-a106-9599d974bcc5";
//		try {
//			String response = webClient.get()
//					.uri("/api/v1/categories/{categoryId}", categoryId)
//					.retrieve()
//					.bodyToMono(String.class)
//					.block();
//			System.out.println(response);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		SpringApplication.run(QuizServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//	Quiz quiz=	Quiz.builder()
//				.id(UUID.randomUUID().toString())
//				.title("Python")
//				.description("Python Basics Quiz")
//				.maxMarks(100)
//				.timeLimit(30)
//				.createdBy("Durgesh")
//				.noOfQuestions(10)
//				.imageUrl("")
//				.live(true)
//				.passingMarks(30)
//				.build();
//
//		Quiz saved = quizRepository.save(quiz);
//		System.out.println("Quiz saved: " + saved.getId());
	}
}
