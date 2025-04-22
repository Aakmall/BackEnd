package kelompok4.backend.service;

import kelompok4.backend.entity.Product;
import kelompok4.backend.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<Object> create(Product product) {
        try {
            // Cek apakah imageBase64 ada, jika ada simpan gambar dalam format Base64
            if (product.getImageBase64() != null && !product.getImageBase64().isEmpty()) {
                product.setImageBase64(product.getImageBase64());
            }

            Product savedProduct = productRepository.save(product);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error creating product: " + e.getMessage());
        }
    }

    public ResponseEntity<Object> detail(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.status(404).body("Product not found!");
        }
    }

    public ResponseEntity<Object> update(Long id, Product product) {
        Optional<Product> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            Product p = existing.get();
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setDescription(product.getDescription());

            // Update warna dan ukuran jika ada perubahan
            p.setColor(product.getColor());
            p.setSize(product.getSize());

            // Jika ada gambar baru dalam format Base64, simpan gambar tersebut
            if (product.getImageBase64() != null && !product.getImageBase64().isEmpty()) {
                p.setImageBase64(product.getImageBase64());
            }

            // Simpan produk yang telah diperbarui
            productRepository.save(p);
            return ResponseEntity.ok("Product updated successfully");
        }
        return ResponseEntity.status(404).body("Product not found");
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<Product> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Product deleted successfully");
        }
        return ResponseEntity.status(404).body("Product not found");
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    // ✅ Tambahkan ini untuk menampilkan semua produk
    public List<Product> listProduct() {
        return productRepository.findAll();
    }
}
