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
		// table ordered by points
		table.sort(Comparator.comparingInt(LeagueTableRow::getPoints).reversed());
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

		for (int i = 0; i < table.size() - 1; i++) {
			int higherRankedTeamPoints = table.get(i).getPoints();
			int lowerRankedTeamPoints = table.get(i + 1).getPoints();

			if (higherRankedTeamPoints > lowerRankedTeamPoints) {
				// There is nothing to change
			} else if (higherRankedTeamPoints < lowerRankedTeamPoints) {
				System.out.println("Record isn't sort i=" + i);
			} else if (higherRankedTeamPoints == lowerRankedTeamPoints) {
				List<String> listOfTeamsWithSamePoints = new LinkedList<>();
				List<LeagueTableRow> sameRows = new ArrayList<>();

				listOfTeamsWithSamePoints.add(table.get(i).getTeamNeame());
				sameRows.add(table.get(i));

				int j = i + 1;
				while (table.get(j).getPoints() == lowerRankedTeamPoints) {
					listOfTeamsWithSamePoints.add(table.get(j).getTeamNeame());
					sameRows.add(table.get(j));
					j++;
				}

				List<Match> matchesTeamsWithSamePoints = new ArrayList<>();
				for (Match m : matches) {
					if (listOfTeamsWithSamePoints.contains(m.getAwayTeam().getClubName())
							&& listOfTeamsWithSamePoints.contains(m.getHomeTeam().getClubName())) {
						matchesTeamsWithSamePoints.add(m);
					}
				}
				
				// create table for teams with same points
				List<LeagueTableRow> samePointsTeamTable = new ArrayList<>();
				for (Match match : matchesTeamsWithSamePoints) {
					int hTeamPosition = -1;
					int aTeamPosition = -1;
					String hTeamNeam = match.getHomeTeam().getClubName();
					String aTeamNeam = match.getAwayTeam().getClubName();
					int hTeamScore = match.getHomeTeamScore();
					int aTeamScore = match.getAwayTeamScore();

					int k = 0;
					if (!samePointsTeamTable.isEmpty()) {
						while (k < samePointsTeamTable.size() && (hTeamPosition < 0 || aTeamPosition < 0)) {
							if (samePointsTeamTable.get(k).getTeamNeame().equals(hTeamNeam))
								hTeamPosition = k;
							if (samePointsTeamTable.get(k).getTeamNeame().equals(aTeamNeam))
								aTeamPosition = k;
							k++;
						}
					}
					if (hTeamPosition < 0) {
						LeagueTableRow thRow = new LeagueTableRow();
						thRow.setTeamNeame(hTeamNeam);
						samePointsTeamTable.add(thRow);
						hTeamPosition = samePointsTeamTable.size() - 1;
					}
					if (aTeamPosition < 0) {
						LeagueTableRow taRow = new LeagueTableRow();
						taRow.setTeamNeame(aTeamNeam);
						samePointsTeamTable.add(taRow);
						aTeamPosition = samePointsTeamTable.size() - 1;
					}

					// counting points in table
					LeagueTableRow hTRow = samePointsTeamTable.get(hTeamPosition);
					hTRow.setGoalsFor(hTRow.getGoalsFor() + hTeamScore);
					hTRow.setGoalsAgainst(hTRow.getGoalsAgainst() + aTeamScore);

					LeagueTableRow aTRow = samePointsTeamTable.get(aTeamPosition);
					aTRow.setGoalsFor(aTRow.getGoalsFor() + aTeamScore);
					aTRow.setGoalsAgainst(aTRow.getGoalsAgainst() + hTeamScore);

					if (hTeamScore > aTeamScore)
						hTRow.setPoints(hTRow.getPoints() + 3);
					else if (hTeamScore < aTeamScore)
						aTRow.setPoints(aTRow.getPoints() + 3);
					else {
						hTRow.setPoints(aTRow.getPoints() + 1);
						aTRow.setPoints(aTRow.getPoints() + 1);
					}
				}
				samePointsTeamTable.sort(Comparator.comparingInt(LeagueTableRow::getPoints).reversed());
				
				int l = i;
				for (LeagueTableRow lr : samePointsTeamTable) {
					for (LeagueTableRow sr : sameRows) {
						if (lr.getTeamNeame().equals(sr.getTeamNeame())) {
							table.set(l, sr);
							l++;
						}
					}
				}
			}
		}
	}
}
