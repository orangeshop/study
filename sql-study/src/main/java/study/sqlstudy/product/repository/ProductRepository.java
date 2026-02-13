package study.sqlstudy.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.sqlstudy.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
