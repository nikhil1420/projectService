package dev.nikhil.projectservice.dtos;

import dev.nikhil.projectservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDto {
    private Product product;
}
