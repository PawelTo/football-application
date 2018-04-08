package pl.pawel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.pawel.model.Match;
import pl.pawel.service.MatchFromData;
import pl.pawel.service.MatchService;
import pl.pawel.service.ReadFile;

// If you send data to HTML views via HttpServletRequest.setAttribute the name
// have to be the same as the name of the object in HTML file, if you use
// Model.addAttribute - without a name of object, the name of the object in HTML
// file have to be the same like Entity name, or you can define name be using
// name attribute (like in the setAttribute)

@Controller
public class MatchController {

	private static Logger logger = LoggerFactory.getLogger(MatchController.class);
	/**
	 * I used dependency injection via field (to try other opportunity), despite the
	 * fact that in MatchService I used injection via constructor (It should be only
	 * one version of DI in project)
	 */
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private MatchFromData mfData;

	// Get vs Post, ModelAttribute vs RequestParm

	@GetMapping("/all")
	public String getAllMatches(HttpServletRequest request) {
		// method to show all Matches from DB
		List<Match> listOfMatches = new ArrayList<>();
		listOfMatches = matchService.findAll();
		logger.info("List of Games: "+listOfMatches);
		request.setAttribute("game", listOfMatches);
		return "showGames";
	}

	@GetMapping("/addNew")
	public String createMatch(Model model) {
		// works only with public Match constructor
		model.addAttribute("match", new Match());
		return "createNew";
	}

	@GetMapping("/addNew2")
	public String createMatch2(Match match) {
		// the same method as createMatch, but it doesn't need public constructor
		return "createNew";
	}

	@PostMapping("/save")
	public String saveMatchFromWebForm(@ModelAttribute Match match, HttpServletRequest request) {
		// method to save game
		System.out.println("Próba zapisu obiektu");
		System.out.println(match);
		matchService.save(match);
		request.setAttribute("game", match);
		return "showGames";
	}

	@GetMapping("/editGame")
	public String editMatch(Model model) {
		// method to edit game
		// value of editing id=6 is only temporary, later it will be set by user
		Match gameToUpdate = matchService.findMatchByID(6);
		model.addAttribute("match", gameToUpdate);
		return "editGame";
	}
	
	@GetMapping("/file")
	public String readGameFromFile() {
		//method to test read data from file, later it will file can be selected
		List<String[]> dataFromFile = new ReadFile().getDataFromFile();
		for(int i =1;i<dataFromFile.size();i++) {
			mfData.setDataFromFile(dataFromFile.get(i));
			Match game = mfData.createMatch();
			logger.info("Row - "+i + " - match: "+game);
			matchService.save(game);
		}
		return "start";
	}
}
