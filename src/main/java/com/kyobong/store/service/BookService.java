package com.kyobong.store.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.enums.Category;
import com.kyobong.store.model.BookDto;

public interface BookService {
	
	public List<BookDto> getBookList(Pageable pageable);
	
	public List<BookDto> getBookList(String title, String writer, Category[] categories, BookStatus[] statuses, Pageable pageable);
	
	public BookDto save(BookDto book);
	
	public BookDto getOne(Integer id);
	
	public BookDto update(Integer id, BookDto book);

}
