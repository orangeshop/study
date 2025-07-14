package study.basespring.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.basespring.entity.Product;
import study.basespring.repo.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product createProduct(String name, Long price, Long stockQuantity, String description) {
        Product product = Product.createProduct(name, price, stockQuantity, description);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product createProduct(String name, Long price, Long stockQuantity) {
        Product product = Product.createProduct(name, price, stockQuantity);
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. ID: " + productId));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAvailableProducts() {
        return productRepository.findAll().stream()
                .filter(Product::isAvailable)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Product updateProduct(Long productId, String name, Long price, String description) {
        Product product = findById(productId);
        product.updateProductInfo(name, price, description);
        return product;
    }

    @Override
    @Transactional
    public Product updateStock(Long productId, Long stockQuantity) {
        Product product = findById(productId);
        Long currentStock = product.getStockQuantity();
        
        if (stockQuantity > currentStock) {
            product.increaseStock(stockQuantity - currentStock);
        } else if (stockQuantity < currentStock) {
            product.decreaseStock(currentStock - stockQuantity);
        }
        
        return product;
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        Product product = findById(productId);
        if (product.getStockQuantity() > 0) {
            throw new IllegalStateException("재고가 있는 상품은 삭제할 수 없습니다.");
        }
        productRepository.delete(product);
    }
}
