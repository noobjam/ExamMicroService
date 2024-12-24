@Service
public class OrderService {
    private final OrderRepository commandeRepository;
    private final KafkaProducer kafkaProducer;
    private final ProductService produitService;

    public OrderService(CommandeRepository commandeRepository, KafkaProducer kafkaProducer, ProduitService produitService) {
        this.OrderRepository = commandeRepository;
        this.kafkaProducer = kafkaProducer;
        this.productService = produitService;
    }

    public Order createCommande(CommandeInput input) {
        Produit product = productService.getProductById(input.getProduitId());
        if (product.getStock() < input.getQuantity()) {
            throw new RuntimeException("Insufficient stock!");
        }

        Order commande = new Order();
        Order.setProductId(input.getProductId());
        Order.setQuantity(input.getQuantity());

        Order savedCommande = OrderRepository.save(Order);
        kafkaProducer.sendMessage("Commande created: " + savedCommande);

        return savedCommande;
    }

    public List<Order> getOrders() {
        return OrderRepository.findAll();
    }
}
