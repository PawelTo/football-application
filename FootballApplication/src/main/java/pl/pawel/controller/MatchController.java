package pl.pawel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.pawel.model.Match;
import pl.pawel.service.MatchService;

@Controller
public class MatchController {

	/**
	 * I used dependency injection via field (to try other opportunity), despite the
	 * fact that in MatchService I used injection via constructor (It should be only one version of DI in project)
	 */
	@Autowired
	private MatchService matchService;

	//there will be other methods to transfer data to views
	
	//Get vs Post, ModelAttribute vs RequestParm
	@GetMapping("/wyniki")
	public String getMatches(HttpServletRequest request) {
		List<Match> listOfMatches = new ArrayList<>();
		listOfMatches = matchService.findAll();
		//*Remove that for... println
		for(Match game:listOfMatches)
			System.out.println("Wyniki: "+game.toString());
		request.setAttribute("message",listOfMatches);
		return "test";
	} 
}
