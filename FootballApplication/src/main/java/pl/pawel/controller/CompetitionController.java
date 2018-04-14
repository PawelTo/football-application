package pl.pawel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pl.pawel.model.Competition;
import pl.pawel.service.CompetitionService;

@Controller
public class CompetitionController {

	private static Logger logger = LoggerFactory.getLogger(CompetitionController.class);
	
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
	
	@GetMapping("/cquery")
	public String customQuery() {
		//method to test custom Query
		List<Competition> listOfCompetitions = competitionService.nameEndsWith("ka");
		for(Competition com : listOfCompetitions)
			logger.info("Competition from JPQL query: " + com);
		
		List<Object[]> listOfLeagues = competitionService.getMostPopularGames("ig");
		for(Object[] league :listOfLeagues)
			logger.info("Competition from Native SQL: Name: " + league[0] +" number of matches: " + league[1] + " average attendance: "+league[2]);
		
		List<Object[]> rankOver = competitionService.rankOver();
		for(Object[] rank :rankOver)
			logger.info("Native SQL: ID: " + rank[0] +" avg attendance: " + rank[1] +" total attendance: "+rank[2]+" rank: "+rank[3]);
		
		return "start";
	}
}
