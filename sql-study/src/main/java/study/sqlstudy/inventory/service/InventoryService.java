package study.sqlstudy.inventory.service;

import study.sqlstudy.inventory.entity.Inventory;

import java.util.List;

public interface InventoryService {

    Inventory save(int productId, int quantity);

    Inventory findById(long id);

    Inventory findByProductId(int productId);

    List<Inventory> findAll();

    Inventory updateQuantity(long id, int quantity);

    void delete(long id);

    void decreaseStock(int productId, int amount);

    void decreaseStockOptimistic(int productId, int amount);

    void decreaseStockOptimisticRandom(int productId, int amount);
}
