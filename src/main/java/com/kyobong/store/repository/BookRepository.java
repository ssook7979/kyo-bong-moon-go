package com.kyobong.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kyobong.store.entity.Book;
import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.repository.querydsl.BookRepositoryCustom;

public interface BookRepository extends JpaRepository<Book, Integer>, BookRepositoryCustom {

	public Page<Book> findByStatus(BookStatus status, Pageable pageable);
	
}
