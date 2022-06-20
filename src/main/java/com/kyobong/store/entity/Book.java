package com.kyobong.store.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import com.kyobong.store.enums.BootStatus;
import com.kyobong.store.enums.Category;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Entity
@Table(name = "TB_BOOK")
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book extends BaseModel {
	
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
	@Column(name="category", columnDefinition = "BIT(4)")
	private Set<Category> categories;
	
	@NotNull
	@Column(name="status", columnDefinition = "BIT(4)")
	private BootStatus status;

	@Builder
	public Book(Date createDate, Date modifiedDate, String registrator, String modifier, String title, String writer,
			Set<Category> categories, BootStatus status) {
		super(createDate, modifiedDate, registrator, modifier);
		this.title = title;
		this.writer = writer;
		this.categories = categories;
		this.status = status;
	}

}
