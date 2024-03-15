package com.demo.app;

import com.demo.app.entity.Student;
import com.demo.app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoAppApplication {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Student student1 = new Student("vikas", "vikas@test.com", 22);
				Student student2 = new Student("bikas", "bikas@test.com", 25);
				studentRepository.saveAll(List.of(student1, student2));
			}
		};
	}

}
