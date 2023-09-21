package com.blessedCoder.jrexWaterApi.service;

import com.blessedCoder.jrexWaterApi.model.Product;
import com.blessedCoder.jrexWaterApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            String imagePath = saveImage(image);
            product.setImagePath(imagePath);
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product, MultipartFile image) {
        Optional<Product> existingProductOptional = productRepository.findById(id);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            // Update the existing product with the new data
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());

            if (image != null && !image.isEmpty()) {
                String imagePath = saveImage(image);
                existingProduct.setImagePath(imagePath);
            }

            return productRepository.save(existingProduct);
        } else {
            return null; // Product not found
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private String saveImage(MultipartFile image) {
        try {
            String fileName = image.getOriginalFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            String uniqueFileName = System.currentTimeMillis() + fileExtension;
            String uploadDirectory = "C:/Users/Blessed Coder/Desktop/MyFiles/";

            Path imagePath = Path.of(uploadDirectory, uniqueFileName);
            Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            return imagePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image: " + e.getMessage());
        }
    }



    @Override
    public Optional<Product> getProductByName(String name) {
        return Optional.ofNullable(productRepository.findProductByName(name));
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findProductsByCategory(category);
    }

}
