package pl.pawel.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Match {

	// *Checking other type of strategy=GenerationType
	// *need to read more about generation value
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * LocalDate need additional dependency to deserialization, which isn't

	 * necessary for java.sql.Date and java.util.Date Annotation, which allow me to
	 * get date from thymeleaf input form
	 */
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateOfGame;
	@ManyToOne
	@JoinColumn(name = "home_club_id")
	private Club homeTeam;
	@ManyToOne
	@JoinColumn(name = "away_club_id")
	private Club awayTeam;
	private int homeTeamScore;
	private int awayTeamScore;
	@ManyToOne
	@JoinColumn(name = "competition_id")
	private Competition competition;
	private int attendance;

	// this constructor is only for JPA use
	// *need to read more
	// I changed protected to public constructor, because it's needed to be used in
	// controller
	public Match() {
	}

	// this constructor is used to create instance, which will be saved in database
	public Match(LocalDate dateOfGame, Club homeTeam, Club awayTeam, int homeTeamScore, int awayTeamScore,
			Competition competition, int attendance) {
		this.dateOfGame = dateOfGame;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
		this.competition = competition;
		this.attendance = attendance;
	}

	@Override
	public String toString() {
		return "Match " + "[id=" + id + ", dateOfGame=" + dateOfGame + ", homeTeam=" + homeTeam.getId() + ", awayTeam=" + awayTeam.getId()
				+ ", homeTeamScore=" + homeTeamScore + ", awayTeamScore=" + awayTeamScore + ", competition="
				+ competition.getId() + ", attendance=" + attendance  + "]";
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

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public int getAttendance() {
		return attendance;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}

	public Club getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Club homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Club getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Club awayTeam) {
		this.awayTeam = awayTeam;
	}
}
