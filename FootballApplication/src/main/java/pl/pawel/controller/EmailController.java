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
		em.sendEmail(email);
		em.sendEmailWithAttachment(email,"C:\\Users\\Pawel\\Desktop\\Email.xls");
		return "start";
	}
	
	@RequestMapping("/email")
	public String prepareEmail(Model model) {
		model.addAttribute("email", new Email());
		return "sendEmail";
	}
}
