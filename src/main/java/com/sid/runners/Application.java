package com.sid.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}




	//application context -> Container for all the beans
//CommandLinerRunner -> Run after the application has started(After the application context has been created)

//	@Bean
//	CommandLineRunner runner(RunRepo runRepo){
//		return args -> {
//			Run run = new Run(1,"First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR);
//			runRepo.create(run);
//		};
//	}

}
