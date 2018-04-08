package pl.pawel.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pl.pawel.model.Club;
import pl.pawel.model.Competition;
import pl.pawel.model.Match;
import pl.pawel.service.MatchService;
import pl.pawel.start.FootballApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FootballApplication.class)
public class MatchControllerTest2 {

	//just other kind of test configuration, to compare
	@Mock
	private MatchService matchService;
	
	@InjectMocks
	private MatchController matchController;
	
	private MockMvc mockMvc;
	
	private Match match;
	@Before
	public void setUp() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".html");
		mockMvc = MockMvcBuilders.standaloneSetup(matchController).setViewResolvers(viewResolver).build();
		match = new Match(LocalDate.of(2014, 11, 21), new Club("klub 1", "stadion 1", 1234), new Club("klub 2", "stadion 2", 234), 1, 2, new Competition("LM", 2), 3432);
	}
	@Test
	public void editGameTest() throws Exception {
		when(matchService.findMatchByID(6)).thenReturn(match);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/editGame")).andExpect(status().isOk()).andExpect(view().name("editGame"))
		.andExpect(model().attribute("match", match))
		.andExpect(MockMvcResultMatchers.model().attribute("match", match));
	}

}
