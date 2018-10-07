package pl.pawel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pawel.service.MatchService;
import pl.pawel.service.competitionPointsCount.CompetitionPointsCount;
import pl.pawel.service.competitionPointsCount.LeagueTableRow;

@Controller
public class CompetitionTableController {

	@Autowired
	private CompetitionPointsCount competitionPointsCounter;
	
	@GetMapping("/zawody")
	public String getNumberOfMatches() {
		int noMatches = competitionPointsCounter.getMatchesFromCompetition();
		System.out.println("Number of Matches: "+noMatches);
		System.out.println("Lista meczy from Controller:");
		List<LeagueTableRow> table =competitionPointsCounter.getTableByCompetitionId(5);
		for(LeagueTableRow record : table)
			System.out.println(record);
		return "start";
	}
	
	@GetMapping("/zawody2")
	@ResponseBody
	public List<LeagueTableRow> getTable(){
		return competitionPointsCounter.getTableByCompetitionId(5);
	}
}
