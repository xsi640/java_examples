package com.suyang.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import com.suyang.Application;
import com.suyang.domain.Student;
import com.suyang.repository.StudentRepository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class StudentControllerTest {
	private MockMvc mockMvc;
	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	private Student student;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {
		mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		// 清除数据
		studentRepository.deleteAll();

		// 准备测试数据
		student = new Student();
		student.setId(UUID.randomUUID().toString());
		student.setName("张三");
		student.setAge(18);
		student.setBirthday(new Date());
		studentRepository.insert(student);
	}

	@Test
	public void findOneTest() throws Exception {
		mockMvc.perform(get("/student/" + student.getId()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(student.getId())))
				.andExpect(jsonPath("$.name", is(student.getName())))
				.andExpect(jsonPath("$.age", is(student.getAge())));
	}

	@Test
	public void findAllTest() throws Exception {
		mockMvc.perform(get("/student"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	public void createTest() throws Exception {
		Student s = new Student();
		s.setId(UUID.randomUUID().toString());
		s.setName("李四");
		s.setAge(20);
		s.setBirthday(new Date());

		mockMvc.perform(post("/student")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json(s)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(s.getId())))
				.andExpect(jsonPath("$.name", is(s.getName())))
				.andExpect(jsonPath("$.age", is(s.getAge())));
	}

	@Test
	public void modifyTest() throws Exception {
		Student s = new Student();
		s.setId(student.getId());
		s.setName("王五");
		s.setAge(30);
		s.setBirthday(new Date());

		mockMvc.perform(put("/student")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json(s)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(s.getId())))
				.andExpect(jsonPath("$.name", is(s.getName())))
				.andExpect(jsonPath("$.age", is(s.getAge())));
	}

	@Test
	public void deleteTest() throws Exception {
		mockMvc.perform(delete("/student/" + student.getId()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());

		mockMvc.perform(get("/student/" + student.getId()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(content().string(""));
	}

	@SuppressWarnings("unchecked")
	private String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}
