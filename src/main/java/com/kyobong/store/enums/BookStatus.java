package com.kyobong.store.enums;

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
	
	public static BookStatus getByDisplayName(String displayName) {
		for (BookStatus status: BookStatus.values()) {
			if (status.displayName.equals(displayName)) {
				return status;
			}
		}
		return null;
	}
	
}
