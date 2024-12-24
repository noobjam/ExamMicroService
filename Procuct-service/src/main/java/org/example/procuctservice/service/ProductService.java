package org.example.procuctservice.service;

import org.example.procuctservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProduitService {
    private final ProductRepository produitRepository;

    public ProduitService(ProductRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public Map<String, Object> resolveQuery(Map<String, Object> request) {
        // Logic to resolve GraphQL queries
        return request;
    }
}

