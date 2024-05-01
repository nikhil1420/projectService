package dev.nikhil.projectservice.services;

import dev.nikhil.projectservice.models.Category;

import java.util.List;

public interface CategoryService {
   List<String> getAllCategories();

    String getProductsInCategory(Long categoryId);
}
