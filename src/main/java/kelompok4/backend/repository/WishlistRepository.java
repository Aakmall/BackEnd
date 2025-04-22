package kelompok4.backend.repository;

import kelompok4.backend.entity.User;
import kelompok4.backend.entity.Product;
import kelompok4.backend.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    boolean existsByUserAndProduct(User user, Product product);  // Cek apakah produk ada di wishlist

    Wishlist findByUserAndProduct(User user, Product product);  // Cari wishlist berdasarkan user dan produk

    List<Wishlist> findByUser(User user);  // Cari semua wishlist berdasarkan user
}
