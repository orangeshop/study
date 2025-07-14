package study.basespring.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.basespring.entity.User;
import study.basespring.repo.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(String email, String password, String name, String address) {
        // 이메일 중복 체크
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 사용중인 이메일입니다: " + email);
        }
        
        // 정적 팩토리 메서드 사용
        User user = User.createUser(email, password, name, address);
        
        return userRepository.save(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + userId));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. Email: " + email));
    }

    @Override
    @Transactional
    public User updateUser(Long userId, String name, String address) {
        User user = findById(userId);
        // 비즈니스 메서드 사용
        user.updateUserInfo(name, address);
        return user;
    }
}