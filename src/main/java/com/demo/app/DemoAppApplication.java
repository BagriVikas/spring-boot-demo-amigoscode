package com.demo.app;

import com.demo.app.dto.StudentRegistrationData;
import com.demo.app.entity.Student;
import com.demo.app.service.StudentService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class DemoAppApplication {

	@Autowired
	private StudentService studentService;

	public static void main(String[] args) {
		SpringApplication.run(DemoAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Faker faker = new Faker();
				String firstName = faker.name().firstName();
				String lastName = faker.name().lastName();
				Random random = new Random();
				Student student = new Student(
						firstName + " " + lastName,
						firstName + "." + lastName + "@test.com",
						random.nextInt(21, 89)
				);
				studentService.saveStudent(new StudentRegistrationData(student.getName(), student.getEmail(), student.getAge()));
			}
		};
	}

}
