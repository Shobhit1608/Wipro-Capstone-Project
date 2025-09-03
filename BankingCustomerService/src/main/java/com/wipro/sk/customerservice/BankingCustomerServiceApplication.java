package com.wipro.sk.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BankingCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingCustomerServiceApplication.class, args);
	}

}
