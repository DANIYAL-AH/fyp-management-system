package com.fyp.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FypManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FypManagementSystemApplication.class, args);
	}

}
