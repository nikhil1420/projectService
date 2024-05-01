package dev.nikhil.projectservice.controllers;

import dev.nikhil.projectservice.models.Category;
import dev.nikhil.projectservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()

    public List<String> getAllCategories() {

        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public String getProductsInCategory(@PathVariable("categoryId") Long categoryId) {
        return "Get products in category";
    }
}
