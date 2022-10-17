package com.durys.jakub.accessmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AccessmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessmanagementApplication.class, args);
	}

}
