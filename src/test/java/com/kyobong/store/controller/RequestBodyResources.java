package com.kyobong.store.controller;

import java.util.Collections;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyobong.store.entity.Book;
import com.kyobong.store.enums.BookStatus;

public abstract class RequestBodyResources {
	
	public static Stream<Arguments> getBooksForCreateRequest() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		final String book1 = objectMapper
				.writeValueAsString(Book.builder().build());
		final String book2 = objectMapper
				.writeValueAsString(Book.builder().title("test").build());
		final String book3 = objectMapper
				.writeValueAsString(Book.builder().title("test").writer("writer").build());
		final String book4 = objectMapper
				.writeValueAsString(Book.builder().title("test").writer("writer").status(BookStatus.DAMAGED).build());
		final String book5 = objectMapper
				.writeValueAsString(Book.builder().categories(Collections.emptySet()).build());
		return Stream.of(
				// object, title, writer, status, categories
				Arguments.of(book1, true, true, true, true),
				Arguments.of(book2, false, true, true, true),
				Arguments.of(book3, false, false, true, true),
				Arguments.of(book4, false, false, false, true),
				Arguments.of(book5, true, true, true, true)
		);
	}

}
