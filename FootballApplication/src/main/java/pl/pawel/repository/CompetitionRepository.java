package pl.pawel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.pawel.model.Competition;

@Repository
public interface CompetitionRepository extends CrudRepository<Competition, Long>, CompetitionRepositoryCustom{

	Competition findByCompetitionName(String nameOfCompetition);
}
