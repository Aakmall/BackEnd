package kelompok4.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date orderDate;

    private Long productId;

    private Date dateEstimation;

    private Integer quantity;

    private Double totalPrice; // 💡 Simpan total price di database

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment; // Relasi ke Payment

}
