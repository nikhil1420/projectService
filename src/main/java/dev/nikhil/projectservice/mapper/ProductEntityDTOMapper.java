package dev.nikhil.projectservice.mapper;

import dev.nikhil.projectservice.dto.CreateProductRequestDTO;
import dev.nikhil.projectservice.dto.ProductResponseDTO;
import dev.nikhil.projectservice.models.Product;

public class ProductEntityDTOMapper {

    public static ProductResponseDTO convertProductEntityToProductResponseDTO(Product product){
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setTitle(product.getTitle());
        responseDTO.setRating(product.getRating());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setImageURL(product.getImageUrl());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setCategory(product.getCategory().getName());
        return responseDTO;
    }

    public static Product convertCreateProductRequestDTOToProduct(CreateProductRequestDTO productRequestDTO){
        Product product = new Product();
        product.setTitle(productRequestDTO.getTitle());
        product.setRating(0);
        product.setPrice(productRequestDTO.getPrice());
        product.setImageUrl(productRequestDTO.getImageURL());
        product.setDescription(productRequestDTO.getDescription());
        return product;
    }
}
