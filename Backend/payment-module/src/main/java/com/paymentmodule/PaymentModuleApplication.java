package com.paymentmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class PaymentModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentModuleApplication.class, args);
	}

}
