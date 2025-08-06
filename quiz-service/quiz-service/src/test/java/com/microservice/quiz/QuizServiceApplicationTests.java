package com.microservice.quiz;

import com.microservice.quiz.dto.CategoryDto;
import com.microservice.quiz.services.CategoryServiceImplFeignClient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
class QuizServiceApplicationTests {
//	@Autowired
//	private CategoryServiceImplFeignClient categoryServiceimplFeignClient;
//
//	@Test
//	public void testFeignAllCategories()
//	{
//		System.out.println("Getting All Categories");
//		List<CategoryDto> all = categoryServiceimplFeignClient.findAll();
//
//		all.forEach(categoryDto -> System.out.println(categoryDto.getTitle()));
//
//		//Assertions.assertEquals(4,all.size());
//		Assertions.assertNotNull(all);
//
//	}
//
//	@Test
//	public void testFeignSingleCategory()
//	{
//		System.out.println("Getting single category");
//
//		CategoryDto categoryDto = categoryServiceimplFeignClient.findById("67409a80-d978-4bb7-a106-9599d974bcc5");
//		System.out.println(categoryDto.getTitle());
//		Assertions.assertNotNull(categoryDto);
//	}

}
