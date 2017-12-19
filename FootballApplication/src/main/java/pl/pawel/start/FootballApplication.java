package pl.pawel.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pl.pawel")
public class FootballApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);
	}
}
