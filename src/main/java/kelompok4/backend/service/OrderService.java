package kelompok4.backend.service;

import kelompok4.backend.entity.Order;
import kelompok4.backend.entity.Product;
import kelompok4.backend.repository.OrderRepository;
import kelompok4.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order createOrder(Order order) {
        if (order.getProductId() == null) {
            throw new RuntimeException("Product ID cannot be null");
        }

        Optional<Product> productOpt = productRepository.findById(order.getProductId());
        if (productOpt.isEmpty()) {
            throw new RuntimeException("Product not found with ID: " + order.getProductId());
        }

        Product product = productOpt.get();
        double totalPrice = product.getPrice() * order.getQuantity(); // 💡 Hitung total price

        order.setTotalPrice(totalPrice); // 💡 Simpan total price di database

        return orderRepository.save(order);
    }

    public Order getOrderDetail(Long id) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isEmpty()) {
            throw new RuntimeException("Order not found with ID: " + id);
        }
        return orderOpt.get();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
