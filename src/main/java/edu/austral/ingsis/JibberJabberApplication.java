package edu.austral.ingsis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class JibberJabberApplication {

	public static void main(String[] args) {
		SpringApplication.run(JibberJabberApplication.class, args);
	}

}
