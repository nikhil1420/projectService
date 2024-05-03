package dev.nikhil.projectservice.services;

import dev.nikhil.projectservice.dto.CreateProductRequestDTO;
import dev.nikhil.projectservice.dto.ProductResponseDTO;
import dev.nikhil.projectservice.exceptions.ProductNotFoundException;
import dev.nikhil.projectservice.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProduct(UUID productId) throws ProductNotFoundException;
    ProductResponseDTO createProduct(CreateProductRequestDTO product);
    ProductResponseDTO updateProduct(CreateProductRequestDTO updatedProduct, UUID productId);
    boolean deleteProduct(UUID productId);
    ProductResponseDTO getProduct(String productName);
    List<Product> getProducts(double minPrice, double maxPrice);
}
