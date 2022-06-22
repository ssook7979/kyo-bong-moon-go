package com.kyobong.store.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import com.kyobong.store.security.JwtTokenUtil;
import com.kyobong.store.service.BookService;
import com.kyobong.store.utils.TestUserDetails;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class BookRestControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;
    
    @Autowired
    private JwtTokenUtil tokenUtil;

    @Test
    public void test_callMethodWithoutToken_returns401() throws Exception {
        mockMvc
            .perform(get("/api/v1/books"))
            .andExpect(status().isUnauthorized());
            
    }
    
    @Test
    public void test_callMethodWithTokenOfUserNotValid_returns401() throws Exception {
    	String token = tokenUtil.generateToken(new TestUserDetails("notexist"));
    	mockMvc
    		.perform(get("/api/v1/books").header("Authorization", "Bearer " + token))
    		.andDo(print())
    		.andExpect(status().isUnauthorized());
    }
    
    @Test
    public void test_callMethodWithInvalidToken_returns401() throws Exception {
    	mockMvc
    	.perform(get("/api/v1/books").header("Authorization", "Bearer " + "I'mToken"))
    	.andDo(print())
    	.andExpect(status().isUnauthorized());
    }
    
    @Test
    public void test_callBookListMethod_returnsOk() throws Exception {
    	String token = tokenUtil.generateToken(new TestUserDetails("notexist"));
    	mockMvc
    	.perform(get("/api/v1/books").header("Authorization", "Bearer " + token))
    	.andDo(print())
    	.andExpect(status().isUnauthorized());
    }

    
}
