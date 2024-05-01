package dev.nikhil.projectservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String name;
    private String description;
    // C : P
    // 1 : m
    // 1 : 1
    // 1 : m
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
