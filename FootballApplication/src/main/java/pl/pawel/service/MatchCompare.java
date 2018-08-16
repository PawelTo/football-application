package pl.pawel.service;

import java.util.function.Predicate;

import pl.pawel.model.Match;

public class MatchCompare {

	public int compareMatchesAttendance(Match m1, Match m2) {
		int attendancedifference = Math.abs(m1.getAttendance() - m2.getAttendance());
		System.out.println("M1: "+ m1.getAttendance() + " M2: "+m2.getAttendance());
		System.out.println("Difference in attendance: "+attendancedifference);
		return attendancedifference;
	}
	
	public void compareGamesLambda(Match m1, Match  m2) {
		MatchComparator attendanceComparator = (a,b) -> {
			int attendancedifference = Math.abs(a.getAttendance() - b.getAttendance());
			System.out.println("M1: "+ a.getAttendance() + " M2: "+b.getAttendance());
			System.out.println("Difference in attendance using lambda: "+attendancedifference);
			return attendancedifference;
		};
		
		MatchComparator goalsComparator = (a,b) ->{
			int goalsInMatch1 = a.getHomeTeamScore() + a.getAwayTeamScore();
			int goalsInMatch2 = b.getHomeTeamScore() + b.getAwayTeamScore();
			int goalsDifference = Math.abs(goalsInMatch1+goalsInMatch2);
			System.out.println("Goals difference: "+goalsDifference);
			return goalsDifference;
		};
		
		//Using functional interface to check if homeTeam win game
		Predicate<Match> checkIfHomeTeamWin = (a) ->{
			int homeTeamScore = a.getHomeTeamScore();
			int awayTeamScore = a.getAwayTeamScore();
			boolean homeTeamWin = false;
			System.out.println(a.getHomeTeam().getClubName()+" - "+a.getAwayTeam().getClubName()+" "+ homeTeamScore+ " : "+awayTeamScore);
			homeTeamWin = (homeTeamScore>awayTeamScore) ? true : false;
			
			return homeTeamWin;
		};
		
		System.out.println("I'm using lambda expression");
		attendanceComparator.compare(m1, m2);
		goalsComparator.compare(m1, m2);
		checkIfHomeTeamWin.test(m1);
	}
	
	
}
