package com.project.data_collection_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DataCollectionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataCollectionServiceApplication.class, args);
	}

}
