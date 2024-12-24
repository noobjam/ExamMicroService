@Component
public class OrderQuery implements GraphQLQueryResolver {

    private final OrderService commandeService;
    private final ProductService produitService;

    public OrderQuery(OrderService commandeService, ProductService produitService) {
        this.OrderService = commandeService;
        this.productService = produitService;
    }

    public List<Commande> getOrders() {
        return commandeService.getOrders();
    }

    public Product getProductById(int productId) {
        return produitService.getProductById(productId);
    }
}
