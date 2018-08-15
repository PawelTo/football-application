package pl.pawel.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("pl.pawel")
@EntityScan("pl.pawel")
@EnableJpaRepositories("pl.pawel")
public class FootballApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);
	}	
}
