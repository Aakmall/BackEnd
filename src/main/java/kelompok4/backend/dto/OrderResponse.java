package kelompok4.backend.dto;

import kelompok4.backend.entity.Order;
import kelompok4.backend.entity.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Data
@Getter
@Setter
public class OrderResponse {
    private Long id;

    private Date orderDate;

    private Double totalPrice;

    private Long productId;

    private Date dateEstimation;

    private Integer quantity;

    private Product product;
}
