package com.shriram.ecom.services.admin.category;

import com.shriram.ecom.dto.CategoryDto;
import com.shriram.ecom.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryDto categoryDto);
    List<Category> getAllCategories();
}
