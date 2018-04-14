package pl.pawel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.pawel.model.Competition;

/**
 * @author Pawel Toporowicz
 * This is my custom repository, where I have been trying to write my own query, despite the fact that it isn't necessary in some cases.
 */
public interface CompetitionRepositoryCustom {
	
	/**Custom method using JPQL to find records ends with given String
	 * @param lastChars
	 * @return selected Competition
	 */
	@Query("select c from Competition c where c.competitionName like %?1")
	List<Competition> ownFindByLastChars(String lastChars);

	/**Native SQL Query - Get most popular competition, number of games in that competition and average attendance in it,
	 * WHERE importance = 1 or name = given String
	 * GROUP BY competition name
	 * @param nameOfCompetitions
	 */
	@Query(value = "SELECT c.competition_name, count(m.id) as suma, avg(m.attendance) as srednia FROM competition c INNER JOIN "
			+ "match m ON c.id = m.competition_id "
			+ "WHERE c.competition_importance=1 OR c.competition_name LIKE %:name "
			+ "GROUP BY c.competition_name HAVING sum(m.attendance)>500", nativeQuery = true)
	List<Object[]> getMostPopularGames(@Param("name") String nameOfCompetitions);
	
	@Query(value = "SELECT m.away_club_id, avg(m.attendance), sum(m.attendance), " + 
			"RANK () OVER (ORDER BY avg(m.attendance) DESC) AS ranking " + 
			"FROM match as m "
			+"GROUP BY m.away_club_id "
			+ "ORDER BY m.away_club_id", nativeQuery = true)
	List<Object[]> rankOver();
}
