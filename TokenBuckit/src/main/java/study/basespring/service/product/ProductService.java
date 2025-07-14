package study.basespring.service.product;

import study.basespring.entity.Product;
import java.util.List;

public interface ProductService {
    Product createProduct(String name, Long price, Long stockQuantity, String description);
    Product createProduct(String name, Long price, Long stockQuantity);
    Product findById(Long productId);
    List<Product> findAll();
    List<Product> findAvailableProducts();
    Product updateProduct(Long productId, String name, Long price, String description);
    Product updateStock(Long productId, Long stockQuantity);
    void deleteProduct(Long productId);
}
