package pl.pawel.service.competitionPointsCount;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<LeagueTableRow> getTableOfCompetition(Competition competition) {
		matches = matchRepository.findByCompetition(competition);
		table = createTable(matches);
		return table;
	}

	public List<LeagueTableRow> getTableOfCompetitoin(long id) {
		competition = createCompetitionFromLong(id);
		return getTableOfCompetition(competition);
	}

	public List<LeagueTableRow> getTableOfCompetition(List<Match> matches) {
		this.matches = matches;
		return createTable(matches);
	}

	private List<LeagueTableRow> createTable(List<Match> matches) {
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
				while (i < table.size() && (homeTeamPosition < 0 || awayTeamPosition < 0)) {
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

		setProperTeamOrder();
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
		LeagueTableRow teamRow = new LeagueTableRow();
		teamRow.setTeamNeame(teamName);
		table.add(teamRow);
		return table.size() - 1;
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

	private void setProperTeamOrder() {
		table.sort(Comparator.comparingInt(LeagueTableRow::getPoints).reversed());

		// if all teams in table have the same amount of points, there is no reason to
		// create small table, because all teams will get the same results
		if (table.get(0).getPoints() != table.get(table.size() - 1).getPoints()) {
			for (int i = 0; i < table.size() - 1; i++) {
				int higherRankedTeamPoints = table.get(i).getPoints();
				int lowerRankedTeamPoints = table.get(i + 1).getPoints();

				if (higherRankedTeamPoints == lowerRankedTeamPoints) {
					List<String> listOfTeamsWithSamePoints = new LinkedList<>();
					List<LeagueTableRow> tableRowsOfTeamsWithSamePoints = new ArrayList<>();

					listOfTeamsWithSamePoints.add(table.get(i).getTeamNeame());
					tableRowsOfTeamsWithSamePoints.add(table.get(i));

					// iterating over table to get list of teams with the same amount of points and
					// that records
					int j = i + 1;
					while (j < table.size() && (table.get(j).getPoints() == lowerRankedTeamPoints)) {
						listOfTeamsWithSamePoints.add(table.get(j).getTeamNeame());
						tableRowsOfTeamsWithSamePoints.add(table.get(j));
						j++;
					}

					// get matches between teams with same points
					List<Match> matchesTeamsWithSamePoints = new ArrayList<>();
					for (Match m : matches) {
						if (listOfTeamsWithSamePoints.contains(m.getHomeTeam().getClubName())
								&& listOfTeamsWithSamePoints.contains(m.getAwayTeam().getClubName())) {
							matchesTeamsWithSamePoints.add(m);
						}
					}

					// creating "small" table for teams with same points
					List<LeagueTableRow> samePointsTeamTable;
					if (!matchesTeamsWithSamePoints.isEmpty()) {
						samePointsTeamTable = new CompetitionPointsCount()
								.getTableOfCompetition(matchesTeamsWithSamePoints);
					}else {
						samePointsTeamTable = tableRowsOfTeamsWithSamePoints;
						samePointsTeamTable.sort(new LeagueTableRowComparator());
					}

					for (LeagueTableRow leagueRow : samePointsTeamTable) {
						for (LeagueTableRow row : tableRowsOfTeamsWithSamePoints) {
							if (leagueRow.getTeamNeame().equals(row.getTeamNeame())) {
								table.set(i, row);
								i++;
							}
						}
					}
				}
			}
		}
	}
}
