package com.microservice.quiz;

import com.microservice.quiz.dto.CategoryDto;
import com.microservice.quiz.services.CategoryServiceFeignClient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
class QuizServiceApplicationTests {
	@Autowired
	private CategoryServiceFeignClient categoryServiceFeignClient;

	@Test
	public void testFeignAllCategories()
	{
		System.out.println("Getting All Categories");
		List<CategoryDto> all = categoryServiceFeignClient.findAll();

		all.forEach(categoryDto -> System.out.println(categoryDto.getTitle()));

		//Assertions.assertEquals(4,all.size());
		Assertions.assertNotNull(all);

	}

	@Test
	public void testFeignSingleCategory()
	{
		System.out.println("Getting single category");

		CategoryDto categoryDto = categoryServiceFeignClient.findById("e4784890-052c-4c5f-b13e-7cc7377cab4c-+");
		System.out.println(categoryDto.getTitle());
		Assertions.assertNotNull(categoryDto);
	}

}
