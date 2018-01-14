package pl.pawel.model;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Match {

	@Id
	private Long id;
	private LocalDate dateOfGame;
	private String homeTeam;
	private String awayTeam;
	private int homeTeamScore;
	private int awayTeamScore;
}
