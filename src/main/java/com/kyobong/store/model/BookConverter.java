package com.kyobong.store.model;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.stereotype.Component;

import com.kyobong.store.entity.Book;
import com.kyobong.store.enums.Category;

@Component
public class BookConverter {
	
	public BookDto toDto(Book entity) {
		BookDto dto = new BookDto(
				entity.getId(),
				entity.getTitle(),
				entity.getWriter(),
				new ArrayList<Category>(),
				entity.getStatus()
		);
		for (Category category: entity.getCategories()) {
			dto.getCategories().add(category);
		}
		return dto;
	}
	
	public Book toEntity(BookDto dto) {
		Book entity = new Book(
			dto.getId(),
			dto.getTitle(),
			dto.getWriter(),
			new HashSet<Category>(),
			dto.getStatus()
		);
		for (Category category: dto.getCategories()) {
			entity.getCategories().add(category);
		}
		return entity;		
	}
}
