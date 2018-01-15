package com.me.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceEurekaClient {

	public static void main(String[] args) {
		SpringApplication.run(ServiceEurekaClient.class, args);
	}
}