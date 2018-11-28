package pl.pawel.service.competitionPointsCount;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.pawel.model.Club;
import pl.pawel.model.Competition;
import pl.pawel.model.Match;

public class CompetitionPointsCountTest {
	
	List<Match> oneMatchList;
	List<Match> twoMatchList;
	List<Match> threeMatchList;
	List<Match> threeMatchListWithoutGameC2vsC3;
	Club club1 = new Club("C1", "stadium1", 123);
	Club club2 = new Club("C2", "stadium2", 135);
	Club club3 = new Club("C3", "stadium3", 235);
	Competition competition1 = new Competition("competition1", 123);
	Match m1 = new Match(LocalDate.of(2018, 11, 25), club1, club2, 2, 1, competition1, 123);
	Match m2 = new Match(LocalDate.of(2018, 11, 25), club1, club3, 1, 1, competition1, 123);
	Match m3 = new Match(LocalDate.of(2018, 11, 25), club3, club1, 1, 0, competition1, 123);
	Match m4 = new Match(LocalDate.of(2018, 11, 25), club2, club1, 1, 1, competition1, 123);
	
	@Before
	public void createMatchesToTest() {		
		oneMatchList = new ArrayList<>();
		oneMatchList.add(m1);
		twoMatchList = new ArrayList<>();
		twoMatchList.addAll(oneMatchList);
		twoMatchList.add(m2);
		threeMatchList = new ArrayList<>();
		threeMatchList.addAll(twoMatchList);
		threeMatchList.add(m3);
		threeMatchListWithoutGameC2vsC3 = new ArrayList<>();
		threeMatchListWithoutGameC2vsC3.addAll(twoMatchList);
		threeMatchListWithoutGameC2vsC3.add(m4);
	}
	
	@Test
	public void getTableOfCompetitionTest_1Match_2Teams() {
		LeagueTableRow team1 = new LeagueTableRow("C1",3,2,1);
		LeagueTableRow team2 = new LeagueTableRow("C2",0,1,2);
		
		List<LeagueTableRow> expectedTable = new ArrayList<>();
		expectedTable.add(team1);
		expectedTable.add(team2);
		
		List<LeagueTableRow> actual = new CompetitionPointsCount().getTableOfCompetition(oneMatchList);
		assertEquals(expectedTable, actual);
	}
	
	@Test
	public void getTableOfCompetitionTest_2Match_3Teams() {
		LeagueTableRow team1 = new LeagueTableRow("C1",4,3,2);
		LeagueTableRow team2 = new LeagueTableRow("C2",0,1,2);
		LeagueTableRow team3 = new LeagueTableRow("C3",1,1,1);
		
		List<LeagueTableRow> expectedTable = new ArrayList<>();
		expectedTable.add(team1);
		expectedTable.add(team3);
		expectedTable.add(team2);
		
		List<LeagueTableRow> actual = new CompetitionPointsCount().getTableOfCompetition(twoMatchList);
		assertEquals(expectedTable, actual);
	}
	
	@Test
	public void getTableOfCompetitionTest_3Match_3Teams_2TeamsSamePoints() {
		List<LeagueTableRow> expectedTable = new ArrayList<>();
		LeagueTableRow team1 = new LeagueTableRow("C1",4,3,3);
		LeagueTableRow team2 = new LeagueTableRow("C2",0,1,2);
		LeagueTableRow team3 = new LeagueTableRow("C3",4,2,1);
		expectedTable.add(team3);
		expectedTable.add(team1);
		expectedTable.add(team2);
		
		List<LeagueTableRow> actual = new CompetitionPointsCount().getTableOfCompetition(threeMatchList);
		assertEquals(expectedTable, actual);
	}
	
	@Test
	public void getTableOfCompetitionTest_3Match_3Teams_2TeamsSamePoints_WithoutGameBetweenSamePointsTeams() {
		List<LeagueTableRow> expectedTable = new ArrayList<>();
		LeagueTableRow team1 = new LeagueTableRow("C1",5,4,3);
		LeagueTableRow team2 = new LeagueTableRow("C2",1,2,3);
		LeagueTableRow team3 = new LeagueTableRow("C3",1,1,1);
		expectedTable.add(team1);
		expectedTable.add(team3);
		expectedTable.add(team2);
		
		List<LeagueTableRow> actual = new CompetitionPointsCount().getTableOfCompetition(threeMatchListWithoutGameC2vsC3);
		assertEquals(expectedTable, actual);
	}
}
