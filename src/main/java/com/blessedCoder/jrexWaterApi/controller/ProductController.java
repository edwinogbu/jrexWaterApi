package com.blessedCoder.jrexWaterApi.controller;

import com.blessedCoder.jrexWaterApi.exception.ProductNotFoundException;
import com.blessedCoder.jrexWaterApi.model.Product;
import com.blessedCoder.jrexWaterApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/viewAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/viewProduct/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(
            @Validated @ModelAttribute Product product,
            @RequestParam("image") MultipartFile image
    ) {
        Product createdProduct = productService.createProduct(product, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @Validated @ModelAttribute Product product,
            @RequestParam("image") MultipartFile image
    ) {
        Product updatedProduct = productService.updateProduct(id, product, image);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getProductName/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Optional<Product> product = productService.getProductByName(name);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            throw new ProductNotFoundException("Product with name " + name + " not found");
        }
    }

    @GetMapping("/getProductCategory/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }
}
