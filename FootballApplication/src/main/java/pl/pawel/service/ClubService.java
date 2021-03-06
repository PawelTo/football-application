package pl.pawel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pawel.model.Club;
import pl.pawel.repository.ClubRepository;

@Service
public class ClubService {
	
	@Autowired
	private ClubRepository clubRepository;
	
	public Club findOne(long id) {
		return clubRepository.findOne(id);
	}
	public void save(Club club) {
		clubRepository.save(club);
	}
}
