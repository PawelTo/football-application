package pl.pawel.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import pl.pawel.model.Club;
import pl.pawel.model.Match;
import pl.pawel.repository.MatchRepository;

@RunWith(SpringRunner.class)
public class MatchServiceTest {
		
	@InjectMocks
	private MatchService matchService;
	
	@Mock
	private MatchRepository matchRepository;
	
	@Mock
	private Club club;
	
	@MockBean
	private Club club2;
	
	private Match match1;
	@Before
	public void setUp() {
		match1 = new Match(LocalDate.of(2017, 12, 23),club,club2,3,2,null,3123);
	}
	
	@Test
	public void findId() {
		when(matchRepository.findOne((long)1)).thenReturn(match1);
		Match found = matchService.findMatchByID(1);
		assertEquals(match1, found);
	}
}
