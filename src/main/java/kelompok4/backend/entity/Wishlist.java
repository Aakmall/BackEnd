package kelompok4.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "wishlists")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Pengguna yang menyukai produk

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;  // Produk yang disukai

    // Anda bisa menambahkan atribut lain jika diperlukan
}
