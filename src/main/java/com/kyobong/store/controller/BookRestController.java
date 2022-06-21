package com.kyobong.store.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.enums.Category;
import com.kyobong.store.model.BookDto;
import com.kyobong.store.service.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class BookRestController {
	
	private final BookService service;

	@GetMapping(path = "books", produces = { "application/json" })
	public ResponseEntity<List<BookDto>> listBooks(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "writer", required = false) String writer,
			@RequestParam(value = "category", required = false) List<Category> categories,
			@RequestParam(value = "status", required = false) BookStatus status) {
		
		return new ResponseEntity<>(service.getBookList(), HttpStatus.OK);
	}
	
}
