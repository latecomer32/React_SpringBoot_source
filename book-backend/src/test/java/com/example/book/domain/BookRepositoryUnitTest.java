package com.example.book.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureTestDatabase(replace = Replace.ANY)//가짜 db로 테스트. Replace.NONE 실제 DB로 테스
@DataJpaTest //Repository들을 다 IoC 등록해둠.
public class BookRepositoryUnitTest { 
	
	@Autowired
	private BookRepository bookRepository;
}
