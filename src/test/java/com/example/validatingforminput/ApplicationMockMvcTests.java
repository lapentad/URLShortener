package com.example.validatingforminput;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationMockMvcTests {
/*
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void checkURLtooShortFailure() throws Exception {
		MockHttpServletRequestBuilder createPerson = post("/")
				.param("longUrl", "w");

		mockMvc.perform(createPerson)
			.andExpect(model().hasErrors());
	}


	@Test
	public void checkURLSuccess() throws Exception {
		MockHttpServletRequestBuilder createPerson = post("/")
				.param("longUrl", "www.google.com");

		mockMvc.perform(createPerson)
			.andExpect(model().hasNoErrors());
	}

	@Test
	public void checkURLwithHTTPStag() throws Exception {
		MockHttpServletRequestBuilder createPerson = post("/")
				.param("longUrl", "https://www.google.com");

		mockMvc.perform(createPerson)
			.andExpect(model().hasNoErrors());
	}
	*/
}
