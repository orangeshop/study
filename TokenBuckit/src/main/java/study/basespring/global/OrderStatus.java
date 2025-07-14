package study.basespring.global;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "주문 상태")
public enum OrderStatus {
    @Schema(description = "주문 대기중")
    PENDING,
    
    @Schema(description = "처리중")
    PROCESSING,
    
    @Schema(description = "배송중")
    SHIPPING,
    
    @Schema(description = "배송 완료")
    DELIVERED,
    
    @Schema(description = "주문 취소")
    CANCELLED
}
