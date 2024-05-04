package dev.nikhil.projectservice.clients.fakestoreapi;

import dev.nikhil.projectservice.dto.fakeStoreDTOs.FakeStoreProductRatingDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
    private FakeStoreProductRatingDTO rating;
}
