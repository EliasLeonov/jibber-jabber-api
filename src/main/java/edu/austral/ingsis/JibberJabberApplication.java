package edu.austral.ingsis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableJpaRepositories
@EnableSwagger2WebMvc
public class JibberJabberApplication {

	public static void main(String[] args) {
		SpringApplication.run(JibberJabberApplication.class, args);
	}

}
