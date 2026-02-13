package study.sqlstudy.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.sqlstudy.product.entity.Product;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Product product;

    @Version
    private Long version;

    @Builder.Default
    private int quantity = 0;

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
        this.updatedAt = LocalDateTime.now();
    }

    public void decreaseStock(int amount) {
        if (this.quantity - amount < 0) {
            throw new IllegalStateException("재고 부족: 현재 " + this.quantity + ", 요청 " + amount);
        }
        this.quantity -= amount;
        this.updatedAt = LocalDateTime.now();
    }
}
