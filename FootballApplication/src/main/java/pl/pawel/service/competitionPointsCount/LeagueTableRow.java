package pl.pawel.service.competitionPointsCount;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import pl.pawel.model.Match;

public class LeagueTableRow {

	private String teamNeame;
	private int points;
	private int goalsFor;
	private int goalsAgainst;
	
	public LeagueTableRow() {}

	@Override
	public String toString() {
		return "LeagueTableRow [TeamNeame=" + teamNeame + ", points=" + points + ", goalsFor=" + goalsFor
				+ ", goalsAgainst=" + goalsAgainst + "]";
	}

	public String getTeamNeame() {
		return teamNeame;
	}

	public void setTeamNeame(String teamNeame) {
		this.teamNeame = teamNeame;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getGoalsFor() {
		return goalsFor;
	}

	public void setGoalsFor(int goalsFor) {
		this.goalsFor = goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}
	
	/*private void setProperTeamOrder() {
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
	}*/
}
