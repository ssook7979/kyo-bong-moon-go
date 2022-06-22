package com.kyobong.store.bootstrap;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kyobong.store.entity.Book;
import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.enums.Category;
import com.kyobong.store.model.BookDto;
import com.kyobong.store.repository.BookRepository;
import com.kyobong.store.service.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BookDataLoader implements CommandLineRunner {
	
	private final BookRepository repo;
	
	@Override
	public void run(String... args) throws Exception {
		try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"), 16 * 1024)) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] inputs = line.split(",");
				Book book = Book.builder()
						.title(inputs[1])
						.writer(inputs[2])
						.status(BookStatus.OK)
						.build();
				book.getCategories().add(Category.getByDisplayName(inputs[0]));
				repo.save(book);
			}
			
		}
	}

}
