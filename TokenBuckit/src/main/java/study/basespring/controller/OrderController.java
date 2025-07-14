package study.basespring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import study.basespring.controller.api.OrderApi;
import study.basespring.entity.Orders;
import study.basespring.global.OrderStatus;
import study.basespring.service.order.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {
    
    private final OrderService orderService;

    @Override
    public ResponseEntity<Orders> createOrder(CreateOrderRequest request) {
        Orders order = orderService.createOrder(request.userId(), request.orderItems());
        return ResponseEntity.ok(order);
    }

    @Override
    public ResponseEntity<Orders> getOrder(Long orderId) {
        Orders order = orderService.findById(orderId);
        return ResponseEntity.ok(order);
    }

    @Override
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @Override
    public ResponseEntity<List<Orders>> getUserOrders(Long userId) {
        List<Orders> orders = orderService.findByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @Override
    public ResponseEntity<Orders> updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {
        Orders order = orderService.updateOrderStatus(orderId, request.status());
        return ResponseEntity.ok(order);
    }

    @Override
    public ResponseEntity<Orders> cancelOrder(Long orderId) {
        Orders order = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(order);
    }
}