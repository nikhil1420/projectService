package dev.nikhil.projectservice.services;


import dev.nikhil.projectservice.clients.fakestoreapi.FakeStoreClient;
import dev.nikhil.projectservice.dto.CategoryResponseDTO;
import dev.nikhil.projectservice.dto.CreateCategoryRequestDTO;
import dev.nikhil.projectservice.dto.fakeStoreDTOs.FakeStoreProductResponseDTO;
import dev.nikhil.projectservice.exceptions.CategoryNotFoundException;
import dev.nikhil.projectservice.mapper.CategoryEntityDTOMapper;
import dev.nikhil.projectservice.models.Category;
import dev.nikhil.projectservice.models.Product;
import dev.nikhil.projectservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private FakeStoreClient fakeStoreClient;

    @Override
    public CategoryResponseDTO getCategory(UUID categoryId) {
        Category category =  categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        return CategoryEntityDTOMapper.convertCategoryToCategoryResponseDTO(category);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOs = new ArrayList<>();
        for(Category c : categories){
            categoryResponseDTOs.add(CategoryEntityDTOMapper.convertCategoryToCategoryResponseDTO(c));
        }
        return categoryResponseDTOs;
    }

    @Override
    public CategoryResponseDTO createCategory(CreateCategoryRequestDTO categoryRequestDTO) {
        Category category = CategoryEntityDTOMapper.convertCreateCategoryDTOToCategory(categoryRequestDTO);
        category = categoryRepository.save(category);
        return CategoryEntityDTOMapper.convertCategoryToCategoryResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(CreateCategoryRequestDTO categoryRequestDTO, UUID categoryId) {
        return null;
    }

    @Override
    public boolean deleteCategory(UUID categoryId) {
        return false;
    }

    @Override
    public double getTotalPriceForCategory(UUID categoryId) {
        //List<FakeStoreProductResponseDTO> productResponseDTOS = fakeStoreClient.getAllProducts();
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category for the given id is not found")
        );
        if(category.getProducts().isEmpty()){
            return 0;
        } else{
            double sum = 0;
            for(Product p : category.getProducts()){
                sum = sum + p.getPrice();
            }
            return sum;
        }
    }
}
