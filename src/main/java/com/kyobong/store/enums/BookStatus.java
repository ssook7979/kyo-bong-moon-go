package com.kyobong.store.enums;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

@Getter
public enum BookStatus {
	
	OK(0, "이용가능"), DAMAGED(1, "훼손"), LOST(2, "분실");

	private Integer value;
	@JsonValue
	private String displayName;
	
	private BookStatus(int value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}
	
	public static List<String> getAllDisplayNames() {
		List<String> names = new ArrayList<>();
		for (BookStatus status: BookStatus.values()) {
			names.add(status.displayName);
		}
		return names;
	}
	
	public static BookStatus getByDisplayName(String displayName) {
		for (BookStatus status: BookStatus.values()) {
			if (status.displayName.equals(displayName)) {
				return status;
			}
		}
		throw new IllegalArgumentException("The values accepted for this field is " + getAllDisplayNames());
	}
	
}
