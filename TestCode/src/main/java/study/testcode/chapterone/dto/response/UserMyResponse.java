package study.testcode.chapterone.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserMyResponse {
    private String username;
    private String email;
    private String address;

    public static UserMyResponse from(String username, String email, String address) {
        return UserMyResponse.builder()
                .username(username)
                .email(email)
                .address(address)
                .build();
    }
}
