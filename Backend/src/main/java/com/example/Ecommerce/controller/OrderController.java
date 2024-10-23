package com.example.Ecommerce.controller;

import com.example.Ecommerce.Helpers.PerformanceMetrics;
import com.example.Ecommerce.model.*;
import com.example.Ecommerce.repository.DeliveryMethodRepository;
import com.example.Ecommerce.repository.PaymentMethodRepository;
import com.example.Ecommerce.repository.StatusRepository;
import com.example.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Transactional
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private static final String[] STREETS = {"Main St", "Second St", "Third St", "Fourth St", "Fifth Ave"};
    private static final String[] CITIES = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix"};
    private static final String[] POSTAL_CODES = {"10001", "90001", "60601", "77001", "85001"};
    private static final String[] PHONE_NUMBERS = {"1234567890", "9876543210", "5555555555", "4444444444", "1111111111"};

    private final Random random = new Random();
    private final PaymentMethodRepository paymentMethodRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final DeliveryMethodRepository deliveryMethodRepository;

    // Endpoint for small dataset (100 records)
    @GetMapping("/smallRead")
    public ResponseEntity<InputStreamResource> getSmallOrders() throws IOException {
        return measurePerformance(100, "small_read_results.txt","read");
    }

    @GetMapping("/read")
    public List<Order> getLast100Orders() {
        return orderService.getOrders100();
    }

    // Endpoint for creating a small dataset (100 records)
    @PostMapping("/createSmall")
    public ResponseEntity<InputStreamResource> createSmallOrders() throws IOException {
        return measurePerformance(100, "create_small_order_results.txt", "write");
    }

    // Endpoint for creating a large dataset (100,000 records)
    @PostMapping("/createLarge")
    public ResponseEntity<InputStreamResource> createLargeOrders() throws IOException {
        return measurePerformance(100000, "create_large_order_results.txt", "write");
    }

    // Endpoint for large dataset (100,000 records)
    @GetMapping("/largeRead")
    public ResponseEntity<InputStreamResource> getLargeOrders() throws IOException {
        return measurePerformance(100000, "large_read_results.txt", "read");
    }

    // Endpoint for small dataset (100 records) deletion
    @DeleteMapping("/deleteSmall")
    public ResponseEntity<InputStreamResource> deleteSmallOrders() throws IOException {
        return measurePerformance(100, "delete_small_order_results.txt", "delete");
    }

    // Endpoint for large dataset (100,000 records) deletion
    @DeleteMapping("/deleteLarge")
    public ResponseEntity<InputStreamResource> deleteLargeOrders() throws IOException {
        return measurePerformance(100000, "delete_large_order_results.txt", "delete");
    }

    // Endpoint to update prices for the first 100 records (10% reduction)
    @PutMapping("/smallUpdate")
    public ResponseEntity<InputStreamResource> updateSmallOrders() throws IOException {
        return measurePerformance(100, "small_update_results.txt", "update");
    }

    // Endpoint to update prices for the first 100,000 records (10% reduction)
    @PutMapping("/largeUpdate")
    public ResponseEntity<InputStreamResource> updateLargeOrders() throws IOException {
        return measurePerformance(100000, "large_update_results.txt", "update");
    }

    // AVG price endpoints
    @GetMapping("/smallAvgPrice")
    public ResponseEntity<InputStreamResource> calculateSmallAvgPrice() throws IOException {
        return measurePerformance(100,"small_avg_price_results.txt", "avg");
    }

    @GetMapping("/largeAvgPrice")
    public ResponseEntity<InputStreamResource> calculateLargeAvgPrice() throws IOException {
        return measurePerformance(100000,"large_avg_price_results.txt", "avg");
    }

    // COUNT endpoints
    @GetMapping("/smallCountOrders")
    public ResponseEntity<InputStreamResource> calculateSmallCountOrders() throws IOException {
        return measurePerformance(100,"small_count_results.txt", "count");
    }

    @GetMapping("/largeCountOrders")
    public ResponseEntity<InputStreamResource> calculateLargeCountOrders() throws IOException {
        return measurePerformance(100000,"large_count_results.txt", "count");
    }

    // SUM price endpoints
    @GetMapping("/smallSumPrice")
    public ResponseEntity<InputStreamResource> calculateSmallSumPrice() throws IOException {
        return measurePerformance( 100,"small_sum_price_results.txt", "sum");
    }

    @GetMapping("/largeSumPrice")
    public ResponseEntity<InputStreamResource> calculateLargeSumPrice() throws IOException {
        return measurePerformance(100000,"large_sum_price_results.txt",  "sum");
    }

    // Measure performance, save total response times to file over 101 iterations (ignore the first)
    private ResponseEntity<InputStreamResource> measurePerformance(int count, String fileName, String type) throws IOException {
        List<Long> totalTimes = new ArrayList<>();
            PaymentMethod paymentMethod = paymentMethodRepository.findById(1L).get();
            Status status = statusRepository.findById(1L).get();
            User user = userRepository.findById(1L).get();
            DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(1L).get();


        Instant overallStart = Instant.now();
        // Perform 101 iterations, but ignore the first result
        for (int i = 0; i < 102; i++) {
            Instant start = Instant.now();
            System.out.println("#####################################################Progress: " + i + "/" + 100 +" #####################################################");
            switch (type) {
                case "avg":
                    if (count == 100) {
                        orderService.calculateAvgPriceForFirst100(); // AVG for 100 records
                    } else if (count == 100000) {
                        orderService.calculateAvgPriceForFirst100000(); // AVG for 100,000 records
                    }
                    break;
                case "count":
                    if (count == 100) {
                        orderService.calculateCountForFirst100(); // COUNT for 100 records
                    } else if (count == 100000) {
                        orderService.calculateCountForFirst100000(); // COUNT for 100,000 records
                    }
                    break;
                case "sum":
                    if (count == 100) {
                       orderService.calculateSumPriceForFirst100(); // SUM for 100 records
                    } else if (count == 100000) {
                        orderService.calculateSumPriceForFirst100000(); // SUM for 100,000 records
                    }
                    break;
            }
            // Simulate data read for the given dataset size (small/large)
            if (count == 100 && Objects.equals(type, "read")) {
                orderService.getOrders100();
            } else if (count == 100000 && Objects.equals(type, "read")) {
                orderService.getOrders100000();
            }
            else if (Objects.equals(type, "write")) {
                // Create a batch of 'count' randomized orders
                for (int j = 0; j < count; j++) {
                    orderService.createOrder(createRandomOrder(deliveryMethod,paymentMethod,status,user));
                }
            }
            else if(Objects.equals(type, "delete")) {
                deleteLastOrders(count);
            }
            else if (count == 100 && Objects.equals(type, "update")) {
                    orderService.updateOrders100();  // New method for updating first 100 records
            } else if (count == 100000 && Objects.equals(type, "update")) {
                    orderService.updateOrders100000();  // New method for updating first 100,000 records
            }

            Instant end = Instant.now();
            long totalTime = Duration.between(start, end).toNanos();  // Measure total response time in milliseconds

            if (i > 1) {  // Ignore the first iteration
                totalTimes.add(totalTime);  // Store only valid total response times (after the first iteration)
            }


        }
        Instant overallEnd = Instant.now();
        long overallTotalTime = Duration.between(overallStart, overallEnd).toNanos();  // Total execution time in milliseconds
        // Calculate performance metrics
        PerformanceMetrics metrics = calculateMetrics(totalTimes);

        // Write all valid total times and metrics to a file
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Total response times (in milliseconds) over 100 valid iterations:\n");
            for (Long time : totalTimes) {
                writer.write(time + " ns\n");  // Output times in milliseconds
            }
            writer.write("\nPerformance Metrics:\n");
            writer.write("Median: " + metrics.getMedian()/1000000 + " ms\n");
            writer.write("Average: " + metrics.getAverage()/1000000 + " ms\n");
            writer.write("Longest: " + metrics.getMax()/1000000 + " ms\n");
            writer.write("Shortest: " + metrics.getMin()/1000000 + " ms\n");
            writer.write("Standard Deviation: " + metrics.getStdDev()/1000000 + " ms\n");
            writer.write("\nTotal Execution Time: " + overallTotalTime/1000000 + " ms\n");
        }

        // Return the file as a downloadable resource
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // Method to delete the last 'count' orders
    private void deleteLastOrders(int count) {
        if(count == 100){
            orderService.deleteOrder100();
        }else if(count == 100000){
            orderService.deleteOrder100000();
        }
    }


    private Order createRandomOrder(DeliveryMethod deliveryMethod, PaymentMethod paymentMethod, Status status, User user) {
        Order order = new Order();

        // Randomly set fields
        order.setStreet(STREETS[random.nextInt(STREETS.length)]);
        order.setCity(CITIES[random.nextInt(CITIES.length)]);
        order.setPostalCode(POSTAL_CODES[random.nextInt(POSTAL_CODES.length)]);
        order.setPhoneNumber(PHONE_NUMBERS[random.nextInt(PHONE_NUMBERS.length)]);
        // Random price between 10 and 500
        order.setPrice(10 + (500 - 10) * random.nextDouble());
        order.setStatus(status);
        order.setPaymentMethod(paymentMethod);
        order.setUser(user);
        order.setDeliveryMethod(deliveryMethod);
        // Random timestamp within the last 30 days
        LocalDateTime createdAt = LocalDateTime.now().minusDays(random.nextInt(30));
        order.setCreated_at(createdAt);

        // Here you can set other required fields (like PaymentMethod, DeliveryMethod, etc.) if needed

        return order;
    }


    // Calculate performance metrics (median, average, longest, shortest, stddev)
    private PerformanceMetrics calculateMetrics(List<Long> times) {
        // Sort times to ensure accurate median calculation
        List<Long> sortedTimes = times.stream().sorted().collect(Collectors.toList());

        // Calculate the minimum and maximum
        long min = sortedTimes.get(0);
        long max = sortedTimes.get(sortedTimes.size() - 1);

        // Calculate the average (double for precision)
        double average = sortedTimes.stream().mapToLong(Long::longValue).average().orElse(0.0);

        // Calculate the median (precise calculation for both odd and even number of elements)
        double median;
        int size = sortedTimes.size();
        if (size % 2 == 0) {
            median = (sortedTimes.get(size / 2 - 1) + sortedTimes.get(size / 2)) / 2.0;
        } else {
            median = sortedTimes.get(size / 2);
        }

        // Calculate the standard deviation (double for precision)
        double stdDev = Math.sqrt(sortedTimes.stream()
                .mapToDouble(time -> Math.pow(time - average, 2))
                .average().orElse(0.0));

        // Return the performance metrics with precise values
        return new PerformanceMetrics(median, min, max, average, stdDev);
    }

    // Endpoint for join query (small dataset) based on product_id
    @GetMapping("/joinProductOrderDetailsSmall")
    public ResponseEntity<InputStreamResource> joinProductOrderDetailsSmall() throws IOException {
        return measureJoinPerformance(100, "join_small_product_order_results.txt", 10L);
    }

    // Endpoint for join query (large dataset) based on product_id
    @GetMapping("/joinProductOrderDetailsLarge")
    public ResponseEntity<InputStreamResource> joinProductOrderDetailsLarge() throws IOException {
        return measureJoinPerformance(100000, "join_large_product_order_results.txt", 10L);
    }

    // Measure join query performance
    private ResponseEntity<InputStreamResource> measureJoinPerformance(int count, String fileName, Long productId) throws IOException {
        List<Long> totalTimes = new ArrayList<>();

        Instant overallStart = Instant.now();
        // Perform 101 iterations, but ignore the first result
        for (int i = 0; i < 102; i++) {
            Instant start = Instant.now();
            System.out.println("#####################################################Progress: " + i + "/" + 100 +" #####################################################");

            if (count == 100) {
                orderService.findOrdersByProductId(productId, 100); // Join query for first 100 records
            } else if (count == 100000) {
                orderService.findOrdersByProductId(productId, 100000); // Join query for first 100,000 records
            }

            Instant end = Instant.now();
            long totalTime = Duration.between(start, end).toNanos();

            if (i > 1) {  // Ignore the first iteration
                totalTimes.add(totalTime);
            }
        }

        Instant overallEnd = Instant.now();
        long overallTotalTime = Duration.between(overallStart, overallEnd).toNanos();

        // Calculate performance metrics
        PerformanceMetrics metrics = calculateMetrics(totalTimes);

        // Write all valid total times and metrics to a file
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Total response times (in milliseconds) over 100 valid iterations:\n");
            for (Long time : totalTimes) {
                writer.write(time + " ns\n");  // Output times in milliseconds
            }
            writer.write("\nPerformance Metrics:\n");
            writer.write("Median: " + metrics.getMedian()/1000000 + " ms\n");
            writer.write("Average: " + metrics.getAverage()/1000000 + " ms\n");
            writer.write("Longest: " + metrics.getMax()/1000000 + " ms\n");
            writer.write("Shortest: " + metrics.getMin()/1000000 + " ms\n");
            writer.write("Standard Deviation: " + metrics.getStdDev()/1000000 + " ms\n");
            writer.write("\nTotal Execution Time: " + overallTotalTime/1000000 + " ms\n");
        }

        // Return the file as a downloadable resource
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
