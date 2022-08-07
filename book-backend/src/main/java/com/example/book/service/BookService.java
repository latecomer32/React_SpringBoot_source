package com.example.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.domain.Book;
import com.example.book.domain.BookRepository;

import lombok.RequiredArgsConstructor;

//기능을 정의할 수 있고, 트랜잭션을 관리할 수 있음.

@RequiredArgsConstructor
@Service
public class BookService {

	private final BookRepository bookRepository;
	
	//org.springframework.transaction.annotation.Transactional
	@Transactional //서비스 함수가 종료될 때 commit할지 rollback할지 트랜잭션 관리하겠다.
	public Book 저장하기(Book book) {
		return bookRepository.save(book);
	}
	
	@Transactional(readOnly = true) //JPA 변경감지라는 내부 기능 활성화 X, update시의 정합성을 유지해줌. insert의 유령데이터현상(팬텀현상) 못 막아줌.
	public Book 한건가져오기(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(()->new IllegalArgumentException("id를 확인해 주세요!!"));
	}

//	 강의에선 아래를 위 처럼 간소화한거라는데 아래 코드를 쓰면 오류가 뜬다. 	
//	public Book 한건가져오기(Long id) {
//		return bookRepository.findById(id)
//				.orElseThrow(new Supplier<IllegalArgumentException>() {
//					
//					@Override
//					public IllegalArgumentException get() {
//						return new IllegalArgumentException("id를 확인해주세요!!");
//					}
//				});
//	}
	
	@Transactional(readOnly = true)
	public List<Book> 모두가져오기(){
		return bookRepository.findAll();
	}
	
	@Transactional
	public Book 수정하기(Long id, Book book) {
		//더티체킹 update치기
	
		Book bookEntity = bookRepository.findById(id)
				.orElseThrow(()->new IllegalArgumentException("id를 확인해 주세요!!")); //영속화 (book 오브젝트)->영속성 컨텍스트 보관
		bookEntity.setTitle(book.getTitle());
		bookEntity.setAuthor(book.getAuthor());
		return bookEntity;
	} //함수종료 => 트랜잭션 종료 => 영속화 되어있는 데이터를 DB로 갱신(flush) => commit ===> 더티체킹
	
	
	@Transactional
	public String 삭제하기(Long id) {
		bookRepository.deleteById(id); //오류가 터지면 익셉션을 타니깐 신경쓰지 말
		return "ok";
	}
}
