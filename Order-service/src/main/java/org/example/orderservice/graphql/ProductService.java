@Service
public class ProductService {
    private final WebClient webClient;

    public ProduitService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/graphql").build();
    }

    public Produit getProductById(int produitId) {
        String query = String.format("{ getProduitById(produitId: %d) { id, name, stock, price } }", produitId);
        Map<String, Object> response = webClient.post()
                .uri("")
                .bodyValue(Map.of("query", query))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map<String, Object> data = (Map<String, Object>) response.get("data");
        Map<String, Object> produitData = (Map<String, Object>) data.get("getProductById");

        Produit produit = new Produit();
        produit.setId((Integer) productData.get("id"));
        produit.setName((String) productData.get("name"));
        produit.setStock((Integer) productData.get("stock"));
        produit.setPrice((Double) productData.get("price"));

        return produit;
    }

    public void updateProductStock(int productId, int newStock) {
        String mutation = String.format(
                "mutation { updateProductStock(produitId: %d, stock: %d) { id, stock } }",
                produitId, newStock
        );
        webClient.post()
                .uri("")
                .bodyValue(Map.of("query", mutation))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
