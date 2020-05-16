package com.calarix.data.jpa;


import com.calarix.data.jpa.dsl.CustomerDslRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class JpaApplication {
	private static final Logger log = LoggerFactory.getLogger(JpaApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerDslRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Customer("Jack 1", "Bauer 1",
					"email1@gmail.com", LocalDate.now().minusMonths(1).minusYears(30)));
			repository.save(new Customer("Jack 2", "Bauer 2",
					"email2@gmail.com",LocalDate.now().minusYears(31)));
			repository.save(new Customer("Jack 3", "Bauer 3",
					"email3@gmail.com",LocalDate.now().minusYears(33)));
			repository.save(new Customer("Jack 4", "Bauer 4",
					"email4@gmail.com",LocalDate.now().minusYears(25)));
			repository.save(new Customer("Jack 5", "Bauer 5",
					"email5@gmail.com",LocalDate.now().minusMonths(2).minusYears(25)));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

		};
	}

}
