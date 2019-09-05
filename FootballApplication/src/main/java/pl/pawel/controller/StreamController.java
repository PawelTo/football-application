package pl.pawel.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pawel.model.Match;
import pl.pawel.service.MatchCompare;

@Controller
public class StreamController {

	@ResponseBody
	@GetMapping("/getHomeWinners")
	public List<Match> getFilteredMatches() {
		List<Match> matchesToFilter = new LinkedList<>();
		for(int i=0;i<40;i++)
			matchesToFilter.add(new Match(null, null, null, i%4, i%3, null, i*123));
		return new MatchCompare().getHomeWinnerMatches(matchesToFilter);
	}
	
	@ResponseBody
	@GetMapping("/getHomeTeams")
	public List<Match> getHomeTeamsEditMatches(){
		List<Match> matchesToEdit = new LinkedList<>();
		for(int i=0;i<10;i++)
			matchesToEdit.add(new Match(null, null, null, i%4, i%3, null, i*123));
		return new MatchCompare().setMyClubsName(matchesToEdit);
	}
	
	@ResponseBody
	@GetMapping("/getSumGoals")
	public List<Integer> getSumGoals(){
		List<Match> matchesToEdit = new LinkedList<>();
		for(int i=0;i<10;i++)
			matchesToEdit.add(new Match(null, null, null, i%4, i%3, null, i*123));
		return new MatchCompare().sumGoals(matchesToEdit);
	}
	
	@ResponseBody
	@GetMapping("/getResultsMap")
	public Map<Integer,Match> getResultsMap(){
		List<Match> matchesToEdit = new LinkedList<>();
		for(int i=0;i<10;i++)
			matchesToEdit.add(new Match(null, null, null, i%4, i%3, null, i*123));
		return new MatchCompare().getMatchMap(matchesToEdit);
	}
}
