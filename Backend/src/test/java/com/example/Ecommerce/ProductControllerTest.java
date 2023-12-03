package com.example.Ecommerce;

import com.example.Ecommerce.Dto.ProductDto;
import com.example.Ecommerce.controller.ProductController;
import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;



import java.util.Arrays;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ComponentScan(basePackages = {"com.example.Ecommerce"})
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void getProductsPage() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        List<Product> products = Arrays.asList(new Product(), new Product());
        Page<Product> productPage = new PageImpl<>(products);
        when(productService.getProductsPage(pageable)).thenReturn(productPage);
        mockMvc.perform(get("/api/productsPage"))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(10)));
    }

    @Test
    void getProductDto() throws Exception {
        Long productId = 1L;
        ProductDto productDto = new ProductDto();
        when(productService.getProductDto(productId)).thenReturn(productDto);

        mockMvc.perform(get("/api/products/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Rower 1"));

    }


}
