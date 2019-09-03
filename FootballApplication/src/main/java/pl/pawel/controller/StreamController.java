package pl.pawel.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pl.pawel.model.Match;
import pl.pawel.service.MatchCompare;

@Controller
public class StreamController {

	@GetMapping("/filterMatches")
	public List<Match> getFilteredMatches() {
		List<Match> matchesToFilter = new LinkedList<>();
		for(int i=0;i<40;i++)
			matchesToFilter.add(new Match(null, null, null, i%4, i%3, null, i*123));
		return new MatchCompare().getHomeWinnerMatches(matchesToFilter);
	}
}
