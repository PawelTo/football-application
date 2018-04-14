package pl.pawel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pawel.model.Competition;
import pl.pawel.repository.CompetitionRepository;

@Service
public class CompetitionService {

	private final CompetitionRepository competitionRepository;
	
	@Autowired
	public CompetitionService(CompetitionRepository competitionRepository) {
		this.competitionRepository = competitionRepository;
	}
	
	public void save(Competition competition) {
		competitionRepository.save(competition);
	}
	
	//metod to test custom Query
	public List<Competition> nameEndsWith(String lastChars){
		List<Competition> listOfCompetitions = competitionRepository.ownFindByLastChars(lastChars);
		return listOfCompetitions;
	}
	//metod to test custom Query
	public List<Object[]> getMostPopularGames(String nameOfCompetitions){
		return competitionRepository.getMostPopularGames(nameOfCompetitions);
	}
	
	//method to rest custom Query - RANK() OVER
	public List<Object[]> rankOver(){
		return competitionRepository.rankOver();
	}
}
