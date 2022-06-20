package com.kyobong.store.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.enums.Category;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Table(name = "TB_BOOK")
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book extends BaseEntity {
	
	@Id
	@Setter(AccessLevel.NONE)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "제목을 입력해주세요.")
	@Length(min = 0, max = 100, message = "제목은 최대 100자를 넘을수 없습니다." )
	@Column(name = "title", length = 100, nullable=false)
	private String title;

	@NotEmpty(message = "지은이의 이름을 입력해주세요.")
	@Length(min = 0, max = 20, message = "지은이의 이름은 최대 20자를 넘을수 없습니다." )
	@Column(name = "writer", length = 20, nullable=false)
	private String writer;
	
	@NotNull
	@Singular(value = "category")
	@ElementCollection(targetClass=Category.class)
	@Enumerated(EnumType.ORDINAL)
	@CollectionTable(name="book_category")
	@Column(name="category")
	private Set<Category> categories = new HashSet<>();
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name="status")
	private BookStatus status;

}
