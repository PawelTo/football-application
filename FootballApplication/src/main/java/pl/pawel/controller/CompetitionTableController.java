package pl.pawel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pawel.model.Competition;
import pl.pawel.service.CompetitionService;
import pl.pawel.service.MatchService;
import pl.pawel.service.competitionPointsCount.CompetitionPointsCount;
import pl.pawel.service.competitionPointsCount.LeagueTableRow;
import pl.pawel.service.email.Email;

@Controller
public class CompetitionTableController {

	@Autowired
	private CompetitionPointsCount competitionPointsCounter;
	
	@Autowired
	private CompetitionService competitionService;
	
	@GetMapping("/tableSelect")
	public String selectTableCompetition(Model model) {
		List<Competition> listOfCompetition = (List<Competition>) competitionService.findAll();
		model.addAttribute("competitionList", listOfCompetition);
		//default value of League table to display data
		model.addAttribute("table", competitionPointsCounter.getTableOfCompetition(1));
		return "tableSelect";
	}
	
	@GetMapping("/table")
	@ResponseBody
	public List<LeagueTableRow> getTable(@RequestParam(defaultValue = "1", required = true, name = "id")int id){
		return competitionPointsCounter.getTableOfCompetition(id);
	}
}
