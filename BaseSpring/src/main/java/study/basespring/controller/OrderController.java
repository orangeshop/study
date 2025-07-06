package study.basespring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.basespring.entity.Orders;
import study.basespring.global.OrderStatus;
import study.basespring.service.order.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody CreateOrderRequest request) {
        Orders order = orderService.createOrder(request.userId(), request.totalAmount());
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrder(@PathVariable Long orderId) {
        Orders order = orderService.findById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Orders>> getUserOrders(@PathVariable Long userId) {
        List<Orders> orders = orderService.findByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<Orders> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody UpdateOrderStatusRequest request) {
        Orders order = orderService.updateOrderStatus(orderId, request.status());
        return ResponseEntity.ok(order);
    }

    // DTO records
    public record CreateOrderRequest(Long userId, Long totalAmount) {}
    public record UpdateOrderStatusRequest(OrderStatus status) {}
}