package com.digicheese.digi_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@RestController
public class DigiV2Application {

	private final JdbcTemplate jdbcTemplate;

	// 👉 Injection de JdbcTemplate
	public DigiV2Application(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(DigiV2Application.class, args);
	}

	@GetMapping("/")
	public String home() {
		return "OK à jour";
	}

	@GetMapping("/db")
	public String testDb() {
		Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
		return "Connexion BDD OK -> " + result;
	}
}