package pl.pawel.service;

import java.io.FileWriter;
import java.io.IOException;

import pl.pawel.model.Match;

/**Class that enable saving data to CSV file
 * @author Pawel
 *
 */
public class CSVFileSaver implements IFileSaver {

	private String separator = "	";
	private String endOfLineMark = "\n";
	private String path = "C:\\Users\\Pawel\\Desktop\\";
	private String fileName = "CreatedFIle.csv";
	private FileWriter fileWriter;

	@Override
	public void saveMatch(Match game) {
		String gameToSave = this.matchToString(game);
		try {
			fileWriter = new FileWriter(path + fileName);
			fileWriter.write(gameToSave);
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String matchToString(Match game) {
		StringBuilder sb = new StringBuilder();
		sb.append("home Team").append(separator).append("Away Team").append(separator).append("Result")
				.append(endOfLineMark);
		sb.append(game.getHomeTeam().getClubName()).append(separator).append(game.getAwayTeam().getClubName())
				.append(separator).append(game.getHomeTeamScore()).append(":").append(game.getAwayTeamScore());
		return sb.toString();
	}
}
