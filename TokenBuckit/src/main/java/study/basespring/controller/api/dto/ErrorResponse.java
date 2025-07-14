package study.basespring.controller.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "에러 응답")
public record ErrorResponse(
    @Schema(description = "에러 코드", example = "BAD_REQUEST")
    String code,
    
    @Schema(description = "에러 메시지", example = "잘못된 요청입니다.")
    String message
) {}
