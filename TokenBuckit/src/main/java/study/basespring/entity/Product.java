package study.basespring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = lombok.AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    private Long stockQuantity;

    private String description;
    
    // 정적 팩토리 메서드
    public static Product createProduct(String name, Long price, Long stockQuantity, String description) {
        return Product.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .description(description)
                .build();
    }
    
    // 설명 없이 생성
    public static Product createProduct(String name, Long price, Long stockQuantity) {
        return Product.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
    }
    
    // 비즈니스 메서드
    public void updateProductInfo(String name, Long price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    
    public void changePrice(Long newPrice) {
        if (newPrice < 0) {
            throw new IllegalArgumentException("가격은 0원 이상이어야 합니다.");
        }
        this.price = newPrice;
    }
    
    public void increaseStock(Long quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("증가 수량은 0보다 커야 합니다.");
        }
        this.stockQuantity += quantity;
    }
    
    public void decreaseStock(Long quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("감소 수량은 0보다 커야 합니다.");
        }
        if (this.stockQuantity < quantity) {
            throw new IllegalStateException("재고가 부족합니다. 현재 재고: " + this.stockQuantity);
        }
        this.stockQuantity -= quantity;
    }
    
    public boolean isAvailable() {
        return this.stockQuantity > 0;
    }
}
