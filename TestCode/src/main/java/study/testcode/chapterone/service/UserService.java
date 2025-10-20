package study.testcode.chapterone.service;


import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.testcode.chapterone.dto.request.UserCreateRequest;
import study.testcode.chapterone.dto.request.UserGetRequest;
import study.testcode.chapterone.dto.request.UserUpdateRequest;
import study.testcode.chapterone.dto.response.UserMyResponse;
import study.testcode.chapterone.dto.response.UserResponse;
import study.testcode.chapterone.entoty.Users;
import study.testcode.chapterone.repository.UserRepository;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    public void save(UserCreateRequest request) {
        System.out.println(request.getEmail());
        Users user = userRepository.save(Users.to(request));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getEmail());
        message.setSubject("회원가입 인증 이메일 입니다.");

        String verificationUrl = "http://localhost:8081/api/users/me/" + user.getId() + "/verify";
        message.setText("인증을 완료하려면 링크를 클릭하세요: " + verificationUrl);

        mailSender.send(message);

    }

    public Users update(UserUpdateRequest request) {
        return userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("not found user")
        );
    }

    public UserMyResponse get(UserGetRequest request) {
        Users user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("not found user")
        );

        return UserMyResponse.from(user.getNickname(), user.getEmail(), user.getAddress());
    }

    public void verify(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("not found user")
        );

        user.verifyState();
    }

    public UserResponse getUserProfile(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("not found user")
        );

        return UserResponse.from(user.getNickname(), user.getEmail());
    }
}
