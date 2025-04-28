package kelompok4.backend.service;

import kelompok4.backend.entity.Cart;
import kelompok4.backend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart updateCart(Long id, Cart cart) {
        Cart existingCart = cartRepository.findById(id).orElse(null);
        if (existingCart != null) {
            existingCart.setUser(cart.getUser());
            existingCart.setProduct(cart.getProduct());
            existingCart.setQuantity(cart.getQuantity());
            return cartRepository.save(existingCart);
        }
        return null;
    }


    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
