package com.wipro.sk.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BankingPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingPaymentServiceApplication.class, args);
	}

}
