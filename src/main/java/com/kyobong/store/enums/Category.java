package com.kyobong.store.enums;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

@Getter
public enum Category {
	
	LITERATURE(0, "문학"), LIBERAL_ARTS(1, "인문학"), IT(2, "IT"), ECONOMY(3, "경제경영"), SCIENCE(4, "과학");

	private Integer value;
	//@JsonValue
	private String displayName;
	
	private Category(int value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}
	
	public static List<String> getAllDisplayNames() {
		List<String> names = new ArrayList<>();
		for (Category category: Category.values()) {
			names.add(category.displayName);
		}
		return names;
	}
	
	public static Category getByDisplayName(String displayName) {
		for (Category category: Category.values()) {
			if (category.displayName.equals(displayName)) {
				return category;
			}
		}
		throw new IllegalArgumentException(
				"The values accepted for this field is " + getAllDisplayNames());
	}
	
}
