package study.testcode.chapterone.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class UserUpdateRequest {
    private String email;
    private String nickname;
    private String address;
}
