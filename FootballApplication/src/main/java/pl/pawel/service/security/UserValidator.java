package pl.pawel.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.pawel.model.User;

@Component
public class UserValidator {

	@Autowired
	private UserService userService;
	
	public UserValidator() {}
	
	public boolean validate(String username, String password, String confirmedPassword) {
		if(!password.equals(confirmedPassword))
			return false;	
		User existingUser = userService.findUserByUsername(username);
		if(existingUser != null)
			return false;
		return true;
	}
}
