package pl.pawel.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Competition {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String competitionName;
	private int competitionImportance;
	@OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Match> games;
	
	
	public Competition() {
	}
	
	public Competition(String competitionName, int competitionImportance) {
		this.competitionName = competitionName;
		this.competitionImportance = competitionImportance;
	}

	@Override
	public String toString() {
		return "Competition [id=" + id + ", competitionName=" + competitionName + ", competitionImportance="
				+ competitionImportance + ", games=" + games + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}

	public int getCompetitionImportance() {
		return competitionImportance;
	}

	public void setCompetitionImportance(int competitionImportance) {
		this.competitionImportance = competitionImportance;
	}

	public List<Match> getGames() {
		return games;
	}

	public void setGames(List<Match> games) {
		this.games = games;
	}
}
