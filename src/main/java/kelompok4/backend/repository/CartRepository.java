package kelompok4.backend.repository;

import kelompok4.backend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    // Bisa ditambah custom query jika ingin filter berdasarkan user misalnya
}
