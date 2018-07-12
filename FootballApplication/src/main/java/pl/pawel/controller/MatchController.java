package pl.pawel.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pawel.model.Club;
import pl.pawel.model.Match;
import pl.pawel.service.MatchFromData;
import pl.pawel.service.MatchService;
import pl.pawel.service.ReadFile;
import pl.pawel.service.dataFileSaver.CSVFileSaver;
import pl.pawel.service.dataFileSaver.ExcelFileFactory;
import pl.pawel.service.dataFileSaver.ExcelFileSaver;
import pl.pawel.service.dataFileSaver.FileSaver;
import pl.pawel.service.dataFileSaver.IFileSaver;
import pl.pawel.service.dataFileSaver.IFileSaverFactory;
import pl.pawel.service.dataFileSaver.TXTFileSaver;

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
	
	@Autowired
	private FileSaver file;

	@GetMapping("/all")
	public String getAllMatches(HttpServletRequest request) {
		// method to show all Matches from DB
		List<Match> listOfMatches = new ArrayList<>();
		listOfMatches = matchService.findAll();
		logger.info("List of Games: " + listOfMatches);
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

	/**
	 * Method to edit match
	 * 
	 * @param id    - of game to edit - defaultValue = 1
	 * @param model
	 * @return
	 */
	@GetMapping("/editGame")
	public String editMatch(@RequestParam(defaultValue = "1", required = true, name = "id") int id, Model model) {
		Match gameToUpdate = matchService.findMatchByID(id);
		model.addAttribute("match", gameToUpdate);
		return "editGame";
	}

	/**
	 * TODO - need to improve Method to read data from file
	 * 
	 * @return
	 */
	@GetMapping("/file")
	public String readGameFromFile() {
		// method to test read data from file, later it will file can be selected
		List<String[]> dataFromFile = new ReadFile().getDataFromFile();
		for (int i = 1; i < dataFromFile.size(); i++) {
			mfData.setDataFromFile(dataFromFile.get(i));
			Match game = mfData.createMatch();
			logger.info("Row - " + i + " - match: " + game);
			matchService.save(game);
		}
		return "start";
	}

	/**
	 * TODO - need to improve, temporary method to test saving data
	 * 
	 * @return
	 */
	@GetMapping("/csv")
	public String saveToCSV() {
		CSVFileSaver fileToSaveData = new CSVFileSaver();
		Match game = matchService.findMatchByID(7);
		fileToSaveData.saveMatch(game);
		TXTFileSaver fileToSave = new TXTFileSaver();
		fileToSave.saveMatch(game);
		ExcelFileSaver excelToSave = new ExcelFileSaver();
		excelToSave.saveMatch(game);
		return "start";
	}

	/**
	 * TODO - need to improve, temporary metod to test saving list of data
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping("/csv2")
	public String saveToFile(HttpServletRequest request) {
		List<Match> listOfGames = new ArrayList<>();
		for (int i = 1; i < 11; i++) {
			listOfGames.add(matchService.findMatchByID(i));
		}
		request.setAttribute("game", listOfGames);
		ExcelFileSaver excelToSave = new ExcelFileSaver();
		excelToSave.saveListOfMatches(listOfGames);
		CSVFileSaver cSVToSave = new CSVFileSaver();
		cSVToSave.saveListOfMatches(listOfGames);
		TXTFileSaver tXTToSave = new TXTFileSaver();
		tXTToSave.saveListOfMatches(listOfGames);
		return "showGamesPick";
	}

	@PostMapping("/saveGamesToFile")
	public String saveGamesToFile(HttpServletRequest request) {
		String[] gamesFromRequest = request.getParameterValues("id");
		String fileKind = request.getParameter("fileKind");
		
		file.saveMatchToFile(fileKind, gamesFromRequest);
		return "start";
	}
	
	/**Temporary method to try ResponseBody
	 * @return
	 */
	@ResponseBody
	@GetMapping("/testResponse")
	public Club testOfResponseBody() {
		return new Club("ClubFromRest", "Stadium From Rest", 1234);
	}
	
	/**Temporary method to try ResponseBody
	 * this method is only to call /testResponse via jsonClub.js
	 * @return
	 */
	@GetMapping("/rest")
	public String testOfR() {
		return "restClub";
	}
}
