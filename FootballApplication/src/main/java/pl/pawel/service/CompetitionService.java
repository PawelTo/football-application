package pl.pawel.service;

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
}
