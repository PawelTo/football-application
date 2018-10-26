package pl.pawel.service.competitionPointsCount;

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
}
