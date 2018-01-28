package pl.pawel.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Match {

	// Checking other type of strategy=GenerationType
	// need to read more about generation value
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate dateOfGame;
	private String homeTeam;
	private String awayTeam;
	private int homeTeamScore;
	private int awayTeamScore;

	// this constructor is only for JPA use
	// need to read more
	protected Match() {
	}

	// this constructor is used to create instance, which will be saved in database
	public Match(LocalDate dateOfGame, String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
		this.dateOfGame = dateOfGame;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
	}

	@Override
	public String toString() {
		return "Match: [id= " + id + ", dateOG= " + dateOfGame + ", hTeam= " + homeTeam + ", aTeam= " + awayTeam
				+ ", hTScore= " + homeTeamScore + ", aTScore= " + awayTeamScore + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateOfGame() {
		return dateOfGame;
	}

	public void setDateOfGame(LocalDate dateOfGame) {
		this.dateOfGame = dateOfGame;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getHomeTeamScore() {
		return homeTeamScore;
	}

	public void setHomeTeamScore(int homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}

	public int getAwayTeamScore() {
		return awayTeamScore;
	}

	public void setAwayTeamScore(int awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}
}
