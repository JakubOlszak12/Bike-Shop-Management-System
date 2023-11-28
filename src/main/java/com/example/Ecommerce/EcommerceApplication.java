package com.example.Ecommerce;

import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Autowired
	ProductRepository productRepository;
	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {

		};
	}

}
