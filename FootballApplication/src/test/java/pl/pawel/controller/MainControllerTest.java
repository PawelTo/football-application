package pl.pawel.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@RunWith(SpringRunner.class)
public class MainControllerTest {

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		// viewResovler created to setSuffix (in other case HTTP address name must be
		// different than view name)
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".html");
		mockMvc = MockMvcBuilders.standaloneSetup(new MainController()).setViewResolvers(viewResolver).build();
	}

	@Test
	public void testStartPage() throws Exception {
		// first test to check if controller return expected view
		mockMvc.perform(get("/start")).andExpect(status().isOk()).andExpect(view().name("start")).andDo(print());
	}

}
