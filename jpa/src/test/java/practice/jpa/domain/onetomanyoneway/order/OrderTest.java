package practice.jpa.domain.onetomanyoneway.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import practice.jpa.domain.onetomanyoneway.product.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class OrderTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void oneToManyOneWayTest() {
        Order order = new Order();
        order.setOrderNumber("111");

        Product product1 = new Product();
        product1.setName("product1");

        Product product2 = new Product();
        product2.setName("product2");

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        order.setItem(products);

        //-----

//        entityManager.persist(product1);
//        entityManager.persist(product2);
        entityManager.persist(order);
        entityManager.flush();

    }
}