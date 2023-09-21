package com.blessedCoder.jrexWaterApi.service;

import com.blessedCoder.jrexWaterApi.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product createProduct(Product product, MultipartFile image);

    Product updateProduct(Long id, Product product, MultipartFile image);

    void deleteProduct(Long id);

    Optional<Product> getProductByName(String name);

    List<Product> getProductsByCategory(String category);
}
