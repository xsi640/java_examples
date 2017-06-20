package com.suyang.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@WebMvcTest(value = HelloController.class, secure = false)
public class HelloControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void test() throws Exception{
		String url = "http://localhost:8080/test";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>(); 
        map.add("name", "Tom");
        RequestBuilder builder = MockMvcRequestBuilders.get(url).param("name", "张三").accept(MediaType.ALL);
        MvcResult result =  mockMvc.perform(builder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println(response.getContentAsString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(response.getContentAsString(), "hello 张三");
	}
}