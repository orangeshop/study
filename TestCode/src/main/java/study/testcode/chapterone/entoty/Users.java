package study.testcode.chapterone.entoty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.testcode.chapterone.dto.request.UserCreateRequest;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickname;

    private String address;

    private Boolean state;

    private LocalDateTime lastLoginAt;

    private UUID uuid;

    public static Users to(UserCreateRequest request) {
        return Users.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .address(request.getAddress())
                .lastLoginAt(LocalDateTime.now())
                .uuid(UUID.randomUUID())
                .state(Boolean.FALSE)
                .build();
    }

    public void verifyState() {
        this.state = !this.state;
        System.out.println("@@@@@@" + this.state);
    }
}
