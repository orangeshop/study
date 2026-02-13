package study.sqlstudy.product.service;

import study.sqlstudy.product.entity.Product;

import java.util.List;

public interface ProductService {

    Product save(String name, String sku);

    Product findById(int id);

    List<Product> findAll();

    Product update(int id, String name, String sku);

    void delete(int id);
}
