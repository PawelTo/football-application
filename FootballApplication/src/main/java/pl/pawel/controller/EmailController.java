package pl.pawel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.pawel.service.email.EmailSenderImplementation;

@Controller
public class EmailController {

	@Autowired
	EmailSenderImplementation em;
	
	@RequestMapping("/email")
	public String sendEmail() {
		//em = new EmailSenderImplementation();
		em.sendEmail("spring.tutorial@o2.pl", "Email subject", "Dummy mail");
		return "start";
	}
}
