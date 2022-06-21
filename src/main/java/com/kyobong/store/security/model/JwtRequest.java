package com.kyobong.store.security.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8204317132367014043L;
	
	private String username;
	
	private String password;

}
