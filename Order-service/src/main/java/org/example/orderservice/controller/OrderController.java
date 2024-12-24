package com.example.commandeservice.controller;

import com.example.commandeservice.dto.CommandeInput;
import com.example.commandeservice.entity.Commande;
import com.example.commandeservice.entity.Produit;
import com.example.commandeservice.service.CommandeService;
import com.example.commandeservice.service.ProduitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeService commandeService;
    private final ProduitService produitService;

    public CommandeController(CommandeService commandeService, ProduitService produitService) {
        this.commandeService = commandeService;
        this.produitService = produitService;
    }

    // Get all commandes
    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() {
        List<Commande> commandes = commandeService.getCommandes();
        return ResponseEntity.ok(commandes);
    }

    // Get a specific commande by ID
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        Commande commande = commandeService.getCommandeById(id);
        return ResponseEntity.ok(commande);
    }

    // Create a new commande
    @PostMapping
    public ResponseEntity<?> createCommande(@RequestBody CommandeInput input) {
        // Step 1: Fetch product details via GraphQL
        Produit produit = produitService.getProduitById(input.getProduitId());

        // Step 2: Validate stock availability
        if (produit.getStock() < input.getQuantity()) {
            return ResponseEntity.badRequest().body("Insufficient stock for product ID: " + input.getProduitId());
        }

        // Step 3: Create the Commande
        Commande createdCommande = commandeService.createCommande(input);

        // Step 4: Update stock via GraphQL (not shown here, but ProduitService would handle it)
        produitService.updateProductStock(input.getProduitId(), produit.getStock() - input.getQuantity());

        return ResponseEntity.ok(createdCommande);
    }

    // Delete a specific commande
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }
}
