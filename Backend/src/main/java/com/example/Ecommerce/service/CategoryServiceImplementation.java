package com.example.Ecommerce.service;

import com.example.Ecommerce.Dto.CategoryDto;
import com.example.Ecommerce.model.Category;
import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImplementation implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((element) -> modelMapper.map(element, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public Category saveCategory(CategoryDto category) {
        return new Category(null, category.getName(), new ArrayList<Product>());
    }
}
