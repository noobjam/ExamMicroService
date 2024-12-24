@Component
public class OrderMutation implements GraphQLMutationResolver {

    private final OrderService commandeService;

    public OrderMutation(OrderService commandeService) {
        this.commandeService = commandeService;
    }

    public Order createCommande(OrderInput input) {
        return OrderService.createOrder(input);
    }
}
