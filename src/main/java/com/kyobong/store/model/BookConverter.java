package com.kyobong.store.model;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.stereotype.Component;

import com.kyobong.store.entity.Book;
import com.kyobong.store.enums.Category;
import com.querydsl.core.util.StringUtils;

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
	
	public Book updateEntity(Book book, BookDto dto) {
		if (!StringUtils.isNullOrEmpty(dto.getTitle())) {
			book.setTitle(dto.getTitle());
		}
		if (!StringUtils.isNullOrEmpty(dto.getWriter())) {
			book.setWriter(dto.getWriter());
		}
		if (dto.getStatus() != null) {
			book.setStatus(dto.getStatus());
		}
		if (dto.getCategories() != null && dto.getCategories().size() > 0) {
			book.getCategories().clear();
			for (Category category: dto.getCategories()) {
				book.getCategories().add(category);
			}			
		}
		return book;
	}
}
