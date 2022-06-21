package com.kyobong.store.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		Set<Category> categories = new HashSet<>();
		categories.add(Category.ECONOMY);
		books = new ArrayList<>();
		books.add(Book.builder().categories(categories).title("title").writer("writer").build());
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
