package com.kyobong.store.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kyobong.store.security.JwtTokenUtil;
import com.kyobong.store.security.JwtUserDetailsService;
import com.kyobong.store.security.model.JwtRequest;
import com.kyobong.store.security.model.JwtResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TokenController {
	
	private final AuthenticationManager authenticationManager;
	
	private final JwtTokenUtil tokenUtil;
	
	private final JwtUserDetailsService userDetailsService;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public JwtResponse createToken(@RequestBody JwtRequest request) {
		String username = request.getUsername();
		String password = request.getPassword();
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		final String token = tokenUtil.generateToken(userDetails);
		return new JwtResponse(token);
		
	}

}
