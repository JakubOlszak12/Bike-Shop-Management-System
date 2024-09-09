package com.example.Ecommerce.service;

import com.example.Ecommerce.Dto.ProductDto;
import com.example.Ecommerce.Exception.ProductError;
import com.example.Ecommerce.Exception.ProductException;
import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImplementation implements ProductService{
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductDto getProductDto(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductError.PRODUCT_NOT_FOUND));
        return modelMapper.map(product, ProductDto.class);

    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).
                orElseThrow(() -> new ProductException(ProductError.PRODUCT_NOT_FOUND));
    }

    @Override
    public Page<Product> getProductsPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<ProductDto> getProductsDtoPage(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map((element) -> modelMapper.map(element, ProductDto.class));
    }

    @Override
    public void deleteProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductError.PRODUCT_NOT_FOUND));
        productRepository.delete(product);
    }

    // TODO: 02.12.2023
    @Override
    public Product saveProduct(ProductDto productDto){
        Product product = new Product();
        return productRepository.save(product);
    }
}
