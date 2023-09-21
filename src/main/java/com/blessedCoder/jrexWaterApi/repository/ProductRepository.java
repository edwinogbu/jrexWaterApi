package com.blessedCoder.jrexWaterApi.repository;

import com.blessedCoder.jrexWaterApi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Custom queries (find by id, name, category)
    Product findProductById(Long id);
    Product findProductByName(String name);
    Product findProductByCategory(String category);

    List<Product> findProductsByCategory(String category);
}
