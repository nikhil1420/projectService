package dev.nikhil.projectservice.services;

import dev.nikhil.projectservice.dtos.ProductDto;
import dev.nikhil.projectservice.exceptions.NotFoundException;
import dev.nikhil.projectservice.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getSingleProduct(Long productId) throws NotFoundException;

    Product addNewProduct(ProductDto product);

    /*
    Product object has only those fields filled which need to be updated.
    Everything else is null
     */
    Product updateProduct(Long productId, Product product);

    Product replaceProduct(Long productId, Product product);

    Optional<Product> deleteProduct(Long productId) throws NotFoundException;
}
