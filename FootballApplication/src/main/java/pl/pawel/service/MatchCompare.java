package pl.pawel.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pl.pawel.model.Club;
import pl.pawel.model.Match;

public class MatchCompare {

	public int compareMatchesAttendance(Match m1, Match m2) {
		int attendancedifference = Math.abs(m1.getAttendance() - m2.getAttendance());
		System.out.println("M1: " + m1.getAttendance() + " M2: " + m2.getAttendance());
		System.out.println("Difference in attendance: " + attendancedifference);
		return attendancedifference;
	}

	public void compareGamesLambda(Match m1, Match m2) {
		MatchComparator attendanceComparator = (a, b) -> {
			int attendancedifference = Math.abs(a.getAttendance() - b.getAttendance());
			System.out.println("M1: " + a.getAttendance() + " M2: " + b.getAttendance());
			System.out.println("Difference in attendance using lambda: " + attendancedifference);
			return attendancedifference;
		};

		MatchComparator goalsComparator = (a, b) -> {
			int goalsInMatch1 = a.getHomeTeamScore() + a.getAwayTeamScore();
			int goalsInMatch2 = b.getHomeTeamScore() + b.getAwayTeamScore();
			int goalsDifference = Math.abs(goalsInMatch1 + goalsInMatch2);
			System.out.println("Goals difference: " + goalsDifference);
			return goalsDifference;
		};

		// Using functional interface to check if homeTeam win game
		Predicate<Match> checkIfHomeTeamWin = (a) -> {
			int homeTeamScore = a.getHomeTeamScore();
			int awayTeamScore = a.getAwayTeamScore();
			boolean homeTeamWin = false;
			System.out.println(a.getHomeTeam().getClubName() + " - " + a.getAwayTeam().getClubName() + " "
					+ homeTeamScore + " : " + awayTeamScore);
			homeTeamWin = (homeTeamScore > awayTeamScore) ? true : false;

			return homeTeamWin;
		};

		System.out.println("I'm using lambda expression");
		attendanceComparator.compare(m1, m2);
		goalsComparator.compare(m1, m2);
		checkIfHomeTeamWin.test(m1);
	}

	public List<Match> getHomeWinnerMatches(List<Match> matchesToFilter) {
		List<Match> filteredMatches = matchesToFilter.stream().filter(m -> m.getHomeTeamScore() > m.getAwayTeamScore())
				.collect(Collectors.toList());
		return filteredMatches;
	}

	public List<Match> setMyClubsName(List<Match> matchesMyClub) {
		List<Match> myClubMatches = matchesMyClub.stream().peek(m -> m.setHomeTeamScore(5))
				.peek(m -> m.setHomeTeam(new Club("gospodarz", "stadion", 1235))).collect(Collectors.toList());
		return myClubMatches;
	}

	public List<Integer> sumGoals(List<Match> matchesMyClub) {
		List<Integer> map = matchesMyClub.stream().filter(m -> m.getAttendance() < 1500)
				.peek(m -> m.setHomeTeamScore(15)).map(m -> m.getHomeTeamScore() + m.getAwayTeamScore())
				.collect(Collectors.toList());
		return map;
	}

	public Map<Integer, Match> getMatchMap(List<Match> matchesMyClub) {
		Map<Integer, Match> mapMatch = matchesMyClub.stream().filter(m -> m.getAttendance() < 1500)
				.collect(Collectors.toMap(m -> Integer.valueOf(m.getAttendance()), m -> m));
		return mapMatch;
	}

	public List<Match> printMatches(List<Match> matchesMyClub) {
		matchesMyClub.stream().filter(m -> m.getAttendance() < 1500)
				.forEach(m -> m.setAttendance(m.getAwayTeamScore() + m.getHomeTeamScore()));
		return matchesMyClub;
	}
}
