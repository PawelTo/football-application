package pl.pawel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pawel.model.Match;
import pl.pawel.repository.MatchRepository;

@Service
public class MatchService {

	//using Dependency Injection to get Repository layer
	private final MatchRepository matchRepository;
	
	@Autowired
	public MatchService(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}
	
	//*Consider pros and cons other kind of collections
	public List<Match> findByHomeTeam(String homeTeam){
		List<Match> listOfGames = new ArrayList<>();
		for(Match game: matchRepository.findByHomeTeam(homeTeam))
			listOfGames.add(game);
		return listOfGames;
	}
	public List<Match> findAll(){
		List<Match> listOfGames = new ArrayList<>();
		for(Match game: matchRepository.findAll())
			listOfGames.add(game);
		return listOfGames;
	}
}
