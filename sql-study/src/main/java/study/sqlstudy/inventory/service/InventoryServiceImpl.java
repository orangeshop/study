package study.sqlstudy.inventory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.sqlstudy.inventory.entity.Inventory;
import study.sqlstudy.inventory.repository.InventoryRepository;
import study.sqlstudy.product.entity.Product;
import study.sqlstudy.product.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductService productService;

    @Override
    @Transactional
    public Inventory save(int productId, int quantity) {
        Product product = productService.findById(productId);
        return inventoryRepository.save(Inventory.builder()
                .product(product)
                .quantity(quantity)
                .build());
    }

    @Override
    public Inventory findById(long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found: " + id));
    }

    @Override
    public Inventory findByProductId(int productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found for product: " + productId));
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @Override
    @Transactional
    public Inventory updateQuantity(long id, int quantity) {
        Inventory inventory = findById(id);
        inventory.updateQuantity(quantity);
        return inventory;
    }

    @Override
    @Transactional
    public void delete(long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void decreaseStock(int productId, int amount) {
        Inventory inventory = inventoryRepository.findByProductIdWithLock(productId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found for product: " + productId));
        System.out.println("[" + Thread.currentThread().getName() + "] 비관적 락 획득 - 현재 재고: " + inventory.getQuantity());
        inventory.decreaseStock(amount);
    }

    @Override
    @Transactional
    @Retryable(
            retryFor = ObjectOptimisticLockingFailureException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 50)
    )
    public void decreaseStockOptimistic(int productId, int amount) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found for product: " + productId));
        System.out.println("[" + Thread.currentThread().getName() + "] 낙관적 락(고정) - 현재 재고: " + inventory.getQuantity() + ", version: " + inventory.getVersion());
        inventory.decreaseStock(amount);
    }

    @Override
    @Transactional
    @Retryable(
            retryFor = ObjectOptimisticLockingFailureException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 50, maxDelay = 300, random = true)
    )
    public void decreaseStockOptimisticRandom(int productId, int amount) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found for product: " + productId));
        System.out.println("[" + Thread.currentThread().getName() + "] 낙관적 락(랜덤) - 현재 재고: " + inventory.getQuantity() + ", version: " + inventory.getVersion());
        inventory.decreaseStock(amount);
    }
}
