package kelompok4.backend.service;

import kelompok4.backend.entity.Product;
import kelompok4.backend.entity.User;
import kelompok4.backend.entity.Wishlist;
import kelompok4.backend.repository.WishlistRepository;
import kelompok4.backend.repository.UserRepository;
import kelompok4.backend.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public WishlistService(WishlistRepository wishlistRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // Menambahkan produk ke wishlist
    public ResponseEntity<Object> addToWishlist(Long userId, Long productId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (userOptional.isPresent() && productOptional.isPresent()) {
            User user = userOptional.get();
            Product product = productOptional.get();

            // Cek apakah produk sudah ada di wishlist
            if (wishlistRepository.existsByUserAndProduct(user, product)) {
                return ResponseEntity.status(400).body("Product already in wishlist");
            }

            Wishlist wishlist = new Wishlist();
            wishlist.setUser(user);
            wishlist.setProduct(product);

            wishlistRepository.save(wishlist);
            return ResponseEntity.ok("Product added to wishlist");
        }

        return ResponseEntity.status(404).body("User or Product not found");
    }

    // Mengambil produk yang ada di wishlist pengguna
    public ResponseEntity<Object> getWishlist(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user.getWishlists());
        }

        return ResponseEntity.status(404).body("User not found");
    }

    // Menghapus produk dari wishlist
    public ResponseEntity<Object> removeFromWishlist(Long userId, Long productId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (userOptional.isPresent() && productOptional.isPresent()) {
            User user = userOptional.get();
            Product product = productOptional.get();

            Wishlist wishlist = wishlistRepository.findByUserAndProduct(user, product);

            if (wishlist != null) {
                wishlistRepository.delete(wishlist);
                return ResponseEntity.ok("Product removed from wishlist");
            }
            return ResponseEntity.status(404).body("Product not found in wishlist");
        }

        return ResponseEntity.status(404).body("User or Product not found");
    }
}
