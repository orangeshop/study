package study.basespring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.basespring.global.OrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;

    private Long totalAmount;

    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn
    private User user;

    @OneToMany
    @Builder.Default
    List<OrderItem> orderItems = new ArrayList<>();
}
