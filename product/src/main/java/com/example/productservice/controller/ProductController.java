package com.example.productservice.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productservice.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.productservice.model.Product;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product API", description = "API para gestionar productos")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/isPrioritary")
    @Operation(summary = "Verifica si el producto es prioritario")
    public boolean isPrioritary(@RequestBody Product product) {
        return productService.isPrioritary(product);
    }

    @PostMapping("/verify")
    @Operation(summary = "Verifica el código de producto")
    public boolean verify(@RequestBody Product product) {
        return productService.verify(product);
    }

    @PostMapping("/sortByCode")
    @Operation(summary = "Ordena una lista de codigos de productos alfabéticamente de menor a mayor")
    public ResponseEntity<List<Product>> sortProductsByCode(@RequestBody List<Product> products) {
        if (products == null || products.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        return ResponseEntity.ok(productService.sortProductsByCode(products));
    }

    @PostMapping("/union")
    @Operation(summary = "Devuelve la unión de dos listas de productos")
    public ResponseEntity<List<Product>> unionProducts(@RequestBody List<List<Product>> productLists) {
        if (productLists == null || productLists.size() != 2) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.unionProducts(productLists.get(0), productLists.get(1)));
    }

    @PostMapping("/intersect")
    @Operation(summary = "Devuelve la intersección de dos listas de productos")
    public ResponseEntity<List<Product>> intersectProducts(@RequestBody List<List<Product>> productLists) {
        if (productLists == null || productLists.size() != 2) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.intersectProducts(productLists.get(0), productLists.get(1)));
    }
}
