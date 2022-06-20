package com.kyobong.store.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;

import java.util.List;

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
			repo.save(Book.builder()
				.title("test" + i)
				.category(Category.LIBERAL_ARTS)
				.status(BookStatus.OK)
				.writer("park")
				.build()
			);
			
		}
		repo.save(Book.builder()
			.title("prod")
			.category(Category.LIBERAL_ARTS)
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
			repo.save(Book.builder()
				.title("test")
				.category(Category.LIBERAL_ARTS)
				.status(BookStatus.OK)
				.writer("park" + i)
				.build()
			);
		}
		repo.save(Book.builder()
			.title("test")
			.category(Category.LIBERAL_ARTS)
			.status(BookStatus.OK)
			.writer("kim")
			.build()
		);
		
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
	
	@Test
	void test_searchByTitleAndWriter_returnsListContainingKeywords() {
		for (int i = 0; i < 5; i++) {
			repo.save(Book.builder()
				.title("test" + i)
				.category(Category.LIBERAL_ARTS)
				.status(BookStatus.OK)
				.writer("park")
				.build()
			);
		}
		for (int i = 0; i < 5; i++) {
			repo.save(Book.builder()
				.title("test" + i)
				.category(Category.LIBERAL_ARTS)
				.status(BookStatus.OK)
				.writer("kim")
				.build()
			);
		}
		
		List<Book> books = repo.getListByTitleAndWriter("test", "park");
		
		org.hamcrest.MatcherAssert.assertThat(
				books,
				allOf(
					hasItem(Matchers.<Book>hasProperty("title", containsString("test"))),
					hasItem(Matchers.<Book>hasProperty("writer", containsString("park")))
				)
		);
		assertThat(books.size()).isEqualTo(5);
	}
	
	@ParameterizedTest
	@EnumSource(value = BookStatus.class)
	void test_getListWithCentainStatus_returnsListContainingOnlyThatStatus(BookStatus status) {
		for (int i = 0; i < 3; i++) {
			repo.save(Book.builder()
				.title("test")
				.category(Category.LIBERAL_ARTS)
				.status(status)
				.writer("park" + i)
				.build()
			);
		}
		for (BookStatus otherStatus: BookStatus.values()) {
			if (otherStatus != status) {
				repo.save(Book.builder()
					.title("test")
					.category(Category.LIBERAL_ARTS)
					.status(otherStatus)
					.writer("kim")
					.build()
				);
				break;
			}
		}
		
		List<Book> books = repo.getList(null, null, null, Arrays.array(status));
		
		assertThat(books.size()).isEqualTo(3);
	}

}
