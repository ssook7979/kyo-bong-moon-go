package com.kyobong.store.repository.querydsl;

import java.util.List;

import com.kyobong.store.entity.Book;
import com.kyobong.store.entity.QBook;
import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.enums.Category;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {
	
	private final QBook book = QBook.book;
	
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Book> getList(String title, String writer, Category[] categories, BookStatus[] status) {
		JPAQuery<Book> query = queryFactory.selectFrom(book);
		if (!StringUtils.isNullOrEmpty(title)) {
			query = query.where(book.title.containsIgnoreCase(title));
		}
		if (!StringUtils.isNullOrEmpty(writer)) {
			query = query.where(book.writer.containsIgnoreCase(writer));
		}
		if (categories != null) {
			query = query.where(book.categories.any().in(categories));
		}
		if (status != null) {
			query = query.where(book.status.in(status));
		}
		return query.fetch();
	}

	@Override
	public List<Book> getListByTitleAndWriter(String title, String writer) {
		return queryFactory.selectFrom(book)
				.where(
					book.title.containsIgnoreCase(title)
					.and(book.writer.containsIgnoreCase(writer))
				)
				.fetch();
	}
	
}
