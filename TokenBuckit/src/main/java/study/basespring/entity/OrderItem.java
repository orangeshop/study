package study.basespring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = {"orders", "product"})
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = lombok.AccessLevel.PRIVATE)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderPrice;

    private Long count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    
    // 정적 팩토리 메서드
    public static OrderItem createOrderItem(Product product, Long orderPrice, Long count) {
        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .orderPrice(orderPrice)
                .count(count)
                .build();
        
        // 재고 감소
        product.decreaseStock(count);
        
        return orderItem;
    }
    
    // 상품 정보로부터 가격을 가져와서 생성
    public static OrderItem createOrderItem(Product product, Long count) {
        return createOrderItem(product, product.getPrice(), count);
    }
    
    // 비즈니스 메서드
    public Long getTotalPrice() {
        return orderPrice * count;
    }
    
    public void cancel() {
        // 재고 복구
        if (product != null) {
            product.increaseStock(count);
        }
    }
    
    public void updateCount(Long newCount) {
        if (newCount <= 0) {
            throw new IllegalArgumentException("수량은 0보다 커야 합니다.");
        }
        
        Long diff = newCount - this.count;
        
        if (diff > 0) {
            // 수량 증가 - 재고 확인 필요
            product.decreaseStock(diff);
        } else if (diff < 0) {
            // 수량 감소 - 재고 복구
            product.increaseStock(-diff);
        }
        
        this.count = newCount;
    }
}
