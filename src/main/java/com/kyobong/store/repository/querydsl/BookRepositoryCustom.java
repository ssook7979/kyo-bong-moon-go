package com.kyobong.store.repository.querydsl;

import java.util.List;
import java.util.Set;

import com.kyobong.store.entity.Book;
import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.enums.Category;

public interface BookRepositoryCustom {
	
	List<Book> getList(String title, String writer, Category[] categories, BookStatus[] status);

}
