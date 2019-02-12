package pl.pawel.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.pawel.model.User;
import pl.pawel.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserValidator userValidator;

	public void createUser(String username, String password, String confirmedPassword) {
		if (userValidator.validate(username, password, confirmedPassword))
			saveUser(username, password);
		else {
			
		}
	}

	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	private void saveUser(String username, String password) {
		User createdUserRole = new User();
		createdUserRole.setUsername(username);
		createdUserRole.setPassword(new BCryptPasswordEncoder().encode(password));
		createdUserRole.setRole("ROLE_USER");
		userRepository.save(createdUserRole);
	}
}
