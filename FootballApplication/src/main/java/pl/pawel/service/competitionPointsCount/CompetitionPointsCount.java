package pl.pawel.service.competitionPointsCount;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphbuilder.struc.LinkedList;

import pl.pawel.model.Competition;
import pl.pawel.model.Match;
import pl.pawel.repository.MatchRepository;

@Service
public class CompetitionPointsCount {

	private List<Match> matches;
	private Competition competition;
	private List<LeagueTableRow> table;

	@Autowired
	private MatchRepository matchRepository;

	public List<LeagueTableRow> getTableOfCompetition(Competition competition){
		matches = matchRepository.findByCompetition(competition);
		table = createTable(matches);
		return table;
	}
	public List<LeagueTableRow> getTableOfCompetitoin(long id) {
		matches = matchRepository.findByCompetition(createCompetitionFromLong(id));
		table = createTable(matches);
		return table;
	}

	private List<LeagueTableRow> createTable(List<Match> matches) {
		matches = getMatchesFromCompetition(competition);
		table = createNewTable();
		for (Match match : matches) {
			int homeTeamPosition = -1;
			int awayTeamPosition = -1;		
			String homeTeamName = match.getHomeTeam().getClubName();
			String awayTeamName = match.getAwayTeam().getClubName();
			int awayTeamScore = match.getAwayTeamScore();
			int homeTeamScore = match.getHomeTeamScore();
			
			// check if teams form match already exist in table	
			int i = 0;
			if (!table.isEmpty()) {
				while (i <= table.size() && (homeTeamPosition < 0 || awayTeamPosition < 0)) {
					if (table.get(i).getTeamNeame().equals(homeTeamName))
						homeTeamPosition = i;
					if (table.get(i).getTeamNeame().equals(awayTeamName))
						awayTeamPosition = i;
					i++;
				}
			}
			if (homeTeamPosition < 0) {
				homeTeamPosition = addNewTeamToTable(homeTeamName);
			}
			if (awayTeamPosition < 0) {
				awayTeamPosition = addNewTeamToTable(awayTeamName);
			}
			updateTable(homeTeamPosition, awayTeamPosition, awayTeamScore, homeTeamScore);
		}
		return table;
	}

	private void updateTable(int homeTeamPosition, int awayTeamPosition, int awayTeamScore, int homeTeamScore) {
		LeagueTableRow homeTeamRow = table.get(homeTeamPosition);
		homeTeamRow.setGoalsFor(homeTeamRow.getGoalsFor() + homeTeamScore);			
		homeTeamRow.setGoalsAgainst(homeTeamRow.getGoalsAgainst() + awayTeamScore);

		LeagueTableRow awayTeamRow = table.get(awayTeamPosition);
		awayTeamRow.setGoalsFor(awayTeamRow.getGoalsFor() + awayTeamScore);
		awayTeamRow.setGoalsAgainst(awayTeamRow.getGoalsAgainst() + homeTeamScore);

		if (homeTeamScore > awayTeamScore)
			homeTeamRow.setPoints(homeTeamRow.getPoints() + 3);
		else if (homeTeamScore == awayTeamScore) {
			homeTeamRow.setPoints(homeTeamRow.getPoints() + 1);
			awayTeamRow.setPoints(awayTeamRow.getPoints() + 1);
		} else {
			awayTeamRow.setPoints(awayTeamRow.getPoints() + 3);
		}
	}

	private int addNewTeamToTable(String teamName) {
		int teamPosition;
		LeagueTableRow teamRow = new LeagueTableRow();
		teamRow.setTeamNeame(teamName);
		table.add(teamRow);
		teamPosition = table.size() - 1;
		return teamPosition;
	}

	private List<LeagueTableRow> createNewTable() {
		return new ArrayList<>();
	}
	
	private List<Match> getMatchesFromCompetition(Competition competition) {
		matches = matchRepository.findByCompetition(competition);
		return matches;
	}

	private Competition createCompetitionFromLong(long id) {
		competition = new Competition();
		competition.setId(id);
		return competition;
	}
}
