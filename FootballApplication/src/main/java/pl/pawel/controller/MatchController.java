package pl.pawel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
