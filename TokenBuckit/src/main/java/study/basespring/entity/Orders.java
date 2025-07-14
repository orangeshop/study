package study.basespring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import study.basespring.global.OrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"user", "orderItems"})
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = lombok.AccessLevel.PRIVATE)
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;

    private Long totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();
    
    // 정적 팩토리 메서드
    public static Orders createOrder(User user) {
        return Orders.builder()
                .user(user)
                .orderDate(LocalDate.now())
                .orderStatus(OrderStatus.PENDING)
                .totalAmount(0L)
                .build();
    }
    
    // 주문 항목과 함께 생성
    public static Orders createOrder(User user, List<OrderItem> orderItems) {
        Orders order = Orders.builder()
                .user(user)
                .orderDate(LocalDate.now())
                .orderStatus(OrderStatus.PENDING)
                .orderItems(orderItems)
                .build();
        
        // 총 금액 계산
        order.calculateTotalAmount();
        
        // 양방향 관계 설정
        orderItems.forEach(item -> item.setOrders(order));
        
        return order;
    }
    
    // 비즈니스 메서드
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrders(this);
        calculateTotalAmount();
    }
    
    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        orderItem.setOrders(null);
        calculateTotalAmount();
    }
    
    public void calculateTotalAmount() {
        this.totalAmount = orderItems.stream()
                .mapToLong(OrderItem::getTotalPrice)
                .sum();
    }
    
    public void changeOrderStatus(OrderStatus newStatus) {
        this.orderStatus = newStatus;
    }
    
    public void cancelOrder() {
        if (this.orderStatus == OrderStatus.DELIVERED) {
            throw new IllegalStateException("배송 완료된 주문은 취소할 수 없습니다.");
        }
        this.orderStatus = OrderStatus.CANCELLED;
    }
}
