package study.basespring.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.basespring.entity.Orders;
import study.basespring.global.OrderStatus;
import study.basespring.service.order.OrderService;

import java.util.List;

@Tag(name = "Order", description = "주문 관리 API")
@RequestMapping("/api/orders")
public interface OrderApi {

    @Operation(summary = "주문 생성", description = "새로운 주문을 생성합니다. 주문 시 재고가 자동으로 차감됩니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "주문 생성 성공",
            content = @Content(schema = @Schema(implementation = Orders.class))),
        @ApiResponse(responseCode = "400", description = "잘못된 요청 (재고 부족 등)"),
        @ApiResponse(responseCode = "404", description = "사용자 또는 상품을 찾을 수 없음")
    })
    @PostMapping
    ResponseEntity<Orders> createOrder(@RequestBody CreateOrderRequest request);

    @Operation(summary = "주문 조회", description = "ID로 특정 주문을 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공"),
        @ApiResponse(responseCode = "404", description = "주문을 찾을 수 없음")
    })
    @GetMapping("/{orderId}")
    ResponseEntity<Orders> getOrder(
            @Parameter(description = "주문 ID", required = true)
            @PathVariable Long orderId);

    @Operation(summary = "전체 주문 조회", description = "모든 주문을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    ResponseEntity<List<Orders>> getAllOrders();

    @Operation(summary = "사용자별 주문 조회", description = "특정 사용자의 모든 주문을 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공"),
        @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    @GetMapping("/user/{userId}")
    ResponseEntity<List<Orders>> getUserOrders(
            @Parameter(description = "사용자 ID", required = true)
            @PathVariable Long userId);

    @Operation(summary = "주문 상태 변경", description = "주문의 상태를 변경합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "상태 변경 성공"),
        @ApiResponse(responseCode = "404", description = "주문을 찾을 수 없음"),
        @ApiResponse(responseCode = "400", description = "잘못된 상태 변경 요청")
    })
    @PutMapping("/{orderId}/status")
    ResponseEntity<Orders> updateOrderStatus(
            @Parameter(description = "주문 ID", required = true)
            @PathVariable Long orderId,
            @RequestBody UpdateOrderStatusRequest request);

    @Operation(summary = "주문 취소", description = "주문을 취소합니다. 배송 완료된 주문은 취소할 수 없으며, 취소 시 재고가 복구됩니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "취소 성공"),
        @ApiResponse(responseCode = "404", description = "주문을 찾을 수 없음"),
        @ApiResponse(responseCode = "400", description = "배송 완료된 주문은 취소 불가")
    })
    @PostMapping("/{orderId}/cancel")
    ResponseEntity<Orders> cancelOrder(
            @Parameter(description = "주문 ID", required = true)
            @PathVariable Long orderId);

    // DTO records with Swagger annotations
    @Schema(description = "주문 생성 요청")
    record CreateOrderRequest(
        @Schema(description = "사용자 ID", example = "1", required = true)
        Long userId,
        
        @Schema(description = "주문 항목 목록", required = true)
        List<OrderService.OrderItemRequest> orderItems
    ) {}
    
    @Schema(description = "주문 상태 변경 요청")
    record UpdateOrderStatusRequest(
        @Schema(description = "변경할 주문 상태", example = "PROCESSING", required = true)
        OrderStatus status
    ) {}
}
