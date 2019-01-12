package pl.pawel.service.dataFromWebSaver;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pawel.model.Club;
import pl.pawel.model.Competition;
import pl.pawel.model.Match;
import pl.pawel.repository.ClubRepository;
import pl.pawel.repository.CompetitionRepository;

/**Class to change Strings from webData to applications models
 * @author Pawel
 *
 */
@Service
public class MatchFromWebDataCreator {

	@Autowired
	private ClubRepository clubRepository;
	@Autowired
	private CompetitionRepository competitionRepository;
	
	/**Method to create match from flashscore data
	 * @param flashscoreData
	 * @return
	 */
	public Match createFlashScoreMatch(FlashscoreData flashscoreData) {
		Match match = new Match();
		match.setDateOfGame(LocalDate.now());
		match.setHomeTeam(setTeamFromString(flashscoreData.getHomeTeam()));
		match.setAwayTeam(setTeamFromString(flashscoreData.getAwayTeam()));
		match.setAttendance(0);//Doesn't receive date of game from flashscore
		match.setAwayTeamScore(flashscoreData.getAwayTeamScore());
		match.setHomeTeamScore(flashscoreData.getHomeTeamScore());
		match.setCompetition(setCompetitionFromString(flashscoreData.getCompetition()));
		return match;
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
				team = clubRepository.save(new Club(nameOfClub, "TBD - ClubFromWeb", 0));
			}
		} catch (Exception e) {
			team = clubRepository.findByClubName(nameOfClub);
			if(team == null) {
				team = clubRepository.save(new Club(nameOfClub, "TBD - ClubFromWeb", 0));
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
}
