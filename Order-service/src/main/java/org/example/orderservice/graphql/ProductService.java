package com.example.commandes;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ProductService {
    private final WebClient webClient;

    public ProductService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/graphql").build();
    }

    public Product getProduitById(int produitId) {
        String query = String.format("{ getProduitById(produitId: %d) { id, name, stock, price } }", produitId);
        return webClient.post()
                .bodyValue(query)
                .retrieve()
                .bodyToMono(Produit.class)
                .block();
    }
}
