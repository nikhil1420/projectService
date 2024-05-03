package dev.nikhil.projectservice.services;

import dev.nikhil.projectservice.dto.CreateProductRequestDTO;
import dev.nikhil.projectservice.dto.ProductResponseDTO;
import dev.nikhil.projectservice.exceptions.CategoryNotFoundException;
import dev.nikhil.projectservice.exceptions.ProductNotFoundException;
import dev.nikhil.projectservice.mapper.ProductEntityDTOMapper;
import dev.nikhil.projectservice.models.Category;
import dev.nikhil.projectservice.models.Product;
import dev.nikhil.projectservice.repositories.CategoryRepository;
import dev.nikhil.projectservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> savedProducts = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOs = new ArrayList<>();
        for (Product product : savedProducts) {
            productResponseDTOs.add(ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product));
        }
        return productResponseDTOs;
    }

    @Override
    public ProductResponseDTO getProduct(UUID productId) throws ProductNotFoundException {
        /*
        // basic code to implement null check
        Product savedProduct = productRepository.findById(productId).get();
        if(savedProduct == null){
            throw new ProductNotFoundException("Product not found for id : " + productId);
        }
        return savedProduct;
         */
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id : " + productId)
        );
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product);
    }

    @Override
    public ProductResponseDTO createProduct(CreateProductRequestDTO productRequestDTO) {
        Product product = ProductEntityDTOMapper.convertCreateProductRequestDTOToProduct(productRequestDTO);
        Category savedCategory = categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow(
                () -> new CategoryNotFoundException("Category not found for id : " + productRequestDTO.getCategoryId()));
        product.setCategory(savedCategory);
        product = productRepository.save(product);
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product);
    }

    // cant update category and rating for a product
    @Override
    public ProductResponseDTO updateProduct(CreateProductRequestDTO createProductRequestDTO, UUID productId) {
        Product savedProduct = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id : " + productId));
        savedProduct.setTitle(createProductRequestDTO.getTitle());
        savedProduct.setImageUrl(createProductRequestDTO.getImageURL());
        savedProduct.setPrice(createProductRequestDTO.getPrice());
        savedProduct.setDescription(createProductRequestDTO.getDescription());
        savedProduct = productRepository.save(savedProduct); // save works as upsert, which is insert and update
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(savedProduct);
    }

    @Override
    public boolean deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
        return true;
    }

    @Override
    public ProductResponseDTO getProduct(String productName) {
//        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(
//                productRepository.findProductByTitle(productName)
//        );
        return null;
    }


    //TODO: convert product list to product response dto list
    @Override
    public List<Product> getProducts(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}

