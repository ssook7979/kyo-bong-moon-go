package com.kyobong.store.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.kyobong.store.enums.BookStatus;
import com.kyobong.store.enums.Category;
import com.kyobong.store.validation.group.OnCreate;

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
	
	@NotEmpty(message = "제목을 입력해주세요.", groups = OnCreate.class)
	@Length(min = 0, max = 100, message = "제목은 최대 100자를 넘을수 없습니다." )
	private String title;

	@NotEmpty(message = "지은이의 이름을 입력해주세요.", groups = OnCreate.class)
	@Length(min = 0, max = 20, message = "지은이의 이름은 최대 20자를 넘을수 없습니다." )
	private String writer;
	
	@NotNull(message = "카테고리를 입력해주세요.", groups = OnCreate.class)
	private List<Category> categories;
	
	@NotNull(message = "상태를 입력해주세요.", groups = OnCreate.class)
	private BookStatus status;

}
