package dev.nikhil.projectservice.services;

import dev.nikhil.projectservice.clients.fakestoreapi.FakeStoreClient;
import dev.nikhil.projectservice.dto.fakeStoreDTOs.FakeStoreProductResponseDTO;
import dev.nikhil.projectservice.exceptions.NoProductPresentException;
import dev.nikhil.projectservice.exceptions.ProductNotFoundException;
import dev.nikhil.projectservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FakeStoreProductServiceImpl {
   @Autowired
    private FakeStoreClient fakeStoreClient;


    public List<FakeStoreProductResponseDTO> getAllProducts() {
        List<FakeStoreProductResponseDTO> fakeStoreProducts = fakeStoreClient.getAllProducts();
        if(fakeStoreProducts == null){
            throw new NoProductPresentException("No products are found");
        }
        return fakeStoreProducts;
    }

    public FakeStoreProductResponseDTO getProduct(int productId) throws ProductNotFoundException {
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreClient.getProductById(productId);
        if(fakeStoreProductResponseDTO == null){
            throw new ProductNotFoundException("Product not found with id : " + productId);
        }
        return fakeStoreProductResponseDTO;
    }

    public Product createProduct(Product product) {
        return null;
    }

    public Product updateProduct(Product updatedProduct, int productId) {
        return null;
    }

    public boolean deleteProduct(int productId) {
        return false;
    }
}
