package com.kyobong.store.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BaseModel {
	
	protected Date createDate;
	
	protected Date modifiedDate;
	
	protected String registrator;
	
	protected String modifier;

	public Date getCreateDate() {
		return createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public String getRegistrator() {
		return registrator;
	}

	public String getModifier() {
		return modifier;
	}
	
	

}
