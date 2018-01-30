package pl.pawel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.pawel.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long>{

	//*Need to read more about other type of Repository to extends - e.g. JPARepository
	//*Create other methods, where I'll use SQL to modify data
	//*Other kind of creating records in DB - like in that example: https://spring.io/guides/gs/accessing-data-jpa/
	List<Match> findByHomeTeam(String homeTeam);
}
