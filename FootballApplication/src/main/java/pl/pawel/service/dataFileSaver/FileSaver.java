package pl.pawel.service.dataFileSaver;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pawel.model.Match;
import pl.pawel.service.MatchService;

@Service
public class FileSaver {
	
	IFileSaver fileSaver;
	
	@Autowired
	MatchService matchService;
	
	public void saveMatchToFile(String typeOfFile, String[] games) {
		fileSaver = this.selectFileType(typeOfFile);
		fileSaver.saveListOfMatches(StringToMatchesList(games));
	}
	
	private IFileSaver selectFileType(String typeOfFile) {
		switch (typeOfFile) {
		case "txt":
			fileSaver = new TXTFileSaver();
			break;
		case "csv":
			fileSaver = new CSVFileSaver();
			break;
		case "xlsx":
			fileSaver = new ExcelFileSaver();
			break;
		default:
			fileSaver = new ExcelFileSaver();
			break;
		}
		return fileSaver;
	}
	
	private List<Match> StringToMatchesList(String[] gamesFromRequest) {
		List<Match> gamesToSave = new LinkedList<>();
		for (String game : gamesFromRequest) {
			gamesToSave.add(matchService.findMatchByID(Long.parseLong(game)));
		}
		return gamesToSave;
	}

}
