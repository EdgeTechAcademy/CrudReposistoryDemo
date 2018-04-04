package edu.edgetech.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.config.CustomRepositoryImplementationDetector;

@SpringBootApplication
public class DatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			repository.save(new Customer("Han", "Solo"));
			repository.save(new Customer("Luke", "Skywalker"));
			repository.save(new Customer("Princess", "Leia"));
			repository.save(new Customer("C3PO", ""));
			repository.save(new Customer("Chewbacca", ""));
			repository.save(new Customer("Obe wan", "Kennobe"));
			Customer r2d2 = new Customer("R2D2", "");
			repository.save(r2d2);

			for (Customer cust : repository.findAll()) {
				System.out.println(cust);
			}
			System.out.println();
			try {
				repository.delete(r2d2);
				repository.deleteById(1);
			} catch (EmptyResultDataAccessException ex) {
				System.out.println(ex);
			}
			r2d2.setLastName("No last name");
			repository.save(r2d2);
			for (Customer cust : repository.findAll()) {
				System.out.println(cust);
			}
			System.out.println();
			for (Customer cust : repository.findByLastName("")) {
				System.out.println(cust);
			}
		};
	}
}
