package com.heydancer.service.impl;

import com.heydancer.exception.ValidationException;
import com.heydancer.model.Product;
import com.heydancer.service.Products;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductsImplTest {
    private Products productsManager;
    private Product firstProduct;
    private Product secondProduct;

    @BeforeEach
    void beforeEach() {
        productsManager = new ProductsImpl();

        firstProduct = new Product("1", "first product name");
        secondProduct = new Product("2", "second product name");
    }

    @Test
    void shouldAddProductAndReturnTrue() {
        boolean isAdded = productsManager.addProduct(firstProduct);
        String productName = productsManager.getName("1");

        assertTrue(isAdded);
        assertEquals("first product name", productName);
    }

    @Test
    void shouldReturnFalseWhenAddingProduct() {
        productsManager.addProduct(firstProduct);
        boolean isAdded = productsManager.addProduct(firstProduct);

        assertFalse(isAdded);
    }

    @Test
    void shouldAddProductAndDelete() {
        productsManager.addProduct(firstProduct);
        boolean isDeleted = productsManager.deleteProduct(firstProduct);

        assertTrue(isDeleted);
    }

    @Test
    void shouldReturnFalseIfProductWasNotAdded() {
        boolean isDeleted = productsManager.deleteProduct(firstProduct);

        assertFalse(isDeleted);
    }

    @Test
    void shouldAddProductsAndReturnNameById() {
        boolean isFirstAdded = productsManager.addProduct(firstProduct);
        boolean isSecondAdded = productsManager.addProduct(secondProduct);
        String firstProductName = productsManager.getName("1");
        String secondProductName = productsManager.getName("2");

        assertTrue(isFirstAdded);
        assertTrue(isSecondAdded);
        assertEquals("first product name", firstProductName);
        assertEquals("second product name", secondProductName);
    }

    @Test
    void shouldReturnEmptyProductName() {
        String firstProductName = productsManager.getName("1");

        assertTrue(firstProductName.isEmpty());
    }

    @Test
    void shouldReturnAllProductIdsByName() {
        List<String> expectedProductIds = Arrays.asList("1", "2");

        firstProduct.setName(secondProduct.getName());
        productsManager.addProduct(firstProduct);
        productsManager.addProduct(secondProduct);

        List<String> actualProductIds = productsManager.findByName("second product name");

        assertArrayEquals(expectedProductIds.toArray(), actualProductIds.toArray());
        assertEquals(2, actualProductIds.size());
    }

    @Test
    void shouldReturnEmptyProductIds() {
        List<String> actualProductIds = productsManager.findByName("second product name");

        assertEquals(0, actualProductIds.size());
        assertTrue(actualProductIds.isEmpty());
    }

    @Test
    void shouldThrowInvalidIdException() {
        firstProduct.setId(null);

        ValidationException validationException = assertThrows(ValidationException.class,
                () -> productsManager.addProduct(firstProduct));


        assertEquals("Incorrect product ID: null", validationException.getMessage());
    }

    @Test
    void shouldThrowInvalidNameException() {
        firstProduct.setName(null);

        ValidationException validationException = assertThrows(ValidationException.class,
                () -> productsManager.addProduct(firstProduct));


        assertEquals("Incorrect product NAME: null", validationException.getMessage());
    }
}