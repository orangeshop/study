package study.basespring.service.order;

import io.swagger.v3.oas.annotations.media.Schema;
import study.basespring.entity.Orders;
import study.basespring.global.OrderStatus;

import java.util.List;

public interface OrderService {
    Orders createOrder(Long userId, List<OrderItemRequest> orderItems);
    Orders findById(Long orderId);
    List<Orders> findByUserId(Long userId);
    List<Orders> findAll();
    Orders updateOrderStatus(Long orderId, OrderStatus status);
    Orders cancelOrder(Long orderId);
    
    // DTO for order item
    @Schema(description = "주문 항목 요청")
    record OrderItemRequest(
        @Schema(description = "상품 ID", example = "1", required = true)
        Long productId,
        
        @Schema(description = "주문 수량", example = "2", required = true)
        Long quantity
    ) {}
}