package dev.nikhil.projectservice.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Data
public class CategoryResponseDTO {
    private UUID categoryId;
    private String categoryName;
    private List<ProductResponseDTO> products;
}
