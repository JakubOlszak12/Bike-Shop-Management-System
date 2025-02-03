package com.example.Ecommerce.service;

import com.example.Ecommerce.Dto.ProductDto;
import com.example.Ecommerce.Exception.ProductError;
import com.example.Ecommerce.Exception.ProductException;
import com.example.Ecommerce.model.Brand;
import com.example.Ecommerce.model.Category;
import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.model.Size;
import com.example.Ecommerce.repository.BrandRepository;
import com.example.Ecommerce.repository.CategoryRepository;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.repository.SizeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.*;

@Service
@AllArgsConstructor
public class ProductServiceImplementation implements ProductService{
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final SizeRepository sizeRepository;
    private final BrandRepository brandRepository;

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
    public Product saveProduct(ProductDto productDto) {
        Product product = new Product();

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setProductionYear(productDto.getProductionYear());
        product.setFork(productDto.getFork());
        product.setForkMaterial(productDto.getForkMaterial());
        product.setFrameMaterial(productDto.getFrameMaterial());
        product.setDrive(productDto.getDrive());
        product.setFrontDerailleur(productDto.getFrontDerailleur());
        product.setRearDerailleur(productDto.getRearDerailleur());
        product.setHandle(productDto.getHandle());
        product.setCrank(productDto.getCrank());
        product.setCassette(productDto.getCassette());
        product.setBrakeType(productDto.getBrakeType());
        product.setBrake(productDto.getBrake());
        product.setWheel(productDto.getWheel());
        product.setWheelSize(productDto.getWheelSize());
        product.setTire(productDto.getTire());
        product.setPedals(productDto.getPedals());
        product.setSaddle(productDto.getSaddle());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(now());
        product.setUpdatedAt(now());

        Category category = categoryRepository.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Size size = sizeRepository.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Size not found"));
        Brand brand = brandRepository.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        product.setCategory(category);
        product.setSize(size);
        product.setBrand(brand);

        return productRepository.save(product);
    }

    @Override
    public Page<ProductDto> findProductsByCategoryName(Pageable pageable, String category) {
        Page<Product> productPage = productRepository.findProductsByCategoryName(category, pageable);
        return productPage.map((element) -> modelMapper.map(element, ProductDto.class));
    }
}
