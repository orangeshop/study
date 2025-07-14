package study.basespring.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "사용자 정보")
@Entity
@Getter
@Setter
@ToString(exclude = "ordersList")
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = lombok.AccessLevel.PRIVATE)
@Table(name = "member")
public class User {

    @Schema(description = "사용자 ID", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "이메일", example = "user@example.com")
    @NotNull
    private String email;

    @Schema(description = "비밀번호 (암호화 예정)", example = "password123")
    @NotNull
    private String password;

    @Schema(description = "이름", example = "홍길동")
    @NotNull
    private String name;

    @Schema(description = "주소", example = "서울시 강남구")
    private String address;

    @Schema(hidden = true)
    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Orders> ordersList = new ArrayList<>();
    
    // 정적 팩토리 메서드
    public static User createUser(String email, String password, String name, String address) {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .address(address)
                .build();
    }
    
    // 필수 정보만으로 생성
    public static User createUser(String email, String password, String name) {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
    
    // 비즈니스 메서드
    public void updateUserInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }
    
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
