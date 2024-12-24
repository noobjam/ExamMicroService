@Entity
public class Odrer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int produitId;
    private int quantity;

    // Getters and Setters
}
