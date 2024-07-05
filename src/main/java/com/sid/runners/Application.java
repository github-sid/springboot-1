package com.sid.runners;

import com.sid.runners.user.User;
import com.sid.runners.user.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//application context -> Container for all the beans
//CommandLinerRunner -> Run after the application has started(After the application context has been created)

	@Bean
	CommandLineRunner runner(UserRestClient client){

		return args -> {

			List<User> users = client.findAll();
			System.out.println(users);
		};
	}

//	@Bean
//	CommandLineRunner runner(RunRepo runRepo){
//		return args -> {
//			Run run = new Run(1,"First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR);
//			runRepo.create(run);
//		};
//	}

}
