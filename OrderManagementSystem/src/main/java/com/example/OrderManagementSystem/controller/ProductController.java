package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.dto.ProductRequestDTO;
import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.service.ProductService;
import com.example.OrderManagementSystem.dto.BaseResponseDTO;
import com.example.OrderManagementSystem.utils.ErrorResponse;
import com.example.OrderManagementSystem.utils.ResponseMessages;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product-controller")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponseDTO<Product>> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        try {
            Product savedProduct = productService.createProduct(productRequestDTO);
            BaseResponseDTO<Product> response = new BaseResponseDTO<>();
            response.setSuccess(true);
            response.setMessage(ResponseMessages.CREATED_RESPONSE.getMessage());
            response.setData(savedProduct);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse(500, "Failed to create product: " + e.getMessage());
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET ALL
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<BaseResponseDTO<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        BaseResponseDTO<List<Product>> response = new BaseResponseDTO<>();
        response.setSuccess(true);
        response.setMessage(ResponseMessages.SUCCESS);
        response.setData(products);
        return ResponseEntity.ok(response);
    }

    // GET BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            ErrorResponse error = ResponseMessages.ID_NOT_FOUND;
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        BaseResponseDTO<Product> response = new BaseResponseDTO<>();
        response.setSuccess(true);
        response.setMessage(ResponseMessages.SUCCESS);
        response.setData(product);
        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO productRequestDTO) {
        try {
            Product updatedProduct = productService.updateProduct(id, productRequestDTO);
            if (updatedProduct == null) {
                ErrorResponse error = ResponseMessages.ID_NOT_FOUND;
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
            BaseResponseDTO<Product> response = new BaseResponseDTO<>();
            response.setSuccess(true);
            response.setMessage(ResponseMessages.UPDATED_RESPONSE.getMessage());
            response.setData(updatedProduct);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse(500, "Failed to update product: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        if (!deleted) {
            ErrorResponse error = ResponseMessages.ID_NOT_FOUND;
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        BaseResponseDTO<Void> response = new BaseResponseDTO<>();
        response.setSuccess(true);
        response.setMessage(ResponseMessages.DELETED_RESPONSE.getMessage());
        response.setData(null);
        return ResponseEntity.ok(response);
    }
}
