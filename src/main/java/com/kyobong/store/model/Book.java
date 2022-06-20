package com.kyobong.store.model;

import java.util.Date;
import java.util.Set;

import com.kyobong.store.annotation.Default;
import com.kyobong.store.enums.BootStatus;
import com.kyobong.store.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book extends BaseModel {
	
	private String name;
		
	private String writer;
	
	@Singular(value = "category")
	private Set<Category> categories;
	
	private BootStatus status;

	@Default
	public Book(Date createDate, Date modifiedDate, String registrator, String modifier, String name, String writer,
			Set<Category> categories, BootStatus status) {
		super(createDate, modifiedDate, registrator, modifier);
		this.name = name;
		this.writer = writer;
		this.categories = categories;
		this.status = status;
	}

}
