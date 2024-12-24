package org.example.procuctservice.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/graphql")
public class ProductController {
    @PostMapping
    public Map<String, Object> handleGraphQLQuery(@RequestBody Map<String, Object> request) {
        // Parses and handles incoming GraphQL queries
        return request;
    }
}

