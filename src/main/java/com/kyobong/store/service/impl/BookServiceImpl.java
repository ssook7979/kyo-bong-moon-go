package com.kyobong.store.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kyobong.store.entity.Book;
import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.enums.Category;
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
	public List<BookDto> getBookList(Pageable pageable) {
		return repository.findAll(pageable).getContent().stream()
				.map(bookConverter::toDto).collect(Collectors.toList());
	}
	
	@Override
	public List<BookDto> getBookListStatusOk(Pageable pageable) {
		return repository.findByStatus(BookStatus.OK, pageable).stream()
				.map(bookConverter::toDto).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public BookDto save(BookDto dto) {
		return bookConverter.toDto(repository.save(bookConverter.toEntity(dto)));
	}

}
