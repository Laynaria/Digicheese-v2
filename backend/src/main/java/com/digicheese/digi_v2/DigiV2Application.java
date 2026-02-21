package com.digicheese.digi_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DigiV2Application {

	public static void main(String[] args) {
		SpringApplication.run(DigiV2Application.class, args);
	}


	@GetMapping("/")
	public String home() {
		return "OK à jour";
	}

}
