package kelompok4.backend.controller;

import kelompok4.backend.entity.Product;
import kelompok4.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
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

    // ✅ Tambahkan method update
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.update(id, product);

    }


    // ✅ Tambahkan method delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        return productService.delete(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

}
