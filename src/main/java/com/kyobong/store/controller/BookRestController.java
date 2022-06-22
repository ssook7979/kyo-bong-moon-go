package com.kyobong.store.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RequestMapping("books")
@RestController
public class BookRestController {
	
	private final BookService service;

	@Operation(summary = "get book list")
	@GetMapping(path = "", produces = { "application/json" })
	public List<BookDto> listBooks(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "writer", required = false) String writer,
			@RequestParam(value = "category", required = false) Category[] categories,
			@RequestParam(value = "status", required = false, defaultValue = "이용가능") BookStatus[] statuses,
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
	@GetMapping(path = "{id}", produces = { "application/json" })
	public BookDto book(@PathVariable(value = "id") Integer id) {
		return service.getOne(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "", produces = { "application/json" })
	public BookDto create(@Validated(OnCreate.class) @RequestBody BookDto dto) {
		return service.save(dto);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(path = "{id}", produces = { "application/json" })
	public BookDto update(@PathVariable(name = "id", required = true) Integer id, @Valid@RequestBody BookDto dto) {
		return service.update(id, dto);
	}

}
