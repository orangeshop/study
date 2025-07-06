package practice.jpa.domain.onetomanyoneway.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.jpa.domain.onetomanyoneway.product.Product;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_product")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Product> item = new ArrayList<>();
}
