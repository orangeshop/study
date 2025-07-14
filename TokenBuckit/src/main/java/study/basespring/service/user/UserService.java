package study.basespring.service.user;

import study.basespring.entity.User;

import java.util.List;

public interface UserService {
    User createUser(String email, String password, String name, String address);
    User findById(Long userId);
    List<User> findAll();
    User findByEmail(String email);
    User updateUser(Long userId, String name, String address);
}