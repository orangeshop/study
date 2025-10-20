package study.testcode.chapterone.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class UserCreateRequest {
    private String email;
    private String nickname;
    private String address;
}
