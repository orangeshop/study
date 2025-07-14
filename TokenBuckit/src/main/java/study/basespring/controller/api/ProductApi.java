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
import study.basespring.entity.Product;

import java.util.List;

@Tag(name = "Product", description = "상품 관리 API")
@RequestMapping("/api/products")
public interface ProductApi {

    @Operation(summary = "상품 생성", description = "새로운 상품을 등록합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "상품 생성 성공",
            content = @Content(schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping
    ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request);

    @Operation(summary = "상품 조회", description = "ID로 특정 상품을 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공"),
        @ApiResponse(responseCode = "404", description = "상품을 찾을 수 없음")
    })
    @GetMapping("/{productId}")
    ResponseEntity<Product> getProduct(
            @Parameter(description = "상품 ID", required = true)
            @PathVariable Long productId);

    @Operation(summary = "전체 상품 조회", description = "모든 상품을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    ResponseEntity<List<Product>> getAllProducts();

    @Operation(summary = "재고 있는 상품 조회", description = "재고가 1개 이상인 상품만 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/available")
    ResponseEntity<List<Product>> getAvailableProducts();

    @Operation(summary = "상품 정보 수정", description = "상품의 이름, 가격, 설명을 수정합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "수정 성공"),
        @ApiResponse(responseCode = "404", description = "상품을 찾을 수 없음")
    })
    @PutMapping("/{productId}")
    ResponseEntity<Product> updateProduct(
            @Parameter(description = "상품 ID", required = true)
            @PathVariable Long productId,
            @RequestBody UpdateProductRequest request);

    @Operation(summary = "재고 수량 수정", description = "상품의 재고 수량을 수정합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "수정 성공"),
        @ApiResponse(responseCode = "404", description = "상품을 찾을 수 없음"),
        @ApiResponse(responseCode = "400", description = "재고 부족")
    })
    @PutMapping("/{productId}/stock")
    ResponseEntity<Product> updateStock(
            @Parameter(description = "상품 ID", required = true)
            @PathVariable Long productId,
            @RequestBody UpdateStockRequest request);

    @Operation(summary = "상품 삭제", description = "상품을 삭제합니다. (재고가 0인 경우만 가능)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "삭제 성공"),
        @ApiResponse(responseCode = "404", description = "상품을 찾을 수 없음"),
        @ApiResponse(responseCode = "400", description = "재고가 있는 상품은 삭제 불가")
    })
    @DeleteMapping("/{productId}")
    ResponseEntity<Void> deleteProduct(
            @Parameter(description = "상품 ID", required = true)
            @PathVariable Long productId);

    // DTO records with Swagger annotations
    @Schema(description = "상품 생성 요청")
    record CreateProductRequest(
        @Schema(description = "상품명", example = "Spring Boot 입문서", required = true)
        String name,
        
        @Schema(description = "가격", example = "25000", required = true)
        Long price,
        
        @Schema(description = "재고 수량", example = "100", required = true)
        Long stockQuantity,
        
        @Schema(description = "상품 설명", example = "Spring Boot를 처음 배우는 사람을 위한 입문서")
        String description
    ) {}
    
    @Schema(description = "상품 정보 수정 요청")
    record UpdateProductRequest(
        @Schema(description = "상품명", example = "Spring Boot 입문서 개정판", required = true)
        String name,
        
        @Schema(description = "가격", example = "28000", required = true)
        Long price,
        
        @Schema(description = "상품 설명", example = "Spring Boot 3.0 기준으로 개정된 입문서")
        String description
    ) {}
    
    @Schema(description = "재고 수량 수정 요청")
    record UpdateStockRequest(
        @Schema(description = "재고 수량", example = "150", required = true)
        Long stockQuantity
    ) {}
}
