package kelompok4.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private double price;

    private Integer stock;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String imageBase64;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


}

