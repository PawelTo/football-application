package pl.pawel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.pawel.model.Club;

@Repository
public interface ClubRepository extends CrudRepository<Club, Long> {

	Club findByClubName(String nameOfClub);

}
