package com.anudeepreddy.JobPortal_EurekaService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class JobPortalEurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalEurekaServiceApplication.class, args);
	}

}
