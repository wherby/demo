package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Test
	void contextLoads() {
	}


	void addIncidentShouldSuccess() throws Exception{

	}

}
