package com.devglan.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FirstServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstServiceApplication.class, args);
	}

}
