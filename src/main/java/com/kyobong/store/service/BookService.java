package com.kyobong.store.service;

import java.util.List;

import com.kyobong.store.entity.Book;

public interface BookService {
	
	public List<Book> getBookList();
	
	public List<Book> getBookListByTitleAndWriter(String title, String writer);
	
	public List<Book> getBookListStatusOk();
	
	public Book save(Book book);

}
