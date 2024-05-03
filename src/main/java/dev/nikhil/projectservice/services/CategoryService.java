package dev.nikhil.projectservice.services;


import dev.nikhil.projectservice.dto.CategoryResponseDTO;
import dev.nikhil.projectservice.dto.CreateCategoryRequestDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryResponseDTO getCategory(UUID categoryId);
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO createCategory(CreateCategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO updateCategory(CreateCategoryRequestDTO categoryRequestDTO, UUID categoryId);
    boolean deleteCategory(UUID categoryId);
}
