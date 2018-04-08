package pl.pawel.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import pl.pawel.model.Club;
import pl.pawel.model.Competition;
import pl.pawel.model.Match;
import pl.pawel.service.MatchService;
import pl.pawel.start.FootballApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FootballApplication.class)
//@AutoConfigureMockMvc(secure=false))
public class MatchControllerTest {

	//All test need to be improved
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	//what is the difference: MockBean vs Mock ?
	@MockBean
	private MatchService matchService;

	private Match match;
	private List<Match> listOfGames;

	@Before
	public void setUp() {
		// it's also allowed to use @Autowire instead of build it manually (like in
		// ClubControllerTest)
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		Match match = new Match(LocalDate.of(2017, 3, 23), new Club("Home Club1", "Stadium1", 2352),
				new Club("Away Club2", "Stadium2", 23553), 3, 2, new Competition("Liga Polska", 2), 3523);
		match.setId((long)6);
		listOfGames = new ArrayList<>();
		listOfGames.add(match);
		System.out.println("Lista: " +listOfGames);
	}

	@Test
	public void getAllMatchesTest() throws Exception {
		assertThat(matchService).isNotNull();
		when(matchService.findAll()).thenReturn(listOfGames);

		mockMvc.perform(MockMvcRequestBuilders.get("/all")).andExpect(status().isOk())
				.andExpect(view().name("showGames")).andExpect(MockMvcResultMatchers.view().name("showGames"))
				.andExpect(MockMvcResultMatchers.request().attribute("game", listOfGames));

		verify(matchService, times(1)).findAll(); // method findAll is called only 1
		verifyNoMoreInteractions(matchService); // there aren't called any other methods from matchService
	}

	@Test
	public void editGameTest() throws Exception {
		when(matchService.findMatchByID(6)).thenReturn(match);

		// this test faild, because exception evaluation springEL expression
		mockMvc.perform(MockMvcRequestBuilders.get("/editGame")).andExpect(status().isOk())
				.andExpect(view().name("editGame"))
				//.andExpect(MockMvcResultMatchers.model().attribute("match", match))
				.andExpect(model().attribute("match", match))
				.andDo(print());
		
		verify(matchService, times(1)).findMatchByID(6);
		verifyNoMoreInteractions(matchService);
	}

//	@Test
//	public void saveMatchFromWebTest() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/save").flashAttr("game", match)).andExpect(status().isOk())
//				.andDo(print());
//	}
}
