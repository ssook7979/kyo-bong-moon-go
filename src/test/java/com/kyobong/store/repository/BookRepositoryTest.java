package com.kyobong.store.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.kyobong.store.config.DataJpaTestConfig;
import com.kyobong.store.entity.Book;
import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.enums.Category;

import io.jsonwebtoken.lang.Collections;

@Import({DataJpaTestConfig.class})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@WithMockUser
@DataJpaTest
class BookRepositoryTest {

	@Autowired
	private BookRepository repo;
	
	@BeforeEach
	void setUp() {
	}
	
	@Test
	void test_searchByTitle_returnsListContainingKeyword() {
		for (int i = 0; i < 10; i++) {
			Set<Category> categories = new HashSet<>();
			categories.add(Category.LIBERAL_ARTS);
			repo.save(
				Book.builder()
					.title("test" + i)
					.categories(categories)
					.status(BookStatus.OK)
					.writer("park")
					.build()
			);
		}
		
		Set<Category> categories = new HashSet<>();
		categories.add(Category.LIBERAL_ARTS);
		repo.save(Book.builder()
			.title("prod")
			.categories(categories)
			.status(BookStatus.OK)
			.writer("park")
			.build()
		);
		
		List<Book> books = repo.getList("te", null, null, null);
		
		org.hamcrest.MatcherAssert.assertThat(
			books,
			allOf(
				hasItem(Matchers.<Book>hasProperty("title", containsString("te")))
			)
		);
		assertThat(books.size()).isEqualTo(10);
		
	}
	
	@Test
	void test_searchByWriter_returnsListContainingKeyword() {
		for (int i = 0; i < 5; i++) {
			Set<Category> categories = new HashSet<>();
			categories.add(Category.LIBERAL_ARTS);
			Book book1 = Book.builder()
				.title("test")
				.status(BookStatus.OK)
				.categories(categories)
				.writer("park" + i)
				.build();
			repo.save(book1);
		}
		Set<Category> categories = new HashSet<>();
		categories.add(Category.LIBERAL_ARTS);
		Book book2 = Book.builder()
			.title("test")
			.categories(categories)
			.status(BookStatus.OK)
			.writer("kim")
			.build();
		repo.save(book2);
		
		List<Book> books = repo.getList("te", "pa", null, null);
		
		org.hamcrest.MatcherAssert.assertThat(
			books,
			allOf(
				hasItem(Matchers.<Book>hasProperty("title", containsString("te"))),
				hasItem(Matchers.<Book>hasProperty("writer", containsString("pa")))
			)
		);
		assertThat(books.size()).isEqualTo(5);
	}
	
	@ParameterizedTest
	@EnumSource(value = BookStatus.class)
	void test_getListWithCentainStatus_returnsListContainingOnlyThatStatus(BookStatus status) {
		for (int i = 0; i < 3; i++) {
			Set<Category> categories = new HashSet<>();
			categories.add(Category.LIBERAL_ARTS);
			Book book = Book.builder()
				.title("test")
				.status(status)
				.categories(categories)
				.writer("park" + i)
				.build();
			repo.save(book);
		}
		for (BookStatus otherStatus: BookStatus.values()) {
			if (otherStatus != status) {
				Set<Category> categories = new HashSet<>();
				categories.add(Category.LIBERAL_ARTS);
				Book book = Book.builder()
					.title("test")
					.status(otherStatus)
					.categories(categories)
					.writer("kim")
					.build();
				repo.save(book);
				break;
			}
		}
		
		List<Book> books = repo.getList(null, null, null, Arrays.array(status));
		
		assertThat(books.size()).isEqualTo(3);
	}
	
	@Test
	void test_saveBookOfExistingId_returnsBookUpdatedWithUserInput() {
		Set<Category> categories = new HashSet<>();
		categories.add(Category.LIBERAL_ARTS);
		repo.save(
			Book.builder()
				.title("book before test")
				.categories(categories)
				.status(BookStatus.OK)
				.writer("park")
				.build()
		);
		
		Book book = repo.getReferenceById(1);
		book.setTitle("updated book");
		book.getCategories().add(Category.IT);
		
		Book updated = repo.getReferenceById(1);
		
		assertThat(updated.getTitle()).isEqualTo("updated book");
		
		assertThat(updated.getStatus()).isEqualTo(BookStatus.OK);
		assertThat(updated.getWriter()).isEqualTo("park");
		assertThat(updated.getCategories()).containsAll(
				Collections.arrayToList(Arrays.array(Category.LIBERAL_ARTS, Category.IT)));
		
	}

}
