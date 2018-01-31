package pl.pawel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.pawel.model.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long>{

	//*Need to read more about other type of Repository to extends - e.g. JPARepository
	//*Create other methods, where I'll use SQL to modify data (I should create my own repository for that)
	//*Other kind of creating records in DB - like in that example: https://spring.io/guides/gs/accessing-data-jpa/
	List<Match> findByHomeTeam(String homeTeam);
}
