package com.kyobong.store.repository.querydsl;

import java.util.List;

import com.kyobong.store.entity.Book;
import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.enums.Category;

public interface BookRepositoryCustom {
	
	public List<Book> getList(String title, String writer, Category[] categories, BookStatus[] status);
	
	public List<Book> getListByTitleAndWriter(String title, String writer);

}
