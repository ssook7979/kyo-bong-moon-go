package com.kyobong.store.enums.converter;

import org.springframework.core.convert.converter.Converter;

import com.kyobong.store.enums.BookStatus;

public class StringToBookStatus implements Converter<String, BookStatus>{

	@Override
	public BookStatus convert(String source) {
		return BookStatus.getByDisplayName(source);
	}

}
