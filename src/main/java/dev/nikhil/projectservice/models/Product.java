package dev.nikhil.projectservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseModel {
    private String title;
    private double price;
    private String description;
    // P : C
    // 1 -> 1
    // m <- 1
    // M <-> 1
    @ManyToOne
    private Category category;
    private String imageUrl;
    private double rating;
}
