package study.testcode.chapterone.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserGetRequest {
    private String email;
}
