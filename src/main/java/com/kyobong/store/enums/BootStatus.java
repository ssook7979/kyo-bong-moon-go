package com.kyobong.store.enums;

import lombok.Getter;

@Getter
public enum BootStatus {
	
	OK(0, "이용가능"), DAMAGED(1, "훼손"), LOST(2, "분실");

	private Integer value;
	
	private String displayName;
	
	private BootStatus(int value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}
	
}
