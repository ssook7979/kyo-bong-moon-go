package com.kyobong.store.repository.querydsl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
	
	private long count(String title, String writer, Category[] categories, BookStatus[] status) {
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
		return query.fetch().size();
	}

	@Override
	public Page<Book> getListAsPage(String title, String writer, Category[] categories, BookStatus[] status,
			Pageable pageable) {
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
		if (pageable != null) {
			query = query.offset(pageable.getOffset()).limit(pageable.getPageSize());
		}
		return new PageImpl<>(query.fetch(), pageable, count(title, writer, categories, status));
	}

	@Override
	public Page<Book> getListAsPage(Pageable pageable) {
		return getListAsPage(null, null, null, null, pageable);
	}

	
}
