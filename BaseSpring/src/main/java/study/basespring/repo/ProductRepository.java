package study.basespring.repo;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.basespring.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
