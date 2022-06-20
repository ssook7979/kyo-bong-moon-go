package com.kyobong.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyobong.store.entity.Book;

public interface BookRepository extends JpaRepository<Integer, Book> {

}
