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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootApplication
@ImportResource({"classpath*:application-context.xml"})
public class EcommerceApplication {
	private static final Logger logger = LogManager.getLogger(EcommerceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

//	private String generateRandomString(Random random, int length) {
//		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < length; i++) {
//			sb.append(characters.charAt(random.nextInt(characters.length())));
//		}
//		return sb.toString();
//	}
//	private String generateRandomEmail(Random random) {
//		String[] domains = {"gmail.com", "yahoo.com", "hotmail.com"};
//		return generateRandomString(random, 6) + "@" + domains[random.nextInt(domains.length)];
//	}
//
//	private String generateRandomUsername(Random random) {
//		String[] NAMES = {"John", "Emily", "Michael", "Sarah", "David", "Jessica", "Daniel", "Ashley", "Matthew", "Samantha"};
//		String name = NAMES[random.nextInt(NAMES.length)]; // Select random name from array
//		int randomNumber = 100000 + random.nextInt(900000); // Generate a random 6-digit number
//		return name + randomNumber;
//	}
//
//	// Helper method to generate random dates within the past 2 years
//	private LocalDateTime generateRandomDate(Random random) {
//		LocalDateTime startDate = LocalDateTime.now().minusYears(2); // 2 years ago
//		long daysBetween = ChronoUnit.DAYS.between(startDate, LocalDateTime.now());
//		return startDate.plusDays(random.nextInt((int) daysBetween));
//	}
//
//
//
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
//	@Bean
//	CommandLineRunner commandLineRunner(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository, BrandRepository brandRepository, ProductRepository productRepository, SizeRepository sizeRepository, CategoryRepository categoryRepository,
//										RoleRepository roleRepository, PaymentMethodRepository paymentMethodRepository, StatusRepository statusRepository, UserRepository userRepository, DeliveryMethodRepository deliveryMethodRepository){
//		return args -> {
//
//			brandRepository.save(new Brand(null,"Giant", new ArrayList<>()));
//			brandRepository.save(new Brand(null,"Kross", new ArrayList<>()));
//			brandRepository.save(new Brand(null,"Romet", new ArrayList<>()));
//			brandRepository.save(new Brand(null,"Canyon", new ArrayList<>()));
//			brandRepository.save(new Brand(null,"Trek", new ArrayList<>()));
//			deliveryMethodRepository.save(new DeliveryMethod(null,"InPost", new ArrayList<>()));
//			deliveryMethodRepository.save(new DeliveryMethod(null,"DHL", new ArrayList<>()));
//			deliveryMethodRepository.save(new DeliveryMethod(null,"UPS", new ArrayList<>()));
//			sizeRepository.save(new Size(null,"M", new ArrayList<>()));
//			sizeRepository.save(new Size(null,"S", new ArrayList<>()));
//			sizeRepository.save(new Size(null,"L", new ArrayList<>()));
//			sizeRepository.save(new Size(null,"XL", new ArrayList<>()));
//			sizeRepository.save(new Size(null,"XS", new ArrayList<>()));
//			categoryRepository.save(new Category(null, "Road bikes", new ArrayList<>()));
//			categoryRepository.save(new Category(null, "Mountain bikes", new ArrayList<>()));
//			categoryRepository.save(new Category(null, "Gravel bikes", new ArrayList<>()));
//			categoryRepository.save(new Category(null, "Cross bikes", new ArrayList<>()));
//			paymentMethodRepository.save(new PaymentMethod(null,"Credit card", new ArrayList<>()));
//			paymentMethodRepository.save(new PaymentMethod(null,"Cash", new ArrayList<>()));
//			statusRepository.save(new Status(null,"pending", new ArrayList<>()));
//			statusRepository.save(new Status(null,"canceled", new ArrayList<>()));
//			statusRepository.save(new Status(null,"completed", new ArrayList<>()));
//
//			Random random = new Random();
//			Lorem lorem = LoremIpsum.getInstance();
//
//			int batchSize = 10000;  // Save 1000 products in each batch
//			List<Product> productsBatch = new ArrayList<>();
//
//			for (int i = 1; i <= 1000; i++) {
//				// Generate random IDs for category, size, and brand
//				long brandId = 1 + random.nextInt(5);
//				long sizeId = 1 + random.nextInt(5);
//				long categoryId = 1 + random.nextInt(4);
//
//				// Fetch entities from repositories
//				Optional<Category> category = categoryRepository.findById(categoryId);
//				Optional<Size> size = sizeRepository.findById(sizeId);
//				Optional<Brand> brand = brandRepository.findById(brandId);
//
//				// Ensure the entities exist before proceeding
//				if (category.isPresent() && size.isPresent() && brand.isPresent()) {
//					// Create a new Product with randomized data
//					Product product = new Product(
//							null,  // Product ID (null, since it's auto-generated)
//							"Rower " + i,
//							lorem.getParagraphs(2, 6),  // Random product description
//							random.nextInt(30),  // Random stock quantity
//							random.nextInt(2023 - 2013 + 1) + 2013,  // Random year between 2013 and 2023
//							"fork " + i,
//							"fork material " + i,
//							"frame material " + i,
//							"drive " + i,
//							"front derailleur " + i,
//							"rear derailleur " + i,
//							"handle " + i,
//							"crank " + i,
//							"cassette " + i,
//							"brake " + i,
//							"wheel " + i,
//							"wheel " + i,
//							"wheelSize " + i,
//							"tire " + i,
//							"pedals " + i,
//							"saddle " + i,
//							random.nextDouble() * (10000 - 4000) + 4000,  // Random price between 4000 and 10000
//							LocalDateTime.now(),  // Created timestamp
//							LocalDateTime.now(),  // Edited timestamp
//							category.get(),
//							size.get(),
//							brand.get()
//					);
//
//					// Add the product to the batch
//					productsBatch.add(product);
//				}
//
//				// Save the batch when it reaches batchSize or when the loop ends
//				if (productsBatch.size() == batchSize || i == 1000) {
//					productRepository.saveAll(productsBatch);
//					productsBatch.clear();  // Clear the batch for the next set of products
//				}
//			}
//			roleRepository.save(new Role(null, "ROLE_USER"));
//			roleRepository.save(new Role(null, "ROLE_ADMIN"));
//			roleRepository.save(new Role(null, "ROLE_SUPERUSER"));
//			Role userRole = roleRepository.findByName("ROLE_USER");
//
//			List<User> usersBatch = new ArrayList<>();
//
//			for (int i = 1; i <= 100000; i++) {
//				// Create a new user with random values
//				User user = new User();
//				user.setUsername(generateRandomUsername(random));
//				user.setPassword(generateRandomString(random, 10)); // Random 10-character password
//				user.setEmail(generateRandomEmail(random)); // Random email
//				user.setCreated_at(generateRandomDate(random)); // Random created date
//				user.setEdited_at(user.getCreated_at().plusDays(random.nextInt(10))); // Random edited date
//
//				// Assign ROLE_USER to the user
//				user.getRoles().add(userRole);
//
//				// Add to batch
//				usersBatch.add(user);
//
//				// Save the batch when the size reaches BATCH_SIZE or when the loop ends
//				if (usersBatch.size() == 1000 || i == 100000) {
//					userRepository.saveAll(usersBatch);
//					usersBatch.clear(); // Clear the batch for the next iteration
//				}
//			}
//			long userCount = userRepository.count(); // Get total number of users
//			long productCount = productRepository.count(); // Get total number of products
//			List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();
//			List<DeliveryMethod> deliveryMethods = deliveryMethodRepository.findAll();
//			List<Status> statuses = statusRepository.findAll();
//			List<Order> ordersBatch = new ArrayList<>();
//			List<OrderDetail> orderDetailsBatch = new ArrayList<>();
//			final int BATCH_SIZE = 10000; // Set your batch size
//
//			for (int i = 1; i <= 500000; i++) {
//				// Create a new order with randomized data
//				Order order = new Order();
//				order.setStreet("Street " + (random.nextInt(500)));
//				order.setPostalCode(String.format("%05d", random.nextInt(99999)));
//				order.setCity("City " + (random.nextInt(500)));
//				order.setPhoneNumber(String.format("123-456-%04d", random.nextInt(9999)));
//				order.setCreated_at(generateRandomDate(random)); // Random created date within a year
//
//				// Fetch random user
//				long randomUserId = 1 + random.nextInt((int) userCount); // Random ID from 1 to userCount
//				Optional<User> user = userRepository.findById(randomUserId);
//				user.ifPresent(order::setUser); // Set user if found
//
//				// Randomly assign payment method, delivery method, and status
//				order.setPaymentMethod(paymentMethods.get(random.nextInt(paymentMethods.size())));
//				order.setDeliveryMethod(deliveryMethods.get(random.nextInt(deliveryMethods.size())));
//				order.setStatus(statuses.get(random.nextInt(statuses.size())));
//
//				// Create OrderDetails based on random products from the database
//				int orderDetailsCount = 2 + random.nextInt(2); // Random number of order details (2 or 3)
//				List<OrderDetail> orderDetailsList = new ArrayList<>();
//				double totalOrderPrice = 0.0; // Initialize total price for the order
//
//				for (int j = 0; j < orderDetailsCount; j++) {
//					// Fetch random product
//					long randomProductId = 1 + random.nextInt((int) productCount); // Random ID from 1 to productCount
//					Optional<Product> product = productRepository.findById(randomProductId);
//
//					if (product.isPresent()) {
//						OrderDetail orderDetail = new OrderDetail();
//						int quantity = 1 + random.nextInt(5); // Random quantity between 1 and 5
//						double unitPrice = product.get().getPrice(); // Get the price from the product
//						double totalPrice = quantity * unitPrice;
//
//						orderDetail.setQuantity(quantity);
//						orderDetail.setUnitPrice(unitPrice);
//						orderDetail.setTotalPrice(totalPrice);
//						orderDetail.setProduct(product.get());
//						orderDetail.setOrder(order); // Associate with the current order
//
//						// Update the total price of the order
//						totalOrderPrice += totalPrice;
//						orderDetailsList.add(orderDetail);
//					}
//				}
//
//				// Set the total order price to be equal to the sum of order details' total prices
//				order.setPrice(Math.ceil(totalOrderPrice * 100) / 100);
//				order.setOrderDetailsList(orderDetailsList);
//
//				// Add the order and order details to their respective batches
//				ordersBatch.add(order);
//				orderDetailsBatch.addAll(orderDetailsList); // Add all details to the batch
//
//				// Save the batch when the size reaches BATCH_SIZE or when the loop ends
//				if (ordersBatch.size() >= BATCH_SIZE || i == 500000) {
//					// Save orders and then their details
//					orderRepository.saveAll(ordersBatch); // Save all orders in batch
//					orderDetailRepository.saveAll(orderDetailsBatch); // Save all order details in batch
//
//					// Clear the batches for the next iteration
//					ordersBatch.clear();
//					orderDetailsBatch.clear();
//				}
//
//				// Optional: Print out order details for confirmation
//				System.out.println("Order ID: " + order.getId() + ", Street: " + order.getStreet() +
//						", Postal Code: " + order.getPostalCode() + ", City: " + order.getCity() +
//						", Phone Number: " + order.getPhoneNumber() + ", Price: " + order.getPrice() +
//						", Created At: " + order.getCreated_at() +
//						", User ID: " + (order.getUser() != null ? order.getUser().getId() : "N/A") +
//						", Payment Method: " + order.getPaymentMethod().getId() +
//						", Delivery Method: " + order.getDeliveryMethod().getId() +
//						", Status: " + order.getStatus().getId());
//			}
//
//
//
//
//		};
	}


