package dev.nikhil.projectservice.services;

import dev.nikhil.projectservice.dtos.ProductDto;
import dev.nikhil.projectservice.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(Long productId);

    Product addNewProduct(ProductDto productDto);

    String updateProduct(Long productId, ProductDto productDto);

    String deleteProduct(Long productId);
}
