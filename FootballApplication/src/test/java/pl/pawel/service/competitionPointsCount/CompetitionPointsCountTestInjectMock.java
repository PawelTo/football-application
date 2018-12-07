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
import org.mockito.Mockito;
import org.mockito.Spy;
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

	@InjectMocks
	private CompetitionPointsCount cmpCount;
	
	@Mock
	private MatchRepository matchRepoMock;
	
	@Mock
	private Club mockClub;
	
	@Spy
	private Club spyClub = new Club("sClub", "spyStadiumName", 341);
	
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
	
	@Test
	public void getTableOfCompetitionTest_1Match_2Teams_MockInjection() {
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
	public void getTableOfCompetitionTest_2Match_3Teams_MockInjection() {
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
	
	/**
	 * Method to testing mocking object
	 */
	@Test
	public void testMockingClub() {
		//Changing name of mocking Club, but real name in table still should be null
		mockClub.setClubName("mockClub");
		Match m3 = new Match(LocalDate.of(2018, 12, 07), mockClub, club2, 2, 1, competition1, 123);
		twoMatchList.add(m3);
		
		LeagueTableRow team1 = new LeagueTableRow("C1",4,3,2);
		LeagueTableRow team2 = new LeagueTableRow("C2",0,2,4);
		LeagueTableRow team3 = new LeagueTableRow("C3",1,1,1);
		LeagueTableRow teamMock = new LeagueTableRow(null,3,2,1);

		
		List<LeagueTableRow> expectedTable = new ArrayList<>();
		expectedTable.add(team1);
		expectedTable.add(teamMock);
		expectedTable.add(team3);
		expectedTable.add(team2);
		
		List<LeagueTableRow> actualTable = cmpCount.getTableOfCompetition(twoMatchList);
		assertEquals(expectedTable, actualTable);
	}
	
	/**
	 * Method to testing spying object
	 */
	@Test
	public void testSpyingClub() {
		//Changing name of spying Club - real name in table should also change
		spyClub.setClubName("spyClub");
		Match m3 = new Match(LocalDate.of(2018, 12, 07), spyClub, club2, 2, 1, competition1, 123);
		twoMatchList.add(m3);
		
		LeagueTableRow team1 = new LeagueTableRow("C1",4,3,2);
		LeagueTableRow team2 = new LeagueTableRow("C2",0,2,4);
		LeagueTableRow team3 = new LeagueTableRow("C3",1,1,1);
		LeagueTableRow teamSpy = new LeagueTableRow("spyClub",3,2,1);

		
		List<LeagueTableRow> expectedTable = new ArrayList<>();
		expectedTable.add(team1);
		expectedTable.add(teamSpy);
		expectedTable.add(team3);
		expectedTable.add(team2);
		
		List<LeagueTableRow> actualTable = cmpCount.getTableOfCompetition(twoMatchList);
		assertEquals(expectedTable, actualTable);
	}
}
