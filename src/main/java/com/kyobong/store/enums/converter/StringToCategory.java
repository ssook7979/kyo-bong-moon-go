package com.kyobong.store.enums.converter;

import org.springframework.core.convert.converter.Converter;

import com.kyobong.store.enums.Category;

public class StringToCategory implements Converter<String, Category> {

	@Override
	public Category convert(String source) {
		return Category.getByDisplayName(source);
	}

}
