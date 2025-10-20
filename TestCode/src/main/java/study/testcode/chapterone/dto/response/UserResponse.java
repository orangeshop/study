package study.testcode.chapterone.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    private String username;
    private String email;

    public static UserResponse from(String username, String email) {
        return UserResponse.builder()
                .username(username)
                .email(email)
                .build();
    }
}
