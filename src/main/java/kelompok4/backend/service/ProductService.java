package kelompok4.backend.service;

import kelompok4.backend.entity.Product;
import kelompok4.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Mendapatkan semua produk
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Mendapatkan produk berdasarkan ID
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    // Membuat produk baru
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Mengupdate produk yang sudah ada
    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProductOpt = productRepository.findById(id);

        if(existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setColor(updatedProduct.getColor());
            existingProduct.setSize(updatedProduct.getSize());

            // Jika ada gambar baru, update gambar Base64
            if (updatedProduct.getImageBase64() != null) {
                existingProduct.setImageBase64(updatedProduct.getImageBase64());
            }

            return productRepository.save(existingProduct); // Simpan perubahan
        }

        return null;
    }

    // Menghapus produk berdasarkan ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
