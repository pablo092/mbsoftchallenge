package com.example.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.productservice.model.Product;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SuppressWarnings("null")
class ProductControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testIsPrioritaryEndpoint() {
        Product product = new Product("PQR-12345-7");
        ResponseEntity<Boolean> response = restTemplate.postForEntity("/api/products/isPrioritary", product,
                Boolean.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }

    @Test
    void testVerifyEndpoint() {
        Product product = new Product("DCR-88578-9");
        ResponseEntity<Boolean> response = restTemplate.postForEntity("/api/products/verify", product, Boolean.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }

    @Test
    void testSortProductsByCodeEndpoint() {
        List<Product> products = Arrays.asList(
                new Product("ZXY-12345-5"),
                new Product("ABC-54321-2"),
                new Product("LMN-67890-8"));

        HttpEntity<List<Product>> request = new HttpEntity<>(products);
        ResponseEntity<Product[]> response = restTemplate.exchange("/api/products/sortByCode", HttpMethod.POST, request,
                Product[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3, response.getBody().length);
        assertEquals("ABC-54321-2", response.getBody()[0].getCode());
        assertEquals("LMN-67890-8", response.getBody()[1].getCode());
        assertEquals("ZXY-12345-5", response.getBody()[2].getCode());
    }

    @Test
    void testUnionProductsEndpoint() {
        List<Product> list1 = Arrays.asList(new Product("ABC-11111-1"), new Product("XYZ-22222-2"));
        List<Product> list2 = Arrays.asList(new Product("XYZ-22222-2"), new Product("LMN-33333-3"));

        HttpEntity<List<List<Product>>> request = new HttpEntity<>(Arrays.asList(list1, list2));
        ResponseEntity<Product[]> response = restTemplate.exchange("/api/products/union", HttpMethod.POST, request,
                Product[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3, response.getBody().length);
    }

    @Test
    void testIntersectProductsEndpoint() {
        List<Product> list1 = Arrays.asList(new Product("ABC-11111-1"), new Product("XYZ-22222-2"));
        List<Product> list2 = Arrays.asList(new Product("XYZ-22222-2"), new Product("LMN-33333-3"));

        HttpEntity<List<List<Product>>> request = new HttpEntity<>(Arrays.asList(list1, list2));
        ResponseEntity<Product[]> response = restTemplate.exchange("/api/products/intersect", HttpMethod.POST, request,
                Product[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().length);
        assertEquals("XYZ-22222-2", response.getBody()[0].getCode());
    }

    @Test
    void testSortProductsByCodeEmptyList() {
        List<Product> products = Arrays.asList();

        HttpEntity<List<Product>> request = new HttpEntity<>(products);
        ResponseEntity<Product[]> response = restTemplate.exchange("/api/products/sortByCode", HttpMethod.POST, request,
                Product[].class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testUnionProductsInvalidRequest() {
        HttpEntity<List<List<Product>>> request = new HttpEntity<>(Arrays.asList());
        ResponseEntity<Product[]> response = restTemplate.exchange("/api/products/union", HttpMethod.POST, request,
                Product[].class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testIntersectProductsNoCommonElements() {
        List<Product> list1 = Arrays.asList(new Product("AAA-11111-1"));
        List<Product> list2 = Arrays.asList(new Product("BBB-22222-2"));

        HttpEntity<List<List<Product>>> request = new HttpEntity<>(Arrays.asList(list1, list2));
        ResponseEntity<Product[]> response = restTemplate.exchange("/api/products/intersect", HttpMethod.POST, request,
                Product[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().length);
    }
}
