package com.heydancer.service.impl;

import com.heydancer.exception.ValidationException;
import com.heydancer.model.Product;
import com.heydancer.service.Products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsImpl implements Products {
    /**
     * HashMap для хранения объектов Product,
     * где ключом является Id продукта, а значением сам продукт
     */
    private final Map<String, Product> productMap = new HashMap<>();

    /**
     * Метод добавляет Product в productMap, если он еще не существует
     *
     * @param product добавляемый продукт
     * @return true, если продукт был успешно добавлен, и false, если он уже существует
     * @throws ValidationException если продукте null или имеет некорректный ID или name
     */
    @Override
    public boolean addProduct(Product product) {
        validateProduct(product);

        String productId = product.getId();

        if (productMap.containsKey(productId)) {
            return false;
        } else {
            productMap.put(productId, product);
            return true;
        }
    }

    /**
     * Метод удаляет Product из productMap, если он ранее был добавлен
     *
     * @param product удаляемый продукт
     * @return true, если продукт был успешно удален, и false, если такой продукт не был найден
     * @throws ValidationException если продукте null или имеет некорректный ID или name
     */
    @Override
    public boolean deleteProduct(Product product) {
        validateProduct(product);

        String productId = product.getId();
        return productMap.remove(productId, product);
    }

    /**
     * Метод позволяет получить имя продукта по его ID
     *
     * @param id продукта для поиска искомого продукта
     * @return productName, если ID был найден в productMap, и пустую строку, если такой продукт не был найден
     */
    @Override
    public String getName(String id) {
        String productName = "";

        if (productMap.containsKey(id)) {
            productName = productMap.get(id).getName();
        }
        return productName;
    }


    /**
     * Метод позволяет получить список идентификаторов по названию продукта
     * @param name название продукта для поиска идентификаторов
     * @return список product ids, если есть совпадения по названию, и пустой список, если совпадений нет
     */
    @Override
    public List<String> findByName(String name) {
        List<String> productIds = new ArrayList<>();

        for (Product value : productMap.values()) {
            if (value.getName().equals(name)) {
                productIds.add(value.getId());
            }
        }

        return productIds;
    }

    /**
     * Метод проверки Product
     *
     * @param product проверяемый объект
     * @throws ValidationException если продукте null или имеет некорректный ID или name
     */
    private void validateProduct(Product product) {
        if (product == null) {
            throw new ValidationException("Product cannot be null");
        }

        if (product.getId() == null || product.getId().isEmpty()) {
            throw new ValidationException(
                    String.format("Incorrect product ID: %s", product.getId()));
        }

        if (product.getName() == null || product.getName().isEmpty()) {
            throw new ValidationException(
                    String.format("Incorrect product NAME: %s", product.getName()));
        }
    }
}
