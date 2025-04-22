package kelompok4.backend.controller;

import kelompok4.backend.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    // Menambahkan produk ke wishlist
    @PostMapping("/add/{userId}/{productId}")
    public ResponseEntity<Object> addToWishlist(@PathVariable Long userId, @PathVariable Long productId) {
        return wishlistService.addToWishlist(userId, productId);
    }

    // Mengambil produk yang ada di wishlist
    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getWishlist(@PathVariable Long userId) {
        return wishlistService.getWishlist(userId);
    }

    // Menghapus produk dari wishlist
    @DeleteMapping("/remove/{userId}/{productId}")
    public ResponseEntity<Object> removeFromWishlist(@PathVariable Long userId, @PathVariable Long productId) {
        return wishlistService.removeFromWishlist(userId, productId);
    }
}
