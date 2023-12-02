package com.example.Ecommerce;

import com.example.Ecommerce.model.*;
import com.example.Ecommerce.repository.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}




	@Bean
	CommandLineRunner commandLineRunner(BrandRepository brandRepository, ProductRepository productRepository, SizeRepository sizeRepository, CategoryRepository categoryRepository){
		return args -> {
			brandRepository.save(new Brand(null,"Giant", new ArrayList<>()));
			sizeRepository.save(new Size(null,"M", new ArrayList<>()));
			categoryRepository.save(new Category(null, "Road bikes", new ArrayList<>()));
			Random random = new Random();
			Optional<Category> category = categoryRepository.findById(1L);
			Optional<Size> size = sizeRepository.findById(1L);
			Optional<Brand> brand = brandRepository.findById(1L);
			for(int i = 1;i<30;i++){
				Product product = new Product(null,"Rower "+i,"Description "+i, random.nextInt(30), random.nextInt(2023- 2013 + 1)+ 2013,"fork " +i, "fork material "+i, "frame material "+i,"drive "+i, "front derailleur "+i,"rear derailleur "+i,"handle "+i, "crank "+i,"casette "+i,"brake "+i, "wheel "+i, "wheel "+i, "wheelSize "+i, "tire "+i, "pedals "+i, "saddle "+i, random.nextDouble(10000-4000+1)+4000, LocalDateTime.now(),LocalDateTime.now(),category.get(),size.get(),brand.get());
				productRepository.save(product);
			}
		};
	}

}
