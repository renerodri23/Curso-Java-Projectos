package org.rpalacios.javaxf.app.javafxapp.services;

import org.rpalacios.javaxf.app.javafxapp.models.Products;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

public class ProductServiceWPC implements ProductService{
    private final WebClient client;
    private String baseUrl = "http://localhost:8080";

    public ProductServiceWPC() {
        client = WebClient.builder().baseUrl(baseUrl).build();
    }

    @Override
    public List<Products> findAll() {
        return client.get().retrieve()
                .bodyToFlux(Products.class)
                .collectList()
                .block();
    }

    @Override
    public Products save(Products product) {
        return client.post()
                .body(Mono.just(product),Products.class)
                .retrieve()
                .bodyToMono(Products.class)
                .block();
    }

    @Override
    public Products update(Products product) {
        return client.put().uri("/{id}", product.getId())
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Products.class)
                .block();
    }

    @Override
    public Products delete(Products product) {
        return client.delete().uri("/{id}", product.getId())
                .retrieve()
                .bodyToMono(Products.class)
                .block();
    }
}
