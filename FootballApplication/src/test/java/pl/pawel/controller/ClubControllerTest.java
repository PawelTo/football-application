package pl.pawel.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import pl.pawel.model.Club;
import pl.pawel.service.ClubService;
import pl.pawel.start.FootballApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FootballApplication.class)
@AutoConfigureMockMvc(secure=false)
public class ClubControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	private ClubService clubService;
	
	private Club clubTest;
	@Before
	public void setUpClub() {
		clubTest = new Club("Klub Testowy", "Stadion Testowy", 2134);
	}
	
	@Test
	public void test() throws Exception {
		assertThat(clubService).isNotNull();
		mockMvc.perform(get("/club")).andExpect(status().isOk()).andExpect(view().name("start"));
	}

}
