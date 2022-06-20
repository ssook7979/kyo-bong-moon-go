package com.kyobong.store.enums;

import lombok.Getter;

@Getter
public enum Category {
	
	LITERATURE(0, "문학"), LIBERAL_ARTS(1, "인문학"), IT(2, "IT"), ECONOMY(3, "경제경영"), SCIENCE(4, "과학");

	private Integer value;
	
	private String displayName;
	
	private Category(int value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}
	
}
