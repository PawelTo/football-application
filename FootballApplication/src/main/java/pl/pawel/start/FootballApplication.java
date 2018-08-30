package pl.pawel.start;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import pl.pawel.service.fileUpload.StorageProperties;
import pl.pawel.service.fileUpload.StorageService;

@SpringBootApplication
@ComponentScan("pl.pawel")
@EntityScan("pl.pawel")
@EnableJpaRepositories("pl.pawel")
@EnableConfigurationProperties(StorageProperties.class)
public class FootballApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
