package com.mynextcomp.DdosProtector.controller.unitTests;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mynextcomp.DdosProtector.controllers.HttpController;
import com.mynextcomp.DdosProtector.services.DdosProtectorService;

@RunWith(SpringRunner.class)
@WebMvcTest(HttpController.class)
public class HttpControllerTest {

	private static final String URL = "http://localhost:8080/?clientID=";

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private DdosProtectorService ddosProtServ;

	@Test
	public void test_Service_Available_Scenario_200() throws Exception {
		when(ddosProtServ.servClientRequest("111")).thenReturn(true);
		mockMvc.perform(get(URL + "111")).andExpect(status().isOk());
		verify(ddosProtServ, times(1)).servClientRequest("111");
	}

	@Test
	public void test_Service_Not_Available_Scenario_503() throws Exception {
		when(ddosProtServ.servClientRequest("222")).thenReturn(false);
		mockMvc.perform(get(URL + "222")).andExpect(status().isServiceUnavailable());
		verify(ddosProtServ, times(1)).servClientRequest("222");
	}
	
}
