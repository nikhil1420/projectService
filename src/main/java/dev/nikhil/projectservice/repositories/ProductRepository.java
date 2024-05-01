package dev.nikhil.projectservice.repositories;

import dev.nikhil.projectservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
