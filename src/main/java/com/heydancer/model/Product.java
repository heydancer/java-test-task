package com.heydancer.model;

/**
 * Класс описывающий модель Product
 */
public class Product {
    private String id;
    private String name;

    /**
     * Публичный конструктор для инициализации объекта
     */
    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Метод для получения идентификатора продукта
     */
    public String getId() {
        return id;
    }

    /**
     * Метод для установки/изменения идентификатора продукта
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Метод для получения названия продукта
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для установки/изменения названия продукта
     */
    public void setName(String name) {
        this.name = name;
    }
}
