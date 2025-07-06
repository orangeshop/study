package study.basespring.service.order;

import study.basespring.entity.Orders;
import study.basespring.global.OrderStatus;

import java.util.List;

public interface OrderService {
    Orders createOrder(Long userId, Long totalAmount);
    Orders findById(Long orderId);
    List<Orders> findByUserId(Long userId);
    Orders updateOrderStatus(Long orderId, OrderStatus status);
}