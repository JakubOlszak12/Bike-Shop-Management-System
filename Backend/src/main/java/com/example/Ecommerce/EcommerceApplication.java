package com.example.Ecommerce;

import com.example.Ecommerce.model.*;
import com.example.Ecommerce.repository.*;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

@SpringBootApplication
@ImportResource({"classpath*:application-context.xml"})
public class EcommerceApplication {
	private static final Logger logger = LogManager.getLogger(EcommerceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}



	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	@Bean
	CommandLineRunner commandLineRunner(BrandRepository brandRepository, ProductRepository productRepository, SizeRepository sizeRepository, CategoryRepository categoryRepository,
										RoleRepository roleRepository){
		return args -> {
			brandRepository.save(new Brand(null,"Giant", new ArrayList<>()));
			brandRepository.save(new Brand(null,"Kross", new ArrayList<>()));
			brandRepository.save(new Brand(null,"Romet", new ArrayList<>()));
			brandRepository.save(new Brand(null,"Canyon", new ArrayList<>()));
			brandRepository.save(new Brand(null,"Trek", new ArrayList<>()));

			sizeRepository.save(new Size(null,"M", new ArrayList<>()));
			sizeRepository.save(new Size(null,"S", new ArrayList<>()));
			sizeRepository.save(new Size(null,"L", new ArrayList<>()));
			sizeRepository.save(new Size(null,"XL", new ArrayList<>()));
			sizeRepository.save(new Size(null,"XS", new ArrayList<>()));
			categoryRepository.save(new Category(null, "Road bikes", new ArrayList<>()));
			categoryRepository.save(new Category(null, "Mountain bikes", new ArrayList<>()));
			categoryRepository.save(new Category(null, "Gravel bikes", new ArrayList<>()));
			categoryRepository.save(new Category(null, "Cross bikes", new ArrayList<>()));
			Random random = new Random();
			Lorem lorem = LoremIpsum.getInstance();

			for(int i = 1;i<300;i++){
				long brandId = 1+(random.nextInt(5));
				long sizeId = 1+(random.nextInt(5) );
				long categoryId = 1+(random.nextInt(4));
				Optional<Category> category = categoryRepository.findById(categoryId);
				Optional<Size> size = sizeRepository.findById(sizeId);
				Optional<Brand> brand = brandRepository.findById(brandId);
				Product product = new Product(null,"Rower "+i,lorem.getParagraphs(2, 6), random.nextInt(30), random.nextInt(2023- 2013 + 1)+ 2013,"fork " +i, "fork material "+i, "frame material "+i,"drive "+i, "front derailleur "+i,"rear derailleur "+i,"handle "+i, "crank "+i,"casette "+i,"brake "+i, "wheel "+i, "wheel "+i, "wheelSize "+i, "tire "+i, "pedals "+i, "saddle "+i, random.nextDouble(10000-4000+1)+4000, LocalDateTime.now(),LocalDateTime.now(),category.get(),size.get(),brand.get());
				productRepository.save(product);
			}
			roleRepository.save(new Role(null, "ROLE_USER"));
			roleRepository.save(new Role(null, "ROLE_ADMIN"));
		};
	}

}
