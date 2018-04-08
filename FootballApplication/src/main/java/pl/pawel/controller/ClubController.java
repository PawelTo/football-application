package pl.pawel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pl.pawel.model.Club;
import pl.pawel.repository.ClubRepository;
import pl.pawel.service.ClubService;

@Controller
public class ClubController {

	@Autowired
	private ClubService clubService;
	
	@Autowired
	private ClubRepository cr;
	
	@GetMapping("/club")
	public String addDefaultClub() {
		//method will be removed - it is only temporary, before I will create input form in HTML
		System.out.println("Próba zapisu klubu");
		Club klub = new Club("Klub 1", "Stadion 1", 4000);
		clubService.save(klub);
		System.out.println("Zapisałem klub: " +klub);
		return "start";
	}
	@GetMapping("/club1")
	public String getOne() {
		System.out.println(cr.findOne((long) 1));
		System.out.println("znalazłem: "+clubService.findOne(1));
		return "start";
	}
}
