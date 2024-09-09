package com.example.Ecommerce.service;

import com.example.Ecommerce.Dto.CategoryDto;
import com.example.Ecommerce.model.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    Category saveCategory(CategoryDto category);

}
