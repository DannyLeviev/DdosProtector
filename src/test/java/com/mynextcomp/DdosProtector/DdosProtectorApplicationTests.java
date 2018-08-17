package com.mynextcomp.DdosProtector;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DdosProtectorApplicationTests {

	private static final String URL = "http://localhost:8080/?clientID=";

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void delayBeforeTest() throws Exception {
		TimeUnit.SECONDS.sleep(3);
	}

	@Before
	public void delayAfterTest() throws Exception {
		TimeUnit.SECONDS.sleep(3);
	}

	@Test
	public void test_single_request() throws Exception {
		mockMvc.perform(get(URL + "11")).andExpect(status().isOk());
	}

	@Test
	public void test_SERVICE_UNAVAILABLE_scenario() throws Exception {
		for (int i = 0; i < 5; i++) {
			mockMvc.perform(get(URL + "22")).andExpect(status().isOk());
		}
		mockMvc.perform(get(URL + "22")).andExpect(status().isServiceUnavailable());
	}

	@Test
	public void test_call_after_delay() throws Exception {
		for (int i = 0; i < 5; i++) {
			mockMvc.perform(get(URL + "33")).andExpect(status().isOk());
		}
		TimeUnit.SECONDS.sleep(6);
		mockMvc.perform(get(URL + "33")).andExpect(status().isOk());
	}

	@Test
	public void test_moving_timeframe_happypath() throws Exception {
		for (int i = 0; i < 15; i++) {
			mockMvc.perform(get(URL + "44")).andExpect(status().isOk());
			TimeUnit.MILLISECONDS.sleep(1000);
		}
	}
	
	@Test
	public void test_moving_timeframe_SERVICE_UNAVAILABLE_scenario() throws Exception {
		for (int i = 0; i < 7; i++) {
			mockMvc.perform(get(URL + "44")).andExpect(status().isOk());
			TimeUnit.MILLISECONDS.sleep(1000);
		}
		mockMvc.perform(get(URL + "44")).andExpect(status().isOk());
		mockMvc.perform(get(URL + "44")).andExpect(status().isServiceUnavailable());

	}
}
