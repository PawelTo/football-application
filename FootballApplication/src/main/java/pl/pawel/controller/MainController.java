package pl.pawel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/start")
	public String startPage() {
		return "start";
	}
	
	/**Method to return login HTML to test Spring Security - authorization
	 * @return
	 */
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
}
