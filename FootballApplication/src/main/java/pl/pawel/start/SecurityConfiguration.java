package pl.pawel.start;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


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
		.and().formLogin().loginPage("/login").failureUrl("/login").defaultSuccessUrl("/start")
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/register")
		.and().exceptionHandling().accessDeniedPage("/accessDenied")
		;
	}
}
