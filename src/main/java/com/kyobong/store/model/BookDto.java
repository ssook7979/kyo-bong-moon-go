package com.kyobong.store.model;

import java.util.List;

import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto {
	
	private Integer id;
	
	private String title;
	
	private String writer;
	
	private List<Category> categories;
	
	private BookStatus status;

}
