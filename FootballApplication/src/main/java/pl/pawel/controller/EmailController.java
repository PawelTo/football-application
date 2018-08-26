package pl.pawel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.pawel.service.email.Email;
import pl.pawel.service.email.EmailSenderImplementation;

@Controller
public class EmailController {

	@Autowired
	EmailSenderImplementation em;
	
	@RequestMapping("/sendEmail")
	public String sendEmail(@ModelAttribute Email email, HttpServletRequest request) {
		//em = new EmailSenderImplementation();
		String reciverAddress = request.getParameter("address");
		String emailSubject = request.getParameter("subject");
		String emailMessage = request.getParameter("message");
		System.out.println("rec: "+reciverAddress+ " sub: "+emailSubject+ " mes: "+emailMessage);
		em.sendEmail("spring.tutorial@o2.pl", "Email subject", "Dummy mail");
		em.sendEmail(email);
		return "start";
	}
	
	@RequestMapping("/email")
	public String prepareEmail(Model model) {
		model.addAttribute("email", new Email());
		return "sendEmail";
	}
}
