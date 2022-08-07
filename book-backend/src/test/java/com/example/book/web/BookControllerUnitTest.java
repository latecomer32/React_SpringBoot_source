package com.example.book.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.book.service.BookService;

import lombok.extern.slf4j.Slf4j;

//단위 테스트 (Filter, ControllerAdvice등은 메모리에 뜬다. controller관련 로직 뜸.)

@Slf4j
@WebMvcTest
public class BookControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean //IoC 환경에 bean 등록됨.
	private BookService bookService;
	
	@Test
	public void save_테스트() {
		log.info("save_테스트() 시작 ===========");
	}
}
