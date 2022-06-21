package com.kyobong.store.service;

import java.util.List;

import com.kyobong.store.entity.Book;
import com.kyobong.store.model.BookDto;

public interface BookService {
	
	public List<BookDto> getBookList();
	
	public List<BookDto> getBookListByTitleAndWriter(String title, String writer);
	
	public List<BookDto> getBookListStatusOk();
	
	public BookDto save(Book book);

}
