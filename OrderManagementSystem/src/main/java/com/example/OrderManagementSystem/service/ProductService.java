package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.dto.ProductRequestDTO;
import com.example.OrderManagementSystem.exceptions.ResourceNotFoundException;
import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.math.RoundingMode;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequestDTO dto) {
        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setBirdSize(dto.getBirdSize());
        product.setQuantity(dto.getQuantity());
        product.setCount(dto.getCount());

        if (dto.getCount() != null && dto.getCount() > 0 && dto.getQuantity() != null) {
            BigDecimal averageWeight = dto.getQuantity()
                    .divide(BigDecimal.valueOf(dto.getCount()), 2, RoundingMode.HALF_UP);
            product.setAverageWeight(averageWeight);
        } else {
            product.setAverageWeight(BigDecimal.ZERO);
        }

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
    }

    public Product updateProduct(Long id, ProductRequestDTO dto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        existingProduct.setProductName(dto.getProductName());
        existingProduct.setBirdSize(dto.getBirdSize());
        existingProduct.setQuantity(dto.getQuantity());
        existingProduct.setCount(dto.getCount());

        if (dto.getCount() != null && dto.getCount() > 0 && dto.getQuantity() != null) {
            BigDecimal averageWeight = dto.getQuantity()
                    .divide(BigDecimal.valueOf(dto.getCount()), 2, RoundingMode.HALF_UP);
            existingProduct.setAverageWeight(averageWeight);
        } else {
            existingProduct.setAverageWeight(BigDecimal.ZERO);
        }

        return productRepository.save(existingProduct);
    }

    public boolean deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return true;
        } else {
            throw new ResourceNotFoundException("Product with id " + id + " not found");
        }
    }
}
