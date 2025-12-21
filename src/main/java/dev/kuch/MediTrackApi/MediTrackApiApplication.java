package dev.kuch.MediTrackApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MediTrackApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediTrackApiApplication.class, args);
	}

}
