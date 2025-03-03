package com.example.productservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.productservice.model.Product;

@Service
public class ProductService {
    public boolean isPrioritary(Product product) {
        String code = product.getCode();
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Codigo no puede ser vacío.");
        }
        return code.startsWith("P") || code.startsWith("W");
    }

    public boolean verify(Product product) {
        String code = product.getCode();
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Codigo no puede ser vacío.");
        }
        String[] parts = code.split("-");
        if (parts.length != 3)

            return false;
        String numericPart = parts[1];
        int sum = 0;
        for (char digit : numericPart.toCharArray()) {
            sum += Character.getNumericValue(digit);
        }
        while (sum >= 10) {
            sum = sum / 10 + sum % 10;
        }
        return sum == Character.getNumericValue(parts[2].charAt(0));
    }

    public List<Product> sortProductsByCode(List<Product> products) {
        List<Product> sortedProducts = new ArrayList<>(products);
        Collections.sort(sortedProducts);

        return sortedProducts;
    }

    public List<Product> unionProducts(List<Product> list, List<Product> otherList) {
        Set<Product> resultSet = new HashSet<>(list);
        resultSet.addAll(otherList);
        return resultSet.stream().collect(Collectors.toList());
    }

    public List<Product> intersectProducts(List<Product> list, List<Product> otherList) {
        Set<Product> set = new HashSet<>(list);
        return otherList.stream().filter(set::contains).collect(Collectors.toList());
    }
}