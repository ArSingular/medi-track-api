package dev.kuch.MediTrackApi;

import org.springframework.boot.SpringApplication;

public class TestMediTrackApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(MediTrackApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
