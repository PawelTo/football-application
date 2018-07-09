package pl.pawel.service.dataFileSaver;

import java.util.List;
import pl.pawel.model.Match;

public interface IFileSaver {

	public void saveMatch(Match game);
  
	public void saveListOfMatches(List<Match> listOfGames);
}
