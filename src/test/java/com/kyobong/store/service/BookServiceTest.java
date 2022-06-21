package com.kyobong.store.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kyobong.store.entity.Book;
import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.enums.Category;
import com.kyobong.store.repository.BookRepository;
import com.kyobong.store.service.impl.BookServiceImpl;

@SpringBootTest(classes = { BookServiceImpl.class })
class BookServiceTest {
	
	@Autowired
	BookService service;
	
	@MockBean
	private BookRepository repository;
	
	List<Book> books;
	
	@BeforeEach
	void setUp() {
		books = new ArrayList<>();
		books.add(Book.builder().category(Category.ECONOMY).title("title").writer("writer").build());
	}
	

	@Test
	void testGetBookList() {
		when(repository.findAll()).thenReturn(books);
		
		service.getBookList();
		
		verify(repository, times(1)).findAll();
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	void testGetBookListByTitleAndWriter() {
		when(repository.getListByTitleAndWriter(any(), any())).thenReturn(books);
		
		service.getBookListByTitleAndWriter("title", "writer");
		
		verify(repository, times(1)).getListByTitleAndWriter(any(), any());
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	void testGetBookListStatusOk() {
		when(repository.findByStatus(BookStatus.OK)).thenReturn(books);
		
		service.getBookListStatusOk();
		
		verify(repository, times(1)).findByStatus(BookStatus.OK);
		verifyNoMoreInteractions(repository);		
	}
	
	@Test
	void testSave() {
		when(repository.save(any())).thenReturn(books.get(0));
		
		service.save(books.get(0));
		
		verify(repository, times(1)).save(books.get(0));
		verifyNoMoreInteractions(repository);		
	}

}
