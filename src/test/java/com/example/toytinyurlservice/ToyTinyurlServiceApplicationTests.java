package com.example.toytinyurlservice;

import com.example.toytinyurlservice.controller.TinyURLController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.concurrent.ExecutorService;

@SpringBootTest
class ToyTinyurlServiceApplicationTests {

	@InjectMocks
	private TinyURLController tinyURLController;
	private MockMvc mockMvc;

	public void setup() throws Exception {
			mockMvc = MockMvcBuilders.standaloneSetup(this.tinyURLController)
					.setMessageConverters(new GsonHttpMessageConverter())
					.build();
	}

	@Test
	void contextLoads() {

//		MvcResult mvcResult = mockMvc
//				.perform(get("http://localhost:9091/tinyURL/shorten/asdf"))
//				.andExpect(status().isOK())
//				.andReturn();
	}

}
