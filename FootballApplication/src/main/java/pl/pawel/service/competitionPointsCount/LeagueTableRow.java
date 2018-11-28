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

	public LeagueTableRow(String teamNeame, int points, int goalsFor, int goalsAgainst) {
		super();
		this.teamNeame = teamNeame;
		this.points = points;
		this.goalsFor = goalsFor;
		this.goalsAgainst = goalsAgainst;
	}

	@Override
	public String toString() {
		return "LeagueTableRow [TeamNeame=" + teamNeame + ", points=" + points + ", goalsFor=" + goalsFor
				+ ", goalsAgainst=" + goalsAgainst + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + goalsAgainst;
		result = prime * result + goalsFor;
		result = prime * result + points;
		result = prime * result + ((teamNeame == null) ? 0 : teamNeame.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeagueTableRow other = (LeagueTableRow) obj;
		if (goalsAgainst != other.goalsAgainst)
			return false;
		if (goalsFor != other.goalsFor)
			return false;
		if (points != other.points)
			return false;
		if (teamNeame == null) {
			if (other.teamNeame != null)
				return false;
		} else if (!teamNeame.equals(other.teamNeame))
			return false;
		return true;
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
