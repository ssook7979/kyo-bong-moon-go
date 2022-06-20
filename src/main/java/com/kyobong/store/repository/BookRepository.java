package com.kyobong.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyobong.store.entity.Book;
import com.kyobong.store.repository.querydsl.BookRepositoryCustom;

public interface BookRepository extends JpaRepository<Book, Integer>, BookRepositoryCustom {

}
