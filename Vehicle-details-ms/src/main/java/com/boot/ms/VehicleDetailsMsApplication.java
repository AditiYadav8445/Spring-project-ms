package com.boot.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class VehicleDetailsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleDetailsMsApplication.class, args);
	}
	
	@Bean
	public RestTemplate createRestTemplate() {
		return new RestTemplate();
	}

}
