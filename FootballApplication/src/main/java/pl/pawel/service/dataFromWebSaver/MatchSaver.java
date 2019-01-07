package pl.pawel.service.dataFromWebSaver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import pl.pawel.model.Match;
import pl.pawel.service.MatchService;

@Service
public class MatchSaver {

	@Autowired
	private MatchService matchService;
	
	@Autowired
	private MatchFromWebDataCreator matchFromWebData;
	
	public List<Match> saveFlashscoreData() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		List<Match> returnedMatches = new LinkedList<>();
		FlashscoreMatchReader flashMR = new FlashscoreMatchReader();
		List<Element> competitions = flashMR.getCompetitionsElements(flashMR.getFlashscorePage());
		List<FlashscoreData> flashscoreData = flashMR.getFlashscoreData(competitions);
		for (FlashscoreData flashscoreMatch : flashscoreData) {
			Match match = matchFromWebData.createFlashScoreMatch(flashscoreMatch);
			matchService.save(match);
			returnedMatches.add(match);
		}
		return returnedMatches;
	}
}
