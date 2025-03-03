package com.example.productservice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;

@SpringBootTest
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsPrioritary() {
        assertTrue(productService.isPrioritary(new Product("PQR-12345-7")));
        assertTrue(productService.isPrioritary(new Product("WXY-67890-4")));
        assertFalse(productService.isPrioritary(new Product("ABC-12345-7")));
        assertFalse(productService.isPrioritary(new Product("XYZ-98765-3")));

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> productService.isPrioritary(new Product(null)));
        assertEquals("Codigo no puede ser vacío.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> productService.isPrioritary(new Product("")));
        assertEquals("Codigo no puede ser vacío.", exception.getMessage());
    }

    @Test
    void testVerify() {
        assertTrue(productService.verify(new Product("DCR-88578-9")));
        assertFalse(productService.verify(new Product("DCR-88578-8")));

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> productService.verify(new Product(null)));
        assertEquals("Codigo no puede ser vacío.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> productService.verify(new Product("")));
        assertEquals("Codigo no puede ser vacío.", exception.getMessage());
    }

    @Test
    void testSortProducts() {
        List<Product> products = Arrays.asList(
                new Product("ZXY-12345-5"),
                new Product("ABC-54321-2"),
                new Product("LMN-67890-8"));
        List<Product> sortedProducts = productService.sortProductsByCode(products);

        assertEquals("ABC-54321-2", sortedProducts.get(0).getCode());
        assertEquals("LMN-67890-8", sortedProducts.get(1).getCode());
        assertEquals("ZXY-12345-5", sortedProducts.get(2).getCode());
    }

    @Test
    void testUnionProducts() {
        List<Product> list1 = Arrays.asList(
                new Product("ABC-11111-1"),
                new Product("XYZ-22222-2"));
        List<Product> list2 = Arrays.asList(
                new Product("XYZ-22222-2"),
                new Product("LMN-33333-3"));

        List<Product> union = productService.unionProducts(list1, list2);

        assertEquals(3, union.size());
        assertTrue(union.stream().anyMatch(p -> p.getCode().equals("ABC-11111-1")));
        assertTrue(union.stream().anyMatch(p -> p.getCode().equals("XYZ-22222-2")));
        assertTrue(union.stream().anyMatch(p -> p.getCode().equals("LMN-33333-3")));
    }

    @Test
    void testIntersectProducts() {
        List<Product> list1 = Arrays.asList(
                new Product("ABC-11111-1"),
                new Product("XYZ-22222-2"),
                new Product("DEF-44444-4"));
        List<Product> list2 = Arrays.asList(
                new Product("XYZ-22222-2"),
                new Product("LMN-33333-3"),
                new Product("DEF-44444-4"));

        List<Product> intersection = productService.intersectProducts(list1, list2);

        assertEquals(2, intersection.size());
        assertTrue(intersection.stream().anyMatch(p -> p.getCode().equals("XYZ-22222-2")));
        assertTrue(intersection.stream().anyMatch(p -> p.getCode().equals("DEF-44444-4")));
    }

    @Test
    void testSortProductsEmptyList() {
        List<Product> products = Arrays.asList();
        List<Product> sortedProducts = productService.sortProductsByCode(products);

        assertEquals(0, sortedProducts.size());
    }

    @Test
    void testUnionProductsEmptyLists() {
        List<Product> list1 = Arrays.asList();
        List<Product> list2 = Arrays.asList();

        List<Product> union = productService.unionProducts(list1, list2);

        assertEquals(0, union.size());
    }

    @Test
    void testIntersectProductsNoCommonElements() {
        List<Product> list1 = Arrays.asList(new Product("AAA-11111-1"));
        List<Product> list2 = Arrays.asList(new Product("BBB-22222-2"));

        List<Product> intersection = productService.intersectProducts(list1, list2);

        assertEquals(0, intersection.size());
    }
}
