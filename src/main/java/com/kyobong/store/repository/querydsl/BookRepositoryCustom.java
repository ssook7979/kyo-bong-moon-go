package com.kyobong.store.repository.querydsl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kyobong.store.entity.Book;
import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.enums.Category;

public interface BookRepositoryCustom {
	
	public List<Book> getList(String title, String writer, Category[] categories, BookStatus[] statuses);
	
	public Page<Book> getListAsPage(String title, String writer, Category[] categories, BookStatus[] statuses, Pageable pageable);

	public Page<Book> getListAsPage(Pageable pageable);

}
