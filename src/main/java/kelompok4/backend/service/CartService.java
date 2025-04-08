package kelompok4.backend.service;

import kelompok4.backend.entity.Cart;
import kelompok4.backend.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;

    public Cart addToCart(Cart cart){
        return cartRepository.save(cart);
    }

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    public Cart getCartById(Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.orElse(null);
    }

    public void deleteCart(Long id){
        cartRepository.deleteById(id);
    }
}
