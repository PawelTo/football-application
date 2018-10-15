package pl.pawel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pawel.service.MatchService;
import pl.pawel.service.competitionPointsCount.CompetitionPointsCount;
import pl.pawel.service.competitionPointsCount.LeagueTableRow;

@Controller
public class CompetitionTableController {

	@Autowired
	private CompetitionPointsCount competitionPointsCounter;
	
	@GetMapping("/table")
	@ResponseBody
	public List<LeagueTableRow> getTable(@RequestParam(defaultValue = "1", required = true, name = "id")int id){
		return competitionPointsCounter.getTableOfCompetitoin(id);
	}
}
