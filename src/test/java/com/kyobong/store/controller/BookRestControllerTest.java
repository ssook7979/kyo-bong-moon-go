package com.kyobong.store.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyobong.store.entity.Book;
import com.kyobong.store.security.JwtTokenUtil;
import com.kyobong.store.service.BookService;
import com.kyobong.store.utils.TestUserDetails;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class BookRestControllerTest extends RequestBodyResources {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;
    
    @Autowired
    private JwtTokenUtil tokenUtil;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private String validToken;
    
    @BeforeEach
    public void setUp() {
    	validToken = tokenUtil.generateToken(new TestUserDetails("testuser"));
    }

    @Test
    public void test_callMethodWithoutToken_returns401() throws Exception {
        mockMvc
            .perform(get("/books"))
            .andExpect(status().isUnauthorized());
            
    }
    
    @Test
    public void test_callMethodWithTokenOfUserNotValid_returns401() throws Exception {
    	String token = tokenUtil.generateToken(new TestUserDetails("notexist"));
    	mockMvc
    		.perform(get("/books").header("Authorization", "Bearer " + token))
    		.andDo(print())
    		.andExpect(status().isUnauthorized());
    }
    
    @Test
    public void test_callMethodWithInvalidToken_returns401() throws Exception {
    	mockMvc
	    	.perform(get("/books").header("Authorization", "Bearer " + "I'mToken"))
	    	.andDo(print())
	    	.andExpect(status().isUnauthorized());
    }
    
    @Test
    public void test_callBookListMethod_returnsOk() throws Exception {
    	mockMvc
	    	.perform(get("/books").header("Authorization", "Bearer " + validToken))
	    	.andDo(print())
	    	.andExpect(status().isOk());
    }
    
    @MethodSource("com.kyobong.store.controller.BookRestControllerTest#getBooksForCreateRequest")
    @ParameterizedTest
    public void test_callCreateMethodWithInvalidValues_returns422(
    		String body, boolean title, boolean writer, boolean status, boolean categories) throws Exception {
       	ResultActions resp = mockMvc
	    	.perform(
    			post("/books")
    				.header("Authorization", "Bearer " + validToken)
    				.content(body)
    				.contentType(MediaType.APPLICATION_JSON_VALUE)
	    	)
	    	.andDo(print())
	    	.andExpect(status().isUnprocessableEntity());
       	if (title) {
       		resp.andExpect(jsonPath("$.title", is(notNullValue())));
       	}
       	if (writer) {
       		resp.andExpect(jsonPath("$.writer", is(notNullValue())));
       	}
       	if (status) {
       		resp.andExpect(jsonPath("$.status", is(notNullValue())));
       	}
       	if (categories) {
       		resp.andExpect(jsonPath("$.categories", is(notNullValue())));
       	}
    }

}
