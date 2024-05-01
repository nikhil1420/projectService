package dev.nikhil.projectservice.services;

public interface CategoryService {
    String getAllCategories();

    String getProductsInCategory(Long categoryId);
}
