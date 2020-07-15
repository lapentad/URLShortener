package com.lapenta.validating;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.lapenta.main.Main;
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
public class TestingValidation {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void checkTooShortUrl() throws Exception {
		this.mockMvc.perform(post("/").param("urlForm", "w")).andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void checkUrl() throws Exception {
		this.mockMvc.perform(post("/").param("urlForm", "www.google.com")).andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void checkUrlwithHTTPS() throws Exception {
		this.mockMvc.perform(post("/").param("urlForm", "https://www.google.com")).andDo(print()).andExpect(status().isOk());

	}
}