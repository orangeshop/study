package study.sqlstudy.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.sqlstudy.product.entity.Product;
import study.sqlstudy.product.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product save(String name, String sku) {
        return productRepository.save(Product.builder()
                .name(name)
                .sku(sku)
                .build());
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product update(int id, String name, String sku) {
        Product product = findById(id);
        product.update(name, sku);
        return product;
    }

    @Override
    @Transactional
    public void delete(int id) {
        productRepository.deleteById(id);
    }
}
