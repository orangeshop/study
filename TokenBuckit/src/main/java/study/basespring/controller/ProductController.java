package study.basespring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import study.basespring.controller.api.ProductApi;
import study.basespring.entity.Product;
import study.basespring.service.product.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {
    
    private final ProductService productService;

    @Override
    public ResponseEntity<Product> createProduct(CreateProductRequest request) {
        Product product = productService.createProduct(
            request.name(), 
            request.price(), 
            request.stockQuantity(), 
            request.description()
        );
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<Product> getProduct(Long productId) {
        Product product = productService.findById(productId);
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @Override
    public ResponseEntity<List<Product>> getAvailableProducts() {
        List<Product> products = productService.findAvailableProducts();
        return ResponseEntity.ok(products);
    }

    @Override
    public ResponseEntity<Product> updateProduct(Long productId, UpdateProductRequest request) {
        Product product = productService.updateProduct(
            productId, 
            request.name(), 
            request.price(), 
            request.description()
        );
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<Product> updateStock(Long productId, UpdateStockRequest request) {
        Product product = productService.updateStock(productId, request.stockQuantity());
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
