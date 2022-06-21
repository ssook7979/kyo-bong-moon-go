package com.kyobong.store.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyobong.store.entity.Book;
import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.repository.BookRepository;
import com.kyobong.store.service.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
	
	private final BookRepository repository;

	@Override
	public List<Book> getBookList() {
		return repository.findAll();
	}

	@Override
	public List<Book> getBookListByTitleAndWriter(String title, String writer) {
		return repository.getListByTitleAndWriter(title, writer);
	}
	
	@Override
	public List<Book> getBookListStatusOk() {
		return repository.findByStatus(BookStatus.OK);
	}

}
