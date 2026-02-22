package org.rplacios.java.swing.jdbc.repositories;

import org.rplacios.java.swing.jdbc.models.Product;

import java.util.List;

public interface ProductRepositorio {

    List<Product> findAll();

    Product findById(Long id);
    Product save(Product product);
    void delete(Long id);
}
