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
public class Club {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String clubName;
	private String stadiumName;
	private int stadiumCapacity;
	@OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Match> matchesAsHomeTeam;
	@OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Match> matchesAsAwayTeam;

	public Club() {
	}

	public Club(String clubName, String stadiumName, int stadiumCapacity) {
		this.clubName = clubName;
		this.stadiumName = stadiumName;
		this.stadiumCapacity = stadiumCapacity;
	}

	@Override
	public String toString() {
		return "Club [id=" + id + ", clubName=" + clubName + ", stadiumName=" + stadiumName + ", stadiumCapacity="
				+ stadiumCapacity + ", matchesAsHomeTeam=" + matchesAsHomeTeam + ", matchesAsAwayTeam="
				+ matchesAsAwayTeam + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getStadiumName() {
		return stadiumName;
	}

	public void setStadiumName(String stadiumName) {
		this.stadiumName = stadiumName;
	}

	public int getStadiumCapacity() {
		return stadiumCapacity;
	}

	public void setStadiumCapacity(int stadiumCapacity) {
		this.stadiumCapacity = stadiumCapacity;
	}

	public List<Match> getMatchesAsHomeTeam() {
		return matchesAsHomeTeam;
	}

	public void setMatchesAsHomeTeam(List<Match> matchesAsHomeTeam) {
		this.matchesAsHomeTeam = matchesAsHomeTeam;
	}

	public List<Match> getMatchesAsAwayTeam() {
		return matchesAsAwayTeam;
	}

	public void setMatchesAsAwayTeam(List<Match> matchesAsAwayTeam) {
		this.matchesAsAwayTeam = matchesAsAwayTeam;
	}
}
