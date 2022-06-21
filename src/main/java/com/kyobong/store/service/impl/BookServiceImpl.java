package com.kyobong.store.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kyobong.store.entity.Book;
import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.model.BookConverter;
import com.kyobong.store.model.BookDto;
import com.kyobong.store.repository.BookRepository;
import com.kyobong.store.service.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
	
	private final BookRepository repository;
	
	private final BookConverter bookConverter;

	@Override
	public List<BookDto> getBookList() {
		return repository.findAll().stream()
				.map(bookConverter::toDto).collect(Collectors.toList());
	}

	@Override
	public List<BookDto> getBookListByTitleAndWriter(String title, String writer) {
		return repository.getListByTitleAndWriter(title, writer).stream()
				.map(bookConverter::toDto).collect(Collectors.toList());
	}
	
	@Override
	public List<BookDto> getBookListStatusOk() {
		return repository.findByStatus(BookStatus.OK).stream()
				.map(bookConverter::toDto).collect(Collectors.toList());
	}

	@Override
	public BookDto save(Book book) {
		return bookConverter.toDto(repository.save(book));
	}

}
