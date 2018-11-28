package pl.pawel.service.competitionPointsCount;

import java.util.Comparator;

public class LeagueTableRowComparator implements Comparator<LeagueTableRow> {

	@Override
	public int compare(LeagueTableRow o1, LeagueTableRow o2) {
		int pointsComp = o2.getPoints() - o1.getPoints();
		if (pointsComp != 0) {
			return pointsComp;
		} else {
			int goalsDiffComp = (o2.getGoalsFor() - o2.getGoalsAgainst()) - (o1.getGoalsFor() - o1.getGoalsAgainst());
			if (goalsDiffComp != 0) {
				return goalsDiffComp;
			} else
				return o2.getGoalsFor() - o1.getGoalsFor();
		}
	}

}
