package pl.pawel.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import pl.pawel.model.Match;
import pl.pawel.service.dataFromWebSaver.MatchSaver;

@Controller
public class GetDataFromWebController {

	@Autowired
	private MatchSaver matchSaver;

	@RequestMapping("/getFlashscore")
	public String getDataFlashscore(HttpServletRequest request) {
		try {
			List<Match> listOfMatches = matchSaver.saveFlashscoreData();
			request.setAttribute("game", listOfMatches);
			return "showGames";
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
			return "start";
		}
	}
}