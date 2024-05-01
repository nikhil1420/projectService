package dev.nikhil.projectservice.services;

import dev.nikhil.projectservice.dtos.ProductDto;
import dev.nikhil.projectservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService {

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public String updateProduct(Long productId, ProductDto productDto ) {
        return null;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }
}
