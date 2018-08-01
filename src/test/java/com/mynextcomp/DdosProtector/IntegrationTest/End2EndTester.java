package com.mynextcomp.DdosProtector.IntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mynextcomp.DdosProtector.controllers.HttpController;

@RunWith(SpringRunner.class)
@WebMvcTest(HttpController.class)
public class End2EndTester {

	private static final String URL = "http://localhost:8080/?clientID=";

	@Autowired
	MockMvc mockMvc;

	@Test
	public void test_single_200_scenario() throws Exception {
		mockMvc.perform(get(URL + "111")).andExpect(status().isOk());
	}

	@Test
	public void test_call_after_delay() throws Exception {
		for (int i = 0; i < 6; i++) {
			mockMvc.perform(get(URL + "222")).andExpect(status().isOk());
		}
		TimeUnit.SECONDS.sleep(500);
		mockMvc.perform(get(URL + "222")).andExpect(status().isOk());
	}

	@Test
	public void test_503_scenario() throws Exception {
		for (int i = 0; i < 6; i++) {
			mockMvc.perform(get(URL + "333")).andExpect(status().isOk());
		}
		mockMvc.perform(get(URL + "333")).andExpect(status().isServiceUnavailable());
	}

	
	
	
}
