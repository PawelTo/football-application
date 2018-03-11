package pl.pawel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pl.pawel.model.Competition;
import pl.pawel.service.CompetitionService;

@Controller
public class CompetitionController {

	private CompetitionService competitionService;

	@Autowired
	public CompetitionController(CompetitionService competitionService) {
		this.competitionService = competitionService;
	}
	
	@GetMapping("/competition")
	public String addDefaultCompetition() {
		//method will be removed - it is only temporary, before I will create input form in HTML
		System.out.println("Próba zapisu rozgrywek");
		Competition zawody = new Competition("Liga Mistrzów", 10);
		competitionService.save(zawody);
		System.out.println("Zapisałem zawody: "+zawody);
		return"start";
	}
}
