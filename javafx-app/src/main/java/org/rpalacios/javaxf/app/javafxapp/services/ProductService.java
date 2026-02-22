package org.rpalacios.javaxf.app.javafxapp.services;

import org.rpalacios.javaxf.app.javafxapp.models.Products;

import java.util.List;

public interface ProductService {

    List<Products> findAll();
    Products save(Products product);
    Products update(Products product);
    Products delete(Products product);
}
