package com.example.PROJETWEBENICAR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProjetwebenicarApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetwebenicarApplication.class, args);
	}
	@GetMapping(value="/")
	public String hello() {
		return "Hello world";
	}

}
