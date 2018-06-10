package pl.pawel.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pawel.model.Club;
import pl.pawel.model.Competition;
import pl.pawel.model.Match;
import pl.pawel.repository.ClubRepository;
import pl.pawel.repository.CompetitionRepository;

/**Class to create Match using data from given Array of String data
 * @author Pawel
 *
 */
@Service
public class MatchFromData {

	private static Logger logger = LoggerFactory.getLogger(MatchFromData.class);
	
	@Autowired
	private ClubRepository clubRepository;
	@Autowired
	private CompetitionRepository competitionRepository;
	private Match match;
	
	private LocalDate date;
	private Club homeTeam;
	private Club awayTeam;
	private int homeTeamScore;
	private int awayTeamScore;
	private Competition competition;
	private int attendance;
	
	public Match createMatch(){
		match = new Match();
		match.setDateOfGame(date);
		match.setHomeTeam(homeTeam);
		match.setAwayTeam(awayTeam);
		match.setHomeTeamScore(homeTeamScore);
		match.setAwayTeamScore(awayTeamScore);
		match.setCompetition(competition);
		match.setAttendance(attendance);
		return match;
	}
	
	public void setDataFromFile(String[] data) {
		date = setDateFromString(data[0]);
		homeTeam = setTeamFromString(data[1]);
		awayTeam = setTeamFromString(data[2]);
		homeTeamScore = Integer.parseInt(data[3]);
		awayTeamScore = Integer.parseInt(data[4]);
		competition = setCompetitionFromString(data[5]);
		attendance = Integer.parseInt(data[6]);
	}
	
	/**
	 * Method checks if club already exist in database, in other case it creates new Club
	 */
	private Club setTeamFromString(String nameOfClub) {
		Club team =null;
		long id;
		try {
			id = Long.parseLong(nameOfClub);
			team = clubRepository.findOne(id);
			if(team == null) {
				team = clubRepository.save(new Club(nameOfClub, "TBD - ClubFromFile", 0));
			}
		} catch (Exception e) {
			team = clubRepository.findByClubName(nameOfClub);
			if(team == null) {
				team = clubRepository.save(new Club(nameOfClub, "TBD - ClubFromFile", 0));
			}
		}
		return team;
	}
	
	/**Check if competition of the match exists, in other case it create a new one.
	 * @param nameOfCompetition
	 */
	private Competition setCompetitionFromString(String nameOfCompetition) {
		Competition competition;
		long id;
		try {
			id = Long.parseLong(nameOfCompetition);
			competition = competitionRepository.findOne(id);
			if(competition==null) {
				competition = competitionRepository.save(new Competition(nameOfCompetition, 1));
			}
		} catch (Exception e) {
			competition = competitionRepository.findByCompetitionName(nameOfCompetition);
			if(competition==null) {
				competition = competitionRepository.save(new Competition(nameOfCompetition, 1));
			}
		}
		return competition;
	}
	
	/**Method to convert String to Date, if date of game doesn't exist in source data it return some default value
	 * @param date
	 * @return date of Game
	 */
	private LocalDate setDateFromString(String date) {
		int year, month, day;
		LocalDate dateToReturn;
		
		logger.info("Date to convert: "+date);
		try {
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(4, 6));
			day = Integer.parseInt(date.substring(6));
			logger.info("Rok: "+year + " m: "+month+" d: "+day);
			dateToReturn = LocalDate.of(year, month, day);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			dateToReturn = LocalDate.of(0, 1, 1);
		} catch (Exception e) {
			e.getMessage();
			dateToReturn = LocalDate.of(0, 1, 2);
		}
		return dateToReturn;
	}
}
