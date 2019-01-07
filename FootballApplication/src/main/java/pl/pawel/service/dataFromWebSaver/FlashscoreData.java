package pl.pawel.service.dataFromWebSaver;

/**Model of data received from Flashscore
 * @author Pawel
 *
 */
public class FlashscoreData {

	private String homeTeam;
	private String awayTeam;
	private String competition;
	private int homeTeamScore;
	private int awayTeamScore;
	
	public FlashscoreData() {}
	
	public FlashscoreData(String homeTeam, String awayTeam, String competition, int homeTeamScore, int awayTeamScore) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.competition = competition;
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
	}
	
	@Override
	public String toString() {
		return "FlashscoreData [homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", competition=" + competition
				+ ", homeTeamScore=" + homeTeamScore + ", awayTeamScore=" + awayTeamScore + "]";
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

	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
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
