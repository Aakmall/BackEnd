package kelompok4.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private String paymentMethod; // Contoh: Credit Card, Bank Transfer
    private Double amountPaid;
    private Date paymentDate;
    private String status; // Contoh: Pending, Completed, Failed
}
