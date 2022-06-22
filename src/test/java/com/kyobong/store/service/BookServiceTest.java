package com.kyobong.store.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.kyobong.store.entity.Book;
import com.kyobong.store.enums.Category;
import com.kyobong.store.model.BookConverter;
import com.kyobong.store.model.BookDto;
import com.kyobong.store.repository.BookRepository;
import com.kyobong.store.service.impl.BookServiceImpl;

@SpringBootTest(classes = { BookServiceImpl.class })
class BookServiceTest {
	
	@Autowired
	BookService service;
	
	@MockBean
	private BookRepository repository;
	
	@MockBean
	private BookConverter converter;
	
	Page<Book> books;
	
	Book book;
	
	BookDto dto;
	
	@BeforeEach
	void setUp() {
		Set<Category> categories = new HashSet<>();
		categories.add(Category.ECONOMY);
		books = new PageImpl<>(Collections.singletonList(
				Book.builder().categories(categories).title("title").writer("writer").build()));
		book = Book.builder().categories(categories).title("title").writer("writer").build();
		dto = BookDto.builder().category(Category.ECONOMY).title("title").writer("writer").build();
	}
	

	@Test
	void testGetBookList() {
		PageRequest pageable = PageRequest.of(1, 10);
		when(repository.findAll(pageable)).thenReturn(books);
		
		service.getBookList(pageable);
		
		verify(repository, times(1)).findAll(pageable);
		verifyNoMoreInteractions(repository);
	}

	@Test
	void testSave() {
		when(repository.save(any())).thenReturn(book);
		when(converter.toEntity(any())).thenReturn(book);
		
		service.save(dto);
		
		verify(repository, times(1)).save(book);
		verifyNoMoreInteractions(repository);		
	}

}
