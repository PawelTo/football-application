package pl.pawel.service.dataFileSaver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import pl.pawel.model.Match;

public class TXTFileSaver implements IFileSaver {

	private String separator = "	";
	private String endOfLineMark = "\n";
	private String path = "C:\\Users\\Pawel\\Desktop\\DirToLearn\\Saved_files\\";
	private String fileName = "CreatedFile.txt";
	private FileWriter fileWriter;
	StringBuilder sb;

	@Override
	public void saveMatch(Match game) {
		sb = new StringBuilder();
		sb.append("home Team").append(separator).append("Away Team").append(separator).append("Result")
				.append(endOfLineMark);
		sb.append(matchToString(game));
		try {
			fileWriter = new FileWriter(path + fileName);
			fileWriter.write(sb.toString());
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String matchToString(Match game) {
		StringBuilder tempSb = new StringBuilder();
		tempSb.append(game.getHomeTeam().getClubName()).append(separator).append(game.getAwayTeam().getClubName())
				.append(separator).append(game.getHomeTeamScore()).append(":").append(game.getAwayTeamScore()).append(endOfLineMark);
		return tempSb.toString();
	}

	@Override
	public void saveListOfMatches(List<Match> listOfGames) {
		// TODO Auto-generated method stub
		sb = new StringBuilder();
		sb.append("home Team").append(separator).append("Away Team").append(separator).append("Result")
				.append(endOfLineMark);
		for (Match game : listOfGames) {
			sb.append(matchToString(game));
		}
		try {
			fileWriter = new FileWriter(path + fileName);
			fileWriter.write(sb.toString());
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
