package com.example.Ecommerce.service;

import com.example.Ecommerce.Dto.ProductDto;
import com.example.Ecommerce.Exception.ProductError;
import com.example.Ecommerce.Exception.ProductException;
import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImplementation implements ProductService{
    private final ProductRepository productRepository;


    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductDto getProductDto(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductError.PRODUCT_NOT_FOUND));
        return mapToProductDto(product);

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

    @Override
    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getProductionYear(),
                product.getFork(),
                product.getForkMaterial(),
                product.getFrameMaterial(),
                product.getDrive(),
                product.getFrontDerailleur(),
                product.getRearDerailleur(),
                product.getHandle(),
                product.getCrank(),
                product.getCassette(),
                product.getBrakeType(),
                product.getBrake(),
                product.getWheel(),
                product.getWheelSize(),
                product.getTire(),
                product.getPedals(),
                product.getSaddle(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getBrand().getName(),
                product.getSize().getName(),
                product.getCategory().getName()
        );
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
