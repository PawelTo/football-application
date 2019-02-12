package pl.pawel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.pawel.service.security.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
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
	
	/**Method to return accessDenied HTML page
	 * @return
	 */
	@RequestMapping("/accessDenied")
	public String accessDeniedPage() {
		return "accessDenied";
	}
	
	@RequestMapping("/register")
	public String createNewUser() {
		return "register";
	}
	
	@PostMapping("/createUser")
	public String afterUserCreation(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmedPassword = request.getParameter("confirm_password");
		userService.createUser(username, password, confirmedPassword);
		return "login";
	}
}
