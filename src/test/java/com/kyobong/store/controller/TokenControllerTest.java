package com.kyobong.store.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyobong.store.security.JwtTokenUtil;
import com.kyobong.store.security.JwtUserDetailsService;
import com.kyobong.store.security.model.JwtRequest;

@SpringBootTest
@AutoConfigureMockMvc
class TokenControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
	private AuthenticationManager authenticationManager;
    
    @Autowired
	private JwtTokenUtil tokenUtil;
    
    @Autowired
	private JwtUserDetailsService userDetailsService;
    
    private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void test_createTokenWithInvalidUser_returns422() throws Exception {
		String body = objectMapper.writeValueAsString(new JwtRequest("invalid", "password"));
		mockMvc
			.perform(post("/token").content(body).contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	void test_createTokenWithValidUser_returnsCreated() throws Exception {
		String body = objectMapper.writeValueAsString(new JwtRequest("testuser", "password"));
		mockMvc
			.perform(post("/token").content(body).contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isCreated());
	}

}
