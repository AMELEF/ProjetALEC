package fr.imt.flinois.projetALEC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjetAlecApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetAlecApplication.class, args);
	}

}
