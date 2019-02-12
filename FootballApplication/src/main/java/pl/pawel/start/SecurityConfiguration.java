package pl.pawel.start;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pl.pawel.service.security.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("User").password("USER").roles("USER")
		.and().withUser("Admin").password("ADMIN").roles("ADMIN");
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(new BCryptPasswordEncoder())
		.usersByUsernameQuery("select username, password, TRUE as enabled from auth_user_data where username=?")
		.authoritiesByUsernameQuery("select username, role from auth_user_data where username=?");
	}

	/* 
	 *Only login and register pages are visible for all, rest need login process
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login","/register","/createUser").permitAll()
		.antMatchers("/addNew").hasRole("ADMIN")
		.antMatchers("/*").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
		.anyRequest().authenticated() 
		.and().formLogin().loginPage("/login").failureUrl("/login")
		.and().exceptionHandling().accessDeniedPage("/accessDenied")
		;
	}
}
