package pl.pawel.service.competitionPointsCount;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.pawel.model.Club;
import pl.pawel.model.Competition;
import pl.pawel.model.Match;
import pl.pawel.repository.MatchRepository;

/**
 * @author Pawel
 * Tests create for learing Mock Injection
 */
@RunWith(MockitoJUnitRunner.class)
public class CompetitionPointsCountTestInjectMock {

	List<Match> oneMatchList;
	List<Match> twoMatchList;
	Club club1 = new Club("C1", "stadium1", 123);
	Club club2 = new Club("C2", "stadium2", 135);
	Club club3 = new Club("C3", "stadium3", 235);
	Competition competition1 = new Competition("competition1", 123);
	Match m1 = new Match(LocalDate.of(2018, 11, 25), club1, club2, 2, 1, competition1, 123);
	Match m2 = new Match(LocalDate.of(2018, 11, 25), club1, club3, 1, 1, competition1, 123);
	
	@Before
	public void createMatchesToTest() {		
		oneMatchList = new ArrayList<>();
		oneMatchList.add(m1);
		twoMatchList = new ArrayList<>();
		twoMatchList.addAll(oneMatchList);
		twoMatchList.add(m2);
	}
	
	@InjectMocks
	private CompetitionPointsCount cmpCount;
	
	@Mock
	private MatchRepository matchRepoMock;
	
	@Test
	public void mockTest() {
		LeagueTableRow team1 = new LeagueTableRow("C1",3,2,1);
		LeagueTableRow team2 = new LeagueTableRow("C2",0,1,2);
		
		List<LeagueTableRow> expectedTable = new ArrayList<>();
		expectedTable.add(team1);
		expectedTable.add(team2);
		
		when(matchRepoMock.findByHomeTeam("homeTeam")).thenReturn(oneMatchList);
		
		List<LeagueTableRow> actualTable = cmpCount.getTableOfCompetition(matchRepoMock.findByHomeTeam("homeTeam"));
		assertEquals(expectedTable, actualTable);
	}
	
	@Test
	public void mockTest2() {
		LeagueTableRow team1 = new LeagueTableRow("C1",4,3,2);
		LeagueTableRow team2 = new LeagueTableRow("C2",0,1,2);
		LeagueTableRow team3 = new LeagueTableRow("C3",1,1,1);
		
		List<LeagueTableRow> expectedTable = new ArrayList<>();
		expectedTable.add(team1);
		expectedTable.add(team3);
		expectedTable.add(team2);
		
		when(matchRepoMock.findByCompetition(competition1)).thenReturn(twoMatchList);
		List<LeagueTableRow> actualTable = cmpCount.getTableOfCompetition(competition1);
		
		assertEquals(expectedTable, actualTable);
		verify(matchRepoMock,times(1)).findByCompetition(competition1);
	}

}
