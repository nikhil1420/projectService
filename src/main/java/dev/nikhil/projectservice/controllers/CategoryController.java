package dev.nikhil.projectservice.controllers;

import dev.nikhil.projectservice.dto.CategoryResponseDTO;
import dev.nikhil.projectservice.dto.CreateCategoryRequestDTO;
import dev.nikhil.projectservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable("id") UUID categoryId){
        return null;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CreateCategoryRequestDTO createCategoryRequestDTO){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable("id") UUID categoryId, @RequestBody CreateCategoryRequestDTO createCategoryRequestDTO){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") UUID categoryId){
        return null;
    }
}
