package study.basespring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import study.basespring.controller.api.UserApi;
import study.basespring.entity.User;
import study.basespring.service.user.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    
    private final UserService userService;

    @Override
    public ResponseEntity<User> createUser(CreateUserRequest request) {
        User user = userService.createUser(
            request.email(), 
            request.password(), 
            request.name(), 
            request.address()
        );
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<User> getUser(Long userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> updateUser(Long userId, UpdateUserRequest request) {
        User user = userService.updateUser(userId, request.name(), request.address());
        return ResponseEntity.ok(user);
    }
}