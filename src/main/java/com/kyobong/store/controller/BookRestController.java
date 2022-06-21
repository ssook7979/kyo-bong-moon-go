package com.kyobong.store.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.enums.Category;
import com.kyobong.store.model.BookDto;
import com.kyobong.store.service.BookService;
import com.kyobong.store.validation.group.OnCreate;

import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class BookRestController {
	
	private final BookService service;

	@GetMapping(path = "books", produces = { "application/json" })
	public List<BookDto> listBooks(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "writer", required = false) String writer,
			@RequestParam(value = "category", required = false) Category[] categories,
			@RequestParam(value = "status", required = false) BookStatus[] statuses,
			@RequestParam(value = "page-number", required = false, defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "page-size", required = false, defaultValue = "10") Integer pageSize) {
		
		return service.getBookList(
				title,
				writer,
				categories,
				statuses,
				PageRequest.of(pageNumber - 1, pageSize)
		);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "books/{id}", produces = { "application/json" })
	public BookDto book(@PathVariable(value = "id") Integer id) {
		return service.getOne(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@Validated(OnCreate.class)
	@PostMapping(path = "books", produces = { "application/json" })
	public BookDto create(@Valid@RequestBody BookDto dto) {
		return service.save(dto);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(path = "books/{id}", produces = { "application/json" })
	public BookDto update(@PathVariable(name = "id", required = true) Integer id, @Valid@RequestBody BookDto dto) {
		return service.update(id, dto);
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NoSuchElementException.class })
	public String handleNoSuchElementException(NoSuchElementException e) {
		return e.getMessage();
		
	}
	
}
