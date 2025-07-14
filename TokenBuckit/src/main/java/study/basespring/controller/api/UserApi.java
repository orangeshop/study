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
import study.basespring.entity.User;

import java.util.List;

@Tag(name = "User", description = "사용자 관리 API")
@RequestMapping("/api/users")
public interface UserApi {

    @Operation(summary = "사용자 생성", description = "새로운 사용자를 생성합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "사용자 생성 성공",
            content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "잘못된 요청 (이메일 중복 등)",
            content = @Content(schema = @Schema(implementation = study.basespring.controller.api.dto.ErrorResponse.class)))
    })
    @PostMapping
    ResponseEntity<User> createUser(@RequestBody CreateUserRequest request);

    @Operation(summary = "사용자 조회", description = "ID로 특정 사용자를 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공"),
        @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    @GetMapping("/{userId}")
    ResponseEntity<User> getUser(
            @Parameter(description = "사용자 ID", required = true)
            @PathVariable Long userId);

    @Operation(summary = "전체 사용자 조회", description = "모든 사용자를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    ResponseEntity<List<User>> getAllUsers();

    @Operation(summary = "사용자 정보 수정", description = "사용자의 이름과 주소를 수정합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "수정 성공"),
        @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    @PutMapping("/{userId}")
    ResponseEntity<User> updateUser(
            @Parameter(description = "사용자 ID", required = true)
            @PathVariable Long userId,
            @RequestBody UpdateUserRequest request);

    // DTO records with Swagger annotations
    @Schema(description = "사용자 생성 요청")
    record CreateUserRequest(
        @Schema(description = "이메일", example = "user@example.com", required = true)
        String email,
        
        @Schema(description = "비밀번호", example = "password123", required = true)
        String password,
        
        @Schema(description = "이름", example = "홍길동", required = true)
        String name,
        
        @Schema(description = "주소", example = "서울시 강남구")
        String address
    ) {}
    
    @Schema(description = "사용자 정보 수정 요청")
    record UpdateUserRequest(
        @Schema(description = "이름", example = "홍길동", required = true)
        String name,
        
        @Schema(description = "주소", example = "서울시 강남구")
        String address
    ) {}
}
