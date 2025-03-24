package kelompok4.backend.controller;

import kelompok4.backend.entity.Product;
import kelompok4.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> create(@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Object> detail(@PathVariable("id") Long id) {
        return productService.detail(id);
    }

    @GetMapping("/list")
    public List<Product> listProduct() {
        return productService.listProduct();
    }
}
