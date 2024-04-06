package com.demo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class DemoAppApplication {

//	@Autowired
//	private StudentService studentService;

	public static void main(String[] args) {
		SpringApplication.run(DemoAppApplication.class, args);
		List<Integer> list = new ArrayList<>();
		list.remove(9);
		Collections.sort(list);
	}

//	@Bean
//	CommandLineRunner commandLineRunner() {
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... args) throws Exception {
//				Faker faker = new Faker();
//				String firstName = faker.name().firstName();
//				String lastName = faker.name().lastName();
//				Random random = new Random();
//				Student student = new Student(
//						firstName + " " + lastName,
//						firstName + "." + lastName + "@test.com",
//						random.nextInt(21, 89)
//				);
//				studentService.saveStudent(new StudentRegistrationData(student.getName(), student.getEmail(), student.getAge()));
//			}
//		};
//	}

}
