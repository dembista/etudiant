package com.example.projetEtudiant;

import com.example.projetEtudiant.fixtures.MoisFixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProjetEtudiantApplication extends SpringBootServletInitializer implements CommandLineRunner {
	@Autowired
	private MoisFixture moisFixture;

	@Override
	public void run(String... args) throws Exception {
		moisFixture.addDefaultMois();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetEtudiantApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

}
