package study.testcode.chapterone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.testcode.chapterone.dto.request.UserCreateRequest;
import study.testcode.chapterone.dto.request.UserGetRequest;
import study.testcode.chapterone.dto.request.UserUpdateRequest;
import study.testcode.chapterone.dto.response.UserMyResponse;
import study.testcode.chapterone.dto.response.UserResponse;
import study.testcode.chapterone.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserMyResponse getMyProfile(@RequestBody UserGetRequest request) {
        return userService.get(request);
    }

    @PutMapping("/me")
    public void updateMyProfile(@RequestBody UserUpdateRequest request) {
        userService.update(request);
    }

    @PostMapping("/me")
    public void createMyProfile(@RequestBody UserCreateRequest request) {
        userService.save(request);
    }

    @GetMapping("/me/{id}")
    public UserResponse getUserProfile(@PathVariable Long id) {
        return userService.getUserProfile(id);
    }

    @GetMapping("/me/{id}/verify")
    public void getVerifyUserProfile(@PathVariable Long id) {
        userService.verify(id);
    }
}
