package com.heydancer.service;

import com.heydancer.model.Product;

import java.util.List;

/**
 * Интерфейс Products описывает поведение реализованного класса сервиса ProductsImpl
 */
public interface Products {
    boolean addProduct(Product product);

    boolean deleteProduct(Product product);

    String getName(String id);

    List<String> findByName(String name);
}
